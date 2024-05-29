package com.ntu.bottomnavigation;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{
    BottomNavigationView bottomNavigationView;
    FrameLayout fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.navbottom);
        fragment = findViewById(R.id.framely);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemID = menuItem.getItemId();
                if(itemID == R.id.home){
                    loadFragment(new HomeFragment(),false);
                }else if (itemID == R.id.profile){
                    loadFragment(new ProfileFragment(),false);
                }else if (itemID == R.id.setting) {
                    loadFragment(new SettingFragment(), false);
                }

                return true;
            }

        });
        loadFragment(new HomeFragment(),true);
    }
    private void loadFragment(Fragment fragment,boolean isApp){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isApp){
            fragmentTransaction.add(R.id.framely,fragment);
        }else {
            fragmentTransaction.add(R.id.framely,fragment);
        }
        fragmentTransaction.replace(R.id.framely,fragment);
        fragmentTransaction.commit();
    }
}