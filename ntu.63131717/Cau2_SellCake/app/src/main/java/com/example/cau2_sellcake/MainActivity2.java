package com.example.cau2_sellcake;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity2 extends AppCompatActivity {

    ImageView imgCake2;
    TextView txtName2,txtCoin2;
    Button btnAdd2;
    ImageButton imgAdd2;
    EditText inputQ;
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
        imgAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int inputQuantity = Integer.parseInt(inputQ.getText().toString());
                inputQuantity+=1;
                inputQ.setText(String.valueOf(inputQuantity));
                int rate = inputQuantity * coin2;
                txtCoin2.setText(String.valueOf(rate + "đ"));

            }
        });
        btnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity2.this);
                alertDialog.setTitle("Success");
                alertDialog.setMessage("Mua hàng thành công");
                alertDialog.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });

    }

    private void addInit() {
        imgCake2 = findViewById(R.id.imgCake2);
        txtName2 = findViewById(R.id.nameCake2);
        txtCoin2 = findViewById(R.id.coinCake2);
        imgAdd2 = findViewById(R.id.imgAdd);
        btnAdd2 = findViewById(R.id.btnAdd);
        inputQ = findViewById(R.id.quantityCake2);

    }
}