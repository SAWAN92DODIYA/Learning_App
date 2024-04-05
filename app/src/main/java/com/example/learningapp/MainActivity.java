 package com.example.learningapp;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

 public class MainActivity extends AppCompatActivity {

    private TextView textView_profile ;
    private FloatingActionButton flatingButtonMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
//        setContentView(R.layout.activity_my_profile);

        Button btn = findViewById(R.id.button_logOut);

        textView_profile = findViewById(R.id.textView_profile);
        textView_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iProfile = new Intent(MainActivity.this,MyProfile.class);
                startActivity(iProfile);
                getOnBackPressedDispatcher().onBackPressed();

            }
        });



        flatingButtonMain = findViewById(R.id.floating_button_main);
        flatingButtonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iDevice = new Intent(MainActivity.this,DeviceDetails.class);
                startActivity(iDevice);

            }
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);
                SharedPreferences.Editor  editor = pref.edit();
                editor.putBoolean("flag",false);
                editor.apply();

                Intent iLogin  = new Intent(MainActivity.this,LoginActivity.class);
                finish();
                startActivity(iLogin);
            }
        });


    }


}