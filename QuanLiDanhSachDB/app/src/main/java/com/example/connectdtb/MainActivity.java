package com.example.connectdtb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnInsert,btnDelete,btnUpdate,btnQuery;
    EditText edMaLop,edTenLop,edSiSo;
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> adapter;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvent();
    }

    private void addControl() {
        btnInsert = findViewById(R.id.btninsert);
        btnDelete = findViewById(R.id.btndelete);
        btnUpdate = findViewById(R.id.btnupdate);
        btnQuery = findViewById(R.id.btnquery);
        edMaLop = findViewById(R.id.edmalop);
        edTenLop = findViewById(R.id.edtenlop);
        edSiSo = findViewById(R.id.edsiso);
        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,mylist);
        lv.setAdapter(adapter);
        //tạo database
        sqLiteDatabase = openOrCreateDatabase("qlsv.db",MODE_PRIVATE,null);
        try {
            String sql = "CREATE TABLE lop(malop TEXT primary key, tenlop TEXT, siso INTEGER)";
            sqLiteDatabase.execSQL(sql);

        }catch (Exception e){
            Log.e("error","Table đã tồn tại");
        }
    }
    private void addEvent() {
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malop = edMaLop.getText().toString();
                String tenlop = edTenLop.getText().toString();
                int siso = Integer.parseInt(edSiSo.getText().toString());
                ContentValues contentValues = new ContentValues();
                contentValues.put("malop",malop);
                contentValues.put("tenlop",tenlop);
                contentValues.put("siso",siso);
                long n = sqLiteDatabase.insert("lop",null,contentValues);
                String msg = "";
                if (n == -1){
                    msg = "No add record to table";
                }else {
                    msg = "Add record successfully";
                }
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malop = edMaLop.getText().toString();
                String tenlop = edTenLop.getText().toString();
                int siso = Integer.parseInt(edSiSo.getText().toString());
                ContentValues contentValues = new ContentValues();
                contentValues.put("tenlop",tenlop);
                contentValues.put("siso",siso);
                int n = sqLiteDatabase.update("lop",contentValues,"malop = ?",new String[]{malop});
                String msg = "";
                if (n == 0){
                    msg = "No update to table";
                }else {
                    msg = n + " update successfully";
                }
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malop = edMaLop.getText().toString();
                int n = sqLiteDatabase.delete("lop","malop = ?",new String[]{malop});
                String msg = "";
                if (n == 0){
                    msg = "No update to table";
                }else {
                    msg = n + " update successfully";
                }
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mylist.clear();
                Cursor cursor = sqLiteDatabase.query("lop",null,null,null,null,null,null) ;
                cursor.moveToNext();
                String data = "";
                while (!cursor.isAfterLast()){
                    data = String.format("%s | %s | %s ",cursor.getString(0),cursor.getString(1),cursor.getString(2));
                    mylist.add(data);
                    cursor.moveToNext();
                }
                cursor.close();
                adapter.notifyDataSetChanged();

            }
        });
    }
}
