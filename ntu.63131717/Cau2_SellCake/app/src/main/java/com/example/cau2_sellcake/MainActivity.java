package com.example.cau2_sellcake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageButton btnSearch;
    TextInputEditText inputSearch;
    GridView gv;
    int[] listImgCake = {R.drawable.cake1,R.drawable.cake2,R.drawable.cake3,R.drawable.cake4,R.drawable.cake5,R.drawable.cake6,R.drawable.cake7,R.drawable.cake8,R.drawable.cake9,R.drawable.cake10,R.drawable.cake11,R.drawable.cake12};
    String[] listNameCake = {"Vanilla Cheese Cake","Croffle Chocolate Đen","Flan Cake","Bánh Mỳ Kẹp Xá Xíu","Salted Egg Pastry","New York Rolls Phô Mai","Crepe Chocolate","Apple Danish","Feuillete Au Chocolat","Snow chocolate","Layer latte cake","Bánh Cuộn Chocolate"};
    int[] listCoinCake = {295_000,23_000,25_000,28_000,35_000,15_000,35_000,23_000,20_000,320_000,280_000,15_000};
    ArrayList<Cake> listCake;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addInit();
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("img",listCake.get(position).getImg());
                intent.putExtra("name",listCake.get(position).getName());
                intent.putExtra("coin",listCake.get(position).getCoin());
                startActivity(intent);
            }
        });
    }

    private void addInit() {
        btnSearch = findViewById(R.id.btnSearch);
        inputSearch = findViewById(R.id.inputSearch);
        gv = findViewById(R.id.gv);
        listCake = new ArrayList<>();
        for (int i = 0; i < listImgCake.length; i++){
            listCake.add(new Cake(listImgCake[i],listNameCake[i],listCoinCake[i]));
        }
        customAdapter = new CustomAdapter(this,R.layout.custom_item,listCake);
        gv.setAdapter(customAdapter);
    }
}