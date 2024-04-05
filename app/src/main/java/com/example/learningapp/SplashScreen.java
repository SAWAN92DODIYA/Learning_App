package com.example.learningapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // network info
        new NetworkChangesObserver(this).register();




        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {



                SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);
                  Boolean check =  pref.getBoolean("flag",false);
                Intent iHome;
                  if(check){

                       iHome = new Intent(SplashScreen.this,MainActivity.class);


                  }else {

                      iHome = new Intent(SplashScreen.this, LoginActivity.class);



                  }
                startActivity(iHome);
                    finish();


            }
        },1000);



    }
}