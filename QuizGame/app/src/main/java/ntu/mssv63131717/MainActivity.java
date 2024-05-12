package ntu.mssv63131717;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import ntu.mssv63131717.Activities.LevelActivity;

public class MainActivity extends AppCompatActivity {
    CardView html,js,py,android;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetInit();
        SetUpOnclick();
    }

    private void GetInit() {
        html = findViewById(R.id.html);
        js = findViewById(R.id.js);
        py = findViewById(R.id.py);
        android = findViewById(R.id.android);
    }
    private void SetUpOnclick(){
        View.OnClickListener listener = v -> {
            Intent intent = new Intent(MainActivity.this, LevelActivity.class);
            startActivity(intent);
        };
        html.setOnClickListener(listener);
        js.setOnClickListener(listener);
        py.setOnClickListener(listener);
        android.setOnClickListener(listener);
    }

}