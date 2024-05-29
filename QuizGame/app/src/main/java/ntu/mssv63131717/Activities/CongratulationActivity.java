package ntu.mssv63131717.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ntu.mssv63131717.MainActivity;
import ntu.mssv63131717.R;
import ntu.mssv63131717.databinding.ActivityCongratulationBinding;
import ntu.mssv63131717.databinding.ActivityQuestionBinding;

public class CongratulationActivity extends AppCompatActivity {

    ActivityCongratulationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCongratulationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int score = getIntent().getIntExtra("score",0);
        int total = getIntent().getIntExtra("total",0);

        binding.all.setText(String.valueOf(total));
        binding.correct.setText(String.valueOf(score));
        binding.wrong.setText(String.valueOf(total-score));
        binding.reloadRong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CongratulationActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        binding.exitRong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishSystem();
            }
        });
    }

    private void finishSystem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CongratulationActivity.this);
        builder.setTitle("Exit");
        builder.setMessage("Bạn có chắc chắn muốn thoát ứng dụng ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }
}