package com.example.bt_gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        holder.txtText.setText(itemFood.getContextFood());
        String packageName = holder.itemView.getContext().getPackageName();
        String tenFileAnh = itemFood.getImgFood();
        int idIMG = holder.itemView.getResources().getIdentifier(tenFileAnh,"mipmap",packageName);
        holder.imgView.setImageResource(idIMG);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemLandHoler extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtText;
        ImageView imgView;

        public ItemLandHoler(@NonNull View itemView) {
            super(itemView);
            this.txtText = itemView.findViewById(R.id.ct_text);
            this.imgView = itemView.findViewById(R.id.ct_img);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clicked = getAdapterPosition();
            Food choose = list.get(clicked);
            String string = "Bạn vừa click vào " + choose.getContextFood();
            Toast.makeText(v.getContext(),string,Toast.LENGTH_SHORT).show();
        }
    }
}
