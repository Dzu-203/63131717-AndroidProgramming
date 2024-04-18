package com.example.th_recycle2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rc;
    ExamViewHolder examViewHolder;
    ArrayList<Exam> Exams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rc = findViewById(R.id.rc);
        Exams = getData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rc.setLayoutManager(layoutManager);
        examViewHolder = new ExamViewHolder(this,Exams);
        rc.setAdapter(examViewHolder);
    }
    ArrayList<Exam> getData(){
        ArrayList<Exam> listData = new ArrayList<Exam>();
        listData.add(new Exam("First Exam","May 23,2015","Best Luck"));
        listData.add(new Exam("Second Exam","May 26,2016","Best Luck"));
        listData.add(new Exam("My test Exam","May 3,2018","Best Luck"));
        return listData;
    }

}