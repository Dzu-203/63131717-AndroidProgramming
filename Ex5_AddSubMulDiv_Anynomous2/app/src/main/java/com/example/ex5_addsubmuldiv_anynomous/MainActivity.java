package com.example.ex5_addsubmuldiv_anynomous;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edta,edtb,edtresult;
    Button btnCong,btnTru,btnNhan,btnChia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addInit();
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleCong();
            }
        });
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTru();
            }
        });
        btnNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNhan();
            }
        });
        btnChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleChia();
            }
        });

    }

    private void addInit() {
        edta = findViewById(R.id.edta);
        edtb = findViewById(R.id.edtb);
        edtresult = findViewById(R.id.edtresult);
        btnCong = findViewById(R.id.btncong);
        btnTru = findViewById(R.id.btntru);
        btnNhan = findViewById(R.id.btnnhan);
        btnChia = findViewById(R.id.btnchia);
    }
    public void handleCong(){
        double a = Double.parseDouble(edta.getText().toString());
        double b = Double.parseDouble(edtb.getText().toString());
        edtresult.setText(String.valueOf(a+b));
    }
    public void handleTru(){
        double a = Double.parseDouble(edta.getText().toString());
        double b = Double.parseDouble(edtb.getText().toString());
        edtresult.setText(String.valueOf(a-b));
    }
    public void handleNhan(){
        double a = Double.parseDouble(edta.getText().toString());
        double b = Double.parseDouble(edtb.getText().toString());
        edtresult.setText(String.valueOf(a*b));
    }
    public void handleChia(){
        double a = Double.parseDouble(edta.getText().toString());
        double b = Double.parseDouble(edtb.getText().toString());
        if (b == 0){
            edtresult.setText(" ");
            Toast.makeText(MainActivity.this,"Không thể chia cho 0",Toast.LENGTH_SHORT).show();
        }else{
            edtresult.setText(String.valueOf(a/b));
        }

    }
}