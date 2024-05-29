package com.example.th_recycle2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExamViewHolder extends RecyclerView.Adapter<ExamViewHolder.ItemHolder>{

    Context context;
    ArrayList<Exam> list;

    public ExamViewHolder(Context context, ArrayList<Exam> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View vItem = layoutInflater.inflate(R.layout.exam_item,parent,false);
        ItemHolder itemLandHoler = new ItemHolder(vItem);
        return itemLandHoler;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Exam item = list.get(position);
        holder.examName.setText(item.getName());
        holder.examDate.setText(item.getDate());
        holder.examMessage.setText(item.getMessage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView examName,examDate,examMessage;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            this.examName = itemView.findViewById(R.id.examName);
            this.examDate = itemView.findViewById(R.id.examDate);
            this.examMessage = itemView.findViewById(R.id.examMessage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clicked = getAdapterPosition();
            Exam choose = list.get(clicked);
            String string = "Bạn vừa click vào " + choose.getName();
            Toast.makeText(v.getContext(),string,Toast.LENGTH_SHORT).show();
        }
    }
}
