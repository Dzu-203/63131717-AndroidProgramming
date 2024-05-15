package ntu.mssv63131717.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ntu.mssv63131717.Activities.QuestionActivity;
import ntu.mssv63131717.Models.Level;
import ntu.mssv63131717.R;
import ntu.mssv63131717.databinding.ItemLevelBinding;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.viewHolder> {
    Context context;
    ArrayList<Level> list;
    String course;


    public LevelAdapter(Context context, ArrayList<Level> list,String course) {
        this.context = context;
        this.list = list;
        this.course = course;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_level,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Level level = list.get(position);
        holder.binding.setLevel.setText(level.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, QuestionActivity.class);
                intent.putExtra("level",level.getName());
                intent.putExtra("course",course);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ItemLevelBinding binding;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemLevelBinding.bind(itemView);


        }


    }
}
