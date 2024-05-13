package ntu.mssv63131717.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ntu.mssv63131717.Adapters.LevelAdapter;
import ntu.mssv63131717.Models.Level;
import ntu.mssv63131717.R;
import ntu.mssv63131717.databinding.ActivityLevelBinding;

public class LevelActivity extends AppCompatActivity {
    ActivityLevelBinding binding;
    ArrayList<Level> listLevel;
    LevelAdapter levelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLevelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        listLevel = getData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerLevel.setLayoutManager(layoutManager);
        levelAdapter = new LevelAdapter(this,listLevel);
        binding.recyclerLevel.setAdapter(levelAdapter);

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