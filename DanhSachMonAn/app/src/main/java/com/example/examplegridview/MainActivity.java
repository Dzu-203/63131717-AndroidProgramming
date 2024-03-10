package com.example.examplegridview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    GridView gridDish;
    int[] listImg = {R.drawable.i1,R.drawable.i2,R.drawable.i3,R.drawable.i4,R.drawable.i5,R.drawable.i6,R.drawable.i7,R.drawable.i8};
    String[] listMon = {"Cơm gà","Cơm sườn","Sườn kho","Chả giò","Bì nướng","Chả nướng","Bánh giò","Bún chả"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridDish = findViewById(R.id.gridDish);
        ArrayList<Dish> listDish = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < listMon.length;i++){
            listDish.add(new Dish(listImg[i],listMon[i],random.nextInt(100001)+ 100000));
        }
        CustomAdapter adapter = new CustomAdapter(this,R.layout.custom_item,listDish);
        gridDish.setAdapter(adapter);
        gridDish.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,SubActivity.class);
                intent.putExtra("img",listDish.get(position).getImg());
                intent.putExtra("ten",listDish.get(position).getName());
                intent.putExtra("gia",listDish.get(position).getPrice());
                startActivity(intent);
            }
        });
    }
}