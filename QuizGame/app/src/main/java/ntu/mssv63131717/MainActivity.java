package ntu.mssv63131717;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import ntu.mssv63131717.Activities.LevelActivity;
import ntu.mssv63131717.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SetUpOnclick();
    }
    private void SetUpOnclick(){
        View.OnClickListener listener = v -> {
            Intent intent = new Intent(MainActivity.this, LevelActivity.class);
            startActivity(intent);
        };
        binding.html.setOnClickListener(listener);
        binding.js.setOnClickListener(listener);
        binding.py.setOnClickListener(listener);
        binding.android.setOnClickListener(listener);
    }

}