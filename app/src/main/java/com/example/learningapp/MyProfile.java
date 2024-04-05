package com.example.learningapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.PrecomputedText;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MyProfile extends AppCompatActivity {

    private  static final  String  SHARED_PREFRENCE_NAME = "mypref";
    private  static final String  KEY_NAME = "full_name";
    private static final  String KEY_EMAIL = "email";
    private  static final  String KEY_DOB  = "dob";
    private static final  String KEY_GENDER = "gender";

    private  static final String  KEY_MOBILE = "mobile";



    private ImageView img_logo ;

    private EditText text_name, text_email, text_date, text_gender, text_mobile;

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        // network are avelaibel  or not
        new NetworkChangesObserver(this).register();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);


        pref  = getSharedPreferences(SHARED_PREFRENCE_NAME,MODE_PRIVATE);
        text_name = findViewById(R.id.editText_myProfile_name);
        text_email = findViewById(R.id.editText_myProfile_email);
        text_date = findViewById(R.id.editText_myProfile_dob);
        text_gender = findViewById(R.id.editText_myProfile_gender);
        text_mobile = findViewById(R.id.editText_myProfile_mobile);

        text_name.setText("Name:- " + pref.getString(KEY_NAME,"virim"));
        text_email.setText("Email ID:- "+pref.getString(KEY_EMAIL,"virim@gmail.com"));
        text_date.setText("BirthDate:- "+pref.getString(KEY_DOB,"18/03/2024"));
        text_gender.setText("Gender:- "+pref.getString(KEY_GENDER,"Male"));
        text_mobile.setText("PhoneNumber:- "+pref.getString(KEY_MOBILE,"9765486656"));
        // back to home ...
        img_logo = findViewById(R.id.back_to_home);
        img_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iHome = new Intent(MyProfile.this,MainActivity.class);
                startActivity(iHome);
                finish();
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

    }
}