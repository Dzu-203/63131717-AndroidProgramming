package ntu.mssv63131717.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ntu.mssv63131717.Adapters.LevelAdapter;
import ntu.mssv63131717.Models.Level;
import ntu.mssv63131717.R;

public class LevelActivity extends AppCompatActivity {
    RecyclerView rc_level;
    LevelAdapter levelAdapter;
    ArrayList<Level> listLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        listLevel = getData();
        rc_level = findViewById(R.id.recyclerLevel);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rc_level.setLayoutManager(layoutManager);
        levelAdapter = new LevelAdapter(this,listLevel);
        rc_level.setAdapter(levelAdapter);

    }
    ArrayList<Level> getData(){
        ArrayList<Level> listData = new ArrayList<>();
        listData.add(new Level("Level 1"));
        listData.add(new Level("Level 2"));
        listData.add(new Level("Level 3"));
        listData.add(new Level("Level 4"));
        listData.add(new Level("Level 5"));
        listData.add(new Level("Level 6"));
        listData.add(new Level("Level 7"));
        listData.add(new Level("Level 8"));
        listData.add(new Level("Level 9"));
        listData.add(new Level("Level 10"));
        return listData;
    }
}