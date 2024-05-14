package ntu.mssv63131717.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ntu.mssv63131717.Adapters.LevelAdapter;
import ntu.mssv63131717.MainActivity;
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
        binding.backLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    ArrayList<Level> getData(){
        ArrayList<Level> listData = new ArrayList<>();
        listData.add(new Level("Level 1"));
        listData.add(new Level("Level 2"));
        listData.add(new Level("Level 3"));
        listData.add(new Level("Level 4"));
        listData.add(new Level("Level 5"));
        return listData;
    }
}