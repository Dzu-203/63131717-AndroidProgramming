package com.example.bt_gridview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
        ArrayList<Food> listData = new ArrayList<Food>();
        listData.add(new Food("ig","Bánh ngọt 1"));
        listData.add(new Food("ig","Bánh ngọt 2"));
        listData.add(new Food("ig","Bánh ngọt 3"));
        listData.add(new Food("ig","Bánh ngọt 4"));
        listData.add(new Food("ig","Bánh ngọt 5"));
        listData.add(new Food("ig","Bánh ngọt 6"));
        return listData;
    }
}