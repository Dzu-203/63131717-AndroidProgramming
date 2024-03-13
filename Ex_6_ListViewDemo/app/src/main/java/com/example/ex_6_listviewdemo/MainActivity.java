package com.example.ex_6_listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<String> Courses;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lv);
        Courses = new ArrayList<>();
        Courses.add("Java");
        Courses.add("JavaScript");
        Courses.add("Python");
        Courses.add("C#");
        Courses.add("Ruby");
        Courses.add("ReacJS");
        Courses.add("NodeJS");
        adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Courses);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = adapter.getItem(position);
                Toast.makeText(MainActivity.this,String.format("Đây là khóa học %s",value),Toast.LENGTH_SHORT).show();

            }
        });
    }

}