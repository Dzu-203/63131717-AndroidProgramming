package com.example.cau2_sellcake;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    ImageButton btnSearch;
    TextInputEditText inputSearch;
    GridView gv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}