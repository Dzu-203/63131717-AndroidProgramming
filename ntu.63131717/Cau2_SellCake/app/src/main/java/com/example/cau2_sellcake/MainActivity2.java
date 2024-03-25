package com.example.cau2_sellcake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    ImageView imgCake2;
    TextView txtName2,txtCoin2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addInit();
        Intent intent = getIntent();
        int img2 = intent.getIntExtra("img",0);
        String name2 = intent.getStringExtra("name");
        int coin2 = intent.getIntExtra("coin",0);
        imgCake2.setImageResource(img2);
        txtName2.setText(name2);
        txtCoin2.setText(String.valueOf(coin2 + "đ"));
    }

    private void addInit() {
        imgCake2 = findViewById(R.id.imgCake2);
        txtName2 = findViewById(R.id.nameCake2);
        txtCoin2 = findViewById(R.id.coinCake2);
    }
}