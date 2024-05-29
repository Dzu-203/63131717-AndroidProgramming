package com.example.ex_6cong_listviewthemxoasua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    Button btnSave;
    EditText edtSua,edtCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addInit();
        Intent intent = getIntent();
        String course = intent.getStringExtra("key");
        edtCourse.setText(course);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rsnew = edtSua.getText().toString();
                intent.putExtra("rs",rsnew);
                setResult(11,intent);
                finish();
            }
        });

    }

    private void addInit() {
        btnSave = findViewById(R.id.btnsave);
        edtSua = findViewById(R.id.edtSua);
        edtCourse = findViewById(R.id.edtcourse2);
    }

}