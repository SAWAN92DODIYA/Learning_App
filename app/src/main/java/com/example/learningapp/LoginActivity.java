package com.example.learningapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {


    private EditText text_email, text_password;
    private ProgressBar progressBar;
    private static final String SHARED_PREFRENCE_NAME = "mypref";

    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";


    Button btnLogin;
    TextView textRegister;
    SharedPreferences pref;
    SharedPreferences pref2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        new NetworkChangesObserver(this).register();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("login", MODE_PRIVATE);
        pref2 = getSharedPreferences(SHARED_PREFRENCE_NAME, MODE_PRIVATE);
        btnLogin = findViewById(R.id.button_login);
        progressBar = findViewById(R.id.progressBar_login);

        text_email = findViewById(R.id.editText_login_email);
        text_password = findViewById(R.id.editText_login_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // code verification

                // get value by edit_text
                String email_text = text_email.getText().toString();
                String password_text = text_password.getText().toString();


                FirebaseAuth auth = FirebaseAuth.getInstance();


                //get value by sharedPreferences
                String email = pref2.getString(KEY_EMAIL, "virim@1234.com");
                String password = pref2.getString(KEY_PASSWORD, "virim@1234");

                SharedPreferences.Editor editor = pref.edit();

                if (TextUtils.isEmpty(email_text)) {
                    Toast.makeText(LoginActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    text_email.setError("Email is required");
                    text_email.requestFocus();
                } else if (TextUtils.isEmpty(password_text)) {
                    Toast.makeText(LoginActivity.this, "Please enter your Password", Toast.LENGTH_SHORT).show();
                    text_password.setError("Email is required");
                    text_password.requestFocus();

                }
                else {


                    auth.signInWithEmailAndPassword(email_text,password_text).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                                editor.putBoolean("flag", true);
                                editor.apply();
                                Intent iHome = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(iHome);
                                finish();
                            }
                            else {
                                    Toast.makeText(LoginActivity.this, "invalid username and password", Toast.LENGTH_SHORT).show();}

                                 }
                    });

                }



//                else if (email.equals(email_text) && password.equals(password_text) && !email.equals("")) {
//                    editor.putBoolean("flag", true);
//                    editor.apply();
//
//                    Intent iHome = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(iHome);
//                    finish();
//                } else {
//                    Toast.makeText(LoginActivity.this, "invalid username and password", Toast.LENGTH_SHORT).show();
//                }
            }
        });


        textRegister = findViewById(R.id.textView_register);
        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(irRegister);
            }
        });



    }

//    private void loginUser(String textEmail, String textPassword) {
//
//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        auth.signInWithEmailAndPassword(textEmail, textPassword).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//                    Toast.makeText(LoginActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
//                    Intent iHome = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(iHome);
//                    finish();
//                }
//            }
//        });
//
//
//    }






}