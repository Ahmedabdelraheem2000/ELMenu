package com.halawy.elmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.halawy.elmenu.Fragment_Requirment.fragment_Requirment;
import com.halawy.elmenu.Fragment_Home.fragment_Home;
import com.halawy.elmenu.Fragment.fragment_setting;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomnavigationview;
    private Fragment select_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomnavigationview = findViewById(R.id.bottomnavigationview);
        bottomnavigationview.setOnNavigationItemSelectedListener(onnavigationitemselectlistener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new fragment_Home()).commit();



    }




    private BottomNavigationView.OnNavigationItemSelectedListener onnavigationitemselectlistener = new BottomNavigationView.
            OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            select_fragment = null;
            switch (item.getItemId()) {
                case R.id.Home:
                    select_fragment = new fragment_Home();
                    break;

                case R.id.account:
                    select_fragment = new fragment_setting();
                    break;
                case R.id.Card:
                    select_fragment = new fragment_Requirment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, select_fragment).commit();
            return true;

        }
    };


    @Override
    public void onBackPressed() {

        if (bottomnavigationview.getSelectedItemId() == R.id.Home) {


            finishAffinity();

        } else {
            bottomnavigationview.setSelectedItemId(R.id.Home);
        }

    }

}

