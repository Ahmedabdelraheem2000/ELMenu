package com.halawy.elmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences shared=getSharedPreferences("savefile",Context.MODE_PRIVATE);
        String email=shared.getString("email","");
        String password=shared.getString("password","");



            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(splash_screen.this,signin.class));
                }
            },3500);


    }
}