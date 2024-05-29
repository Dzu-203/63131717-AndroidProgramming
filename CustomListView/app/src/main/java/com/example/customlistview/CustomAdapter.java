package com.example.customlistview;

import android.annotation.SuppressLint;
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

public class CustomAdapter extends ArrayAdapter<Country> {
    Activity context;
    int id_layout;
    ArrayList<Country> listCountry;
    public CustomAdapter(Activity context, int id_layout,ArrayList<Country> listCountry) {
        super(context, id_layout,listCountry);
        this.context = context;
        this.id_layout = id_layout;
        this.listCountry = listCountry;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        convertView = layoutInflater.inflate(id_layout, null);
        Country country = listCountry.get(position);
        ImageView img = convertView.findViewById(R.id.imageCT);
        img.setImageResource(country.getAnhCountry());
        TextView name = convertView.findViewById(R.id.nameCT);
        name.setText(country.getNameCountry());
        TextView text = convertView.findViewById(R.id.textCT);
        text.setText(country.getTextCountry());
        return convertView;
    }
}
