package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemLandHoler> {
    Context context;
    ArrayList<Food> list;

    public RecyclerAdapter(Context context, ArrayList<Food> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ItemLandHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View vItem = layoutInflater.inflate(R.layout.custom_item,parent,false);
        ItemLandHoler itemLandHoler = new ItemLandHoler(vItem);
        return itemLandHoler;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLandHoler holder, int position) {
        Food itemFood = list.get(position);
        holder.txt_text.setText(itemFood.getContextFood());
        String packageName = holder.itemView.getContext().getPackageName();
        String tenFileAnh = itemFood.getImgFood();
        int idIMG = holder.itemView.getResources().getIdentifier(tenFileAnh,"mipmap",packageName);
        holder.txt_img.setImageResource(idIMG);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemLandHoler extends RecyclerView.ViewHolder{
        TextView txt_text;
        ImageView txt_img;

        public ItemLandHoler(@NonNull View itemView) {
            super(itemView);
            this.txt_text = itemView.findViewById(R.id.ct_text);
            this.txt_img = itemView.findViewById(R.id.ct_img);
        }
    }

}
