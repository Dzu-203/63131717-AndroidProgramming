package ntu.mssv63131717.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ntu.mssv63131717.Adapters.LevelAdapter;
import ntu.mssv63131717.MainActivity;
import ntu.mssv63131717.Models.DataLoadedCallbackLevel;
import ntu.mssv63131717.Models.Level;
import ntu.mssv63131717.databinding.ActivityLevelBinding;

public class LevelActivity extends AppCompatActivity {
    ActivityLevelBinding binding;
    ArrayList<Level> listLevel;
    LevelAdapter levelAdapter;
    String course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLevelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        course = getIntent().getStringExtra("course");

        // Khởi tạo danh sách listLevel
        listLevel = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerLevel.setLayoutManager(layoutManager);
        levelAdapter = new LevelAdapter(this, listLevel, course);
        binding.recyclerLevel.setAdapter(levelAdapter);

        // Gọi phương thức GetData để lấy dữ liệu levels
        GetData(new DataLoadedCallbackLevel() {
            @Override
            public void onDataLoaded(ArrayList<Level> data) {
                listLevel.addAll(data);
                levelAdapter.notifyDataSetChanged();

                // Cập nhật tổng số level và số lượng câu hỏi
                binding.totalLevel.setText(String.valueOf(listLevel.size()));
                binding.totalQues.setText(String.valueOf(listLevel.size() * 5));
            }
        });

        binding.backLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void GetData(final DataLoadedCallbackLevel callback) {
        ArrayList<Level> list = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("courses");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String courseName = dataSnapshot.child("name").getValue(String.class);
                    if (courseName != null && courseName.equals(course)) {
                        DataSnapshot levelsSnapshot = dataSnapshot.child("levels");
                        for (DataSnapshot levelSnapshot : levelsSnapshot.getChildren()) {
                            String levelName = levelSnapshot.getKey();
                            int levelNumber = Integer.parseInt(levelName);
                            list.add(new Level("Level " + (levelNumber + 1)));
                        }
                    }
                }
                callback.onDataLoaded(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý khi có lỗi
            }
        });
    }
}
