package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rc;
    RecyclerAdapter recyclerAdapter;
    ArrayList<Food> listFood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rc = findViewById(R.id.rc);
        listFood = getData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rc.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(this,listFood);
        rc.setAdapter(recyclerAdapter);
    }
    ArrayList<Food> getData(){
        ArrayList<Food> listData = new ArrayList<>();
        listData.add(new Food("cake3","Bánh ngọt 1"));
        listData.add(new Food("cake4","Bánh ngọt 2"));
        listData.add(new Food("cake5","Bánh ngọt 3"));
        listData.add(new Food("cake6","Bánh ngọt 4"));
        listData.add(new Food("cake7","Bánh ngọt 5"));
        listData.add(new Food("cake8","Bánh ngọt 6"));
        return listData;
    }
}