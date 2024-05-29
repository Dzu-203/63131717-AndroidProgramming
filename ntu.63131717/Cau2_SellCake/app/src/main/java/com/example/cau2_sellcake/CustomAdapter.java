package com.example.cau2_sellcake;

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

public class CustomAdapter extends ArrayAdapter<Cake> {
    Activity context;
    int id_layout;
    ArrayList<Cake> myList;
    public CustomAdapter(Activity context, int id_layout, ArrayList<Cake> myList) {
        super(context,id_layout,myList);
        this.context = context;
        this.id_layout = id_layout;
        this.myList = myList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(id_layout,null);
        Cake listCake = myList.get(position);
        //ánh xạ
        ImageView imgCake = convertView.findViewById(R.id.imgCake);
        imgCake.setImageResource(listCake.getImg());
        TextView nameCake = convertView.findViewById(R.id.txtNameCake);
        nameCake.setText(listCake.getName());
        TextView coinCake = convertView.findViewById(R.id.txtCoinCake);
        coinCake.setText(String.valueOf(listCake.getCoin() + "đ"));
        return convertView;
    }
}
