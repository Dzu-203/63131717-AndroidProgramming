package ntu.mssv63131717;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

import ntu.mssv63131717.Activities.LevelActivity;
import ntu.mssv63131717.Adapters.CourseAdapter;
import ntu.mssv63131717.Models.Course;
import ntu.mssv63131717.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<Course> courses = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        courses = GetData();
        CourseAdapter adapter = new CourseAdapter(this,R.layout.item_course,courses);
        binding.gridCourse.setAdapter(adapter);
        binding.gridCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,LevelActivity.class);
                intent.putExtra("course",courses.get(position).getNameCourse());
                startActivity(intent);
            }
        });

    }

    public ArrayList<Course> GetData(){
        ArrayList<Course> lists = new ArrayList<>();
        lists.add(new Course(R.drawable.html,"HTML"));
        lists.add(new Course(R.drawable.js,"JavaScript"));
        lists.add(new Course(R.drawable.python,"Python"));
        lists.add(new Course(R.drawable.android,"Android"));
        return lists;
    }

}