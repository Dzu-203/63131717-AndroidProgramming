package com.example.exampletabhost;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText eda,edb;
    Button btnCong;
    TabHost tabHost;
    ListView lv;
    ArrayList<String> myList;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvent();

    }

    private void addEvent() {
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(eda.getText().toString());
                int b = Integer.parseInt(edb.getText().toString());
                int kq = a + b;
                myList.add(String.format("%s + %s = %s",a,b,kq));
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void addControl() {
        // xu li tab
        tabHost = findViewById(R.id.tabhost);
        tabHost.setup();
        TabHost.TabSpec spec1,spec2;
        // thêm tab 1
        spec1 = tabHost.newTabSpec("t1");
        spec1.setIndicator("",getDrawable(R.drawable.cong));
        spec1.setContent(R.id.tabcong);
        tabHost.addTab(spec1);
        // thêm tab 2
        spec2 = tabHost.newTabSpec("t2");
        spec2.setIndicator("",getDrawable(R.drawable.lichsu));
        spec2.setContent(R.id.tablichsu);
        tabHost.addTab(spec2);
        eda = findViewById(R.id.eda);
        edb = findViewById(R.id.edb);
        btnCong = findViewById(R.id.btncong);
        myList = new ArrayList<>();
        lv = findViewById(R.id.lv);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,myList);
        lv.setAdapter(adapter);



    }
}