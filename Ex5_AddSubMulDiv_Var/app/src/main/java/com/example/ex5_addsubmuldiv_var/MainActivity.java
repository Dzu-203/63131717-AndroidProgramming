package com.example.ex5_addsubmuldiv_var;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edta,edtb;
    TextView edtresult;
    Button btnCong,btnTru,btnNhan,btnChia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        btnCong.setOnClickListener(handleCong);
        btnTru.setOnClickListener(handleTru);
        btnNhan.setOnClickListener(handleNhan);
        btnChia.setOnClickListener(handleChia);


    }

    private void addControl() {
        edta = findViewById(R.id.edta);
        edtb = findViewById(R.id.edtb);
        edtresult = findViewById(R.id.edtresult);
        btnCong = findViewById(R.id.btncong);
        btnTru = findViewById(R.id.btntru);
        btnNhan = findViewById(R.id.btnnhan);
        btnChia = findViewById(R.id.btnchia);
    }

    View.OnClickListener handleCong = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            double a = Double.parseDouble(edta.getText().toString());
            double b = Double.parseDouble(edtb.getText().toString());
            edtresult.setText(String.valueOf(a-b));
        }
    };

    View.OnClickListener handleTru = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            double a = Double.parseDouble(edta.getText().toString());
            double b = Double.parseDouble(edtb.getText().toString());
            edtresult.setText(String.valueOf(a-b));
        }
    };
    View.OnClickListener handleNhan = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            double a = Double.parseDouble(edta.getText().toString());
            double b = Double.parseDouble(edtb.getText().toString());
            edtresult.setText(String.valueOf(a * b));
        }
    };
    View.OnClickListener handleChia = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            double a = Double.parseDouble(edta.getText().toString());
            double b = Double.parseDouble(edtb.getText().toString());
            if (b == 0) {
                edtresult.setText(" ");
                Toast.makeText(MainActivity.this, "Không thể chia cho 0", Toast.LENGTH_SHORT).show();
            } else {
                edtresult.setText(String.valueOf(a / b));
            }
        }
    };
}