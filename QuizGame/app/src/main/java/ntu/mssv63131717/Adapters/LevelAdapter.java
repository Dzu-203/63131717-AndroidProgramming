package ntu.mssv63131717.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ntu.mssv63131717.Models.Level;
import ntu.mssv63131717.R;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.viewHolder> {
    Context context;
    ArrayList<Level> list;

    public LevelAdapter(Context context, ArrayList<Level> list) {
        this.context = context;
        this.list = list;
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
        holder.name.setText(level.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.set_level);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int clicked = getAdapterPosition();
            Level choose = list.get(clicked);
            String string = "Bạn vừa click vào " + choose.getName();
            Toast.makeText(v.getContext(),string,Toast.LENGTH_SHORT).show();
        }
    }
}
