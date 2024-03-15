package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int[] listImg = {R.drawable.vn,R.drawable.vn,R.drawable.vn};
    String[] listName = {"Nha Trang","Ho Chi Minh","Ha Noi"};
    String[] listText = {"Đây là text","Đây là text","Đây là text"};
    ArrayList<Country> listCountry;
    CustomAdapter adapter;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv);
        addInit();
        for (int i = 0; i < listName.length;i++){
            listCountry.add(new Country(listImg[i],listName[i],listText[i]));
        }
    }

    private void addInit() {
        listCountry = new ArrayList<>();
        adapter = new CustomAdapter(this,R.layout.custom_layout,listCountry);
        lv.setAdapter(adapter);
    }

}