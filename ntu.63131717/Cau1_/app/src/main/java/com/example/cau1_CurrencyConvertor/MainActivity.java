package com.example.cau1_CurrencyConvertor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtCr1,edtCr2;
    Spinner spinner1,spinner2;
    TextView txtHienThi;
    ImageButton imageConvert;
    List<String> ListCurrency;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addInit();
        imageConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency();
            }
        });

    }

    private void addInit() {
        edtCr1 = findViewById(R.id.edtCurrency1);
        edtCr2 = findViewById(R.id.edtCurrency2);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        txtHienThi = findViewById(R.id.txtHienThi);
        imageConvert = findViewById(R.id.imgConvert);
        ListCurrency = new ArrayList<>();
        ListCurrency.add("VNĐ");
        ListCurrency.add("USD");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,ListCurrency);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
    }
    public void convertCurrency(){
        String StrcurrencyC = (edtCr1.getText().toString());
        if (StrcurrencyC.isEmpty()){
            Toast.makeText(MainActivity.this,"Vui lòng nhập số tiền cần qui đổi",Toast.LENGTH_SHORT).show();
        }
        else {
            double currencyC = Double.parseDouble(StrcurrencyC);
            double rate = 0;
            String currency1 = String.valueOf(spinner1.getSelectedItem());
            String currency2 = String.valueOf(spinner2.getSelectedItem());

            if (currency1.equals("USD") && currency2.equals("VNĐ")){
                rate = currencyC * 25000;
                txtHienThi.setText(String.format("%s USD = %s VNĐ",currencyC,rate));
            }else if (currency1.equals("VNĐ") && currency2.equals("USD")) {
                rate = currencyC / 25000;
                txtHienThi.setText(String.format("%s VNĐ = %s USD",currencyC,rate));
            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông báo");
                builder.setIcon(R.drawable.logo);
                builder.setMessage("Vui lòng xem lại tiền tệ chuyển đổi !!");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                edtCr2.setText("");
            }
            edtCr2.setText(String.valueOf(rate));
        }

    }
}