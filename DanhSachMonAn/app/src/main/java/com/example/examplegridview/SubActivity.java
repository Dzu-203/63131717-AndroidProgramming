package com.example.examplegridview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    TextView txtten,txtgia;
    ImageView imgmon;
    Button btnout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        txtgia = findViewById(R.id.txt_gia);
        txtten = findViewById(R.id.txt_mon);
        imgmon = findViewById(R.id.imgmon);
        Intent intent = getIntent();
        int anh = intent.getIntExtra("img",0);
        String ten = intent.getStringExtra("ten");
        int gia = intent.getIntExtra("gia",0);
        imgmon.setImageResource(anh);
        txtten.setText(ten);
        txtgia.setText(String.format("Giá : " + gia));
        btnout = findViewById(R.id.btnout);
        btnout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}