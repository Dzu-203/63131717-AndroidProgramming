package ntu.mssv63131717.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import ntu.mssv63131717.Models.Course;
import ntu.mssv63131717.R;
import ntu.mssv63131717.databinding.ItemCourseBinding;
import ntu.mssv63131717.databinding.ItemLevelBinding;

public class CourseAdapter extends ArrayAdapter<Course> {
    Activity context;
    int id_layout;
    ArrayList<Course> list;

    public CourseAdapter( Activity context, int id_layout, ArrayList<Course> list) {
        super(context, id_layout,list);
        this.context = context;
        this.id_layout = id_layout;
        this.list = list;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(id_layout,null);
        Course course = list.get(position);
        ImageView img = convertView.findViewById(R.id.imgCourse);
        TextView name = convertView.findViewById(R.id.nameCourse);
        img.setImageResource(course.getImgCourse());
        name.setText(course.getNameCourse());
        return convertView;
    }
}
