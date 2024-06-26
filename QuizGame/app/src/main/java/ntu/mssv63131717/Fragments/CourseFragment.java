package ntu.mssv63131717.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ntu.mssv63131717.Activities.LevelActivity;
import ntu.mssv63131717.Adapters.CourseAdapter;
import ntu.mssv63131717.Models.Course;
import ntu.mssv63131717.Models.DataLoadedCallbackCourse;
import ntu.mssv63131717.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseFragment newInstance(String param1, String param2) {
        CourseFragment fragment = new CourseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    ArrayList<Course> courses = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course,container,false);
        GridView gridCourse = view.findViewById(R.id.gridCourse);
        GetData(new DataLoadedCallbackCourse() {
            @Override
            public void onDataLoaded(ArrayList<Course> courses) {
                CourseAdapter adapter = new CourseAdapter((Activity) requireContext(),R.layout.item_course,courses);
                gridCourse.setAdapter(adapter);
                gridCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(requireContext(), LevelActivity.class);
                        intent.putExtra("course",courses.get(position).getNameCourse());
                        startActivity(intent);
                    }
                });
            }
        });
        return view;
    }
    public void GetData(final DataLoadedCallbackCourse callback){
        ArrayList<Course> list = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("courses");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String courseName = dataSnapshot.child("name").getValue(String.class);
                    String drawableName = courseName.toLowerCase();
                    int drawableId = getResources().getIdentifier(drawableName, "drawable", getActivity().getPackageName());
                    if (drawableId == 0) {
                        drawableId = R.drawable.android;
                    }
                    list.add(new Course(drawableId, courseName));

                }
                callback.onDataLoaded(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Lỗi lấy khóa học", Toast.LENGTH_SHORT).show();
            }
        });
    }

}