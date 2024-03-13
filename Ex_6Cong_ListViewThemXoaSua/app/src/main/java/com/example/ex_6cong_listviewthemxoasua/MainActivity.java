package com.example.ex_6cong_listviewthemxoasua;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnThem,btnXoa,btnSua,btnThoat;
    EditText edtCourse;
    ListView lv;
    ArrayAdapter<String> adapter;
    List<String> listCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addInit();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = edtCourse.getText().toString();
                if (data.isEmpty()){
                    Toast.makeText(MainActivity.this,"Vui lòng nhập tên khóa học !!",Toast.LENGTH_SHORT).show();
                }
                listCourses.add(data);
                adapter.notifyDataSetChanged();
                edtCourse.setText("");
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = edtCourse.getText().toString();
                if (data.isEmpty()){
                    Toast.makeText(MainActivity.this,"Vui lòng nhập tên khóa học cần xóa",Toast.LENGTH_SHORT).show();
                }
                for (int i = 0; i < listCourses.size();i++){
                    if (data.equals(listCourses.get(i))){
                        listCourses.remove(data);
                    }
                }
                adapter.notifyDataSetChanged();
                edtCourse.setText("");
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = edtCourse.getText().toString();
                if (data.isEmpty()){
                    Toast.makeText(MainActivity.this,"Vui lòng nhập tên khóa học cần sửa",Toast.LENGTH_SHORT).show();
                }
                else {
                    listCourses.remove(data);
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("key",data);
                    startActivityForResult(intent,99);
                }
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == 11) {
            String courseNew = data.getStringExtra("rs");
            listCourses.add(courseNew);
        }else {
            Toast.makeText(MainActivity.this,"Lỗi",Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();
    }

    private void addInit() {
        btnThem = findViewById(R.id.btnthem);
        btnXoa = findViewById(R.id.btnxoa);
        btnSua = findViewById(R.id.btnsua);
        btnThoat = findViewById(R.id.bntthoat);
        edtCourse = findViewById(R.id.edtcourse);
        lv = findViewById(R.id.lv);
        listCourses = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listCourses);
        lv.setAdapter(adapter);
    }

}