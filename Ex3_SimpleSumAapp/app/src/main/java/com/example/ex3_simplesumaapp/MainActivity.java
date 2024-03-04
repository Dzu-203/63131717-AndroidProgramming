package com.example.ex3_simplesumaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edta,edtb,edtresult;
    Button btnTong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addInit();
        btnTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double a = Double.parseDouble(edta.getText().toString());
                double b = Double.parseDouble(edtb.getText().toString());
                edtresult.setText(String.valueOf(a + b));
            }
        });
    }
    public void addInit(){
        edta = findViewById(R.id.edta);
        edtb = findViewById(R.id.edtb);
        edtresult = findViewById(R.id.edtresult);
        btnTong = findViewById(R.id.btnTong);

    }
}