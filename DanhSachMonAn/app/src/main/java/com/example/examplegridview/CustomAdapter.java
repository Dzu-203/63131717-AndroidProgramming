package com.example.examplegridview;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Dish> {
    Activity context;
    int id_layout;
    ArrayList<Dish> mylist;

    public CustomAdapter(Activity context, int id_layout, ArrayList<Dish> mylist) {
        super(context, id_layout,mylist);
        this.context = context;
        this.id_layout = id_layout;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(id_layout,null);
        Dish dish = mylist.get(position);
        //ánh xa;
        ImageView anhMon = convertView.findViewById(R.id.imgmon);
        anhMon.setImageResource(dish.getImg());
        TextView tenMon = convertView.findViewById(R.id.txttenmon);
        tenMon.setText(dish.getName());
        TextView giaMon = convertView.findViewById(R.id.txtgia);
        giaMon.setText(String.valueOf("Giá : " + dish.getPrice()));
        return convertView;
    }
}
