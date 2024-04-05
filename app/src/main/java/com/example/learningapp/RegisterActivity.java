package com.example.learningapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Cleaner;
import java.sql.Date;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextRegisterFullName, editTextRegisterEmail, editTextRegisterDoB , editTextRegisterMobile, editTextRegisterPwd,editTextRegisterConfirmPwd;
    private ProgressBar progressBar;
    private RadioGroup radioGroupRegisterGender ;
    private RadioButton radioButtonRegisterGenderselect;

    private  static final  String  SHARED_PREFRENCE_NAME = "mypref";
    private  static final String  KEY_NAME = "full_name";
    private static final  String KEY_EMAIL = "email";
    private  static final  String KEY_DOB  = "dob";
    private static final  String KEY_GENDER = "gender";

    private  static final String  KEY_MOBILE = "mobile";
    private  static final String KEY_PASSWORD = "password";
    private  static final String KEY_CONFIRM_PASSWORD = "con_password";

    private DatePickerDialog piker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        //   newtwork info
        new NetworkChangesObserver(this).register();

        Context context = RegisterActivity.this;
        Log.d(getClass().getSimpleName(), "onCreate: ");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);


        SharedPreferences pref = getSharedPreferences(SHARED_PREFRENCE_NAME,MODE_PRIVATE);


        Toast.makeText(RegisterActivity.this,"You can register now ",Toast.LENGTH_LONG ).show();
        editTextRegisterFullName  = findViewById(R.id.edit_register_full_name);
        editTextRegisterEmail = findViewById(R.id.edit_register_email);
        editTextRegisterDoB = findViewById(R.id.edit_register_date_of_birth);
        radioGroupRegisterGender = findViewById(R.id.radio_group_register_gender);
        editTextRegisterMobile = findViewById(R.id.edit_register_mobile);
        editTextRegisterPwd = findViewById(R.id.edit_register_password);
        editTextRegisterConfirmPwd = findViewById(R.id.edit_register_confirm_password);

        radioGroupRegisterGender.clearCheck();

        editTextRegisterDoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar  calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int  month  = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                piker  = new DatePickerDialog(RegisterActivity.this,new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year,int month , int day)
                    {
                            editTextRegisterDoB.setText(day + "/"+(month+1) +"/" + year);
                    }
                },year,month,day);
                piker.show();
            }
        });





        Button buttonRegister = findViewById(R.id.button_register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                String textFullName = editTextRegisterFullName.getText().toString();
                String textEmail = editTextRegisterEmail.getText().toString();
                String textDoB = editTextRegisterDoB.getText().toString();
                String  textMobile = editTextRegisterMobile.getText().toString();
                String  textPwd = editTextRegisterPwd.getText().toString();
                String textConfirmPwd = editTextRegisterConfirmPwd.getText().toString();
                String textGender ;

                //valid mobile
                String mobileRegex = "[6_9][0-9]{9}";
                Matcher mobileMatcher ;
                Pattern mobilePattern = Pattern.compile(mobileRegex);
                mobileMatcher = mobilePattern.matcher(textMobile);

                if(TextUtils.isEmpty(textFullName))
                {
                    Toast.makeText(RegisterActivity.this,"Please Enter the Your full Name",Toast.LENGTH_LONG).show();
                    editTextRegisterFullName.setError("Full Name is Required");
                    editTextRegisterFullName.requestFocus();
                }
                else if(TextUtils.isEmpty(textEmail))
                {
                    Toast.makeText(RegisterActivity.this, "Please Enter the Your Email", Toast.LENGTH_SHORT).show();
                    editTextRegisterEmail.setError("Email is Required");
                    editTextRegisterEmail.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches())
                {
                    Toast.makeText(RegisterActivity.this, "Please Re-Enter the Your Email", Toast.LENGTH_SHORT).show();
                    editTextRegisterEmail.setError("Email is Required");
                    editTextRegisterEmail.requestFocus();
                }
                else if(TextUtils.isEmpty((textDoB)))
                {
                    Toast.makeText(RegisterActivity.this, "Please Enter the Date Of Birth", Toast.LENGTH_SHORT).show();
                    editTextRegisterDoB.setError("Date of Birth is Required");
                    editTextRegisterDoB.requestFocus();
                }
                else if(radioGroupRegisterGender.getCheckedRadioButtonId()== -1)
                {
                    Toast.makeText(RegisterActivity.this,"Please Select Gender",Toast.LENGTH_SHORT).show();
                    radioButtonRegisterGenderselect.setError("Gender is Required");
                    radioButtonRegisterGenderselect.requestFocus();
                }
                else if(TextUtils.isEmpty((textMobile)))
                {
                    Toast.makeText(RegisterActivity.this, "Please Enter your Mobile No.", Toast.LENGTH_SHORT).show();
                    editTextRegisterMobile.setError(" Mobile No.  is Required");
                    editTextRegisterMobile.requestFocus();
                }
//                else if (!mobileMatcher.find()) {
//                    Toast.makeText(RegisterActivity.this, "Please Re-Enter your Mobile No.", Toast.LENGTH_SHORT).show();
//                    editTextRegisterMobile.setError(" Mobile No.  is Required");
//                    editTextRegisterMobile.requestFocus();
//
//                }
                else if(textMobile.length() != 10)
                {
                    Toast.makeText(RegisterActivity.this, "Please Re-Enter your Mobile No.", Toast.LENGTH_SHORT).show();
                    editTextRegisterMobile.setError(" Mobile No.  is Required");
                    editTextRegisterMobile.requestFocus();

                }
                else if(TextUtils.isEmpty((textDoB)))
                {
                    Toast.makeText(RegisterActivity.this, "Please Enter the Date Of Birth", Toast.LENGTH_SHORT).show();
                    editTextRegisterDoB.setError("Date of Birth is Required");
                    editTextRegisterDoB.requestFocus();
                }
                else if (TextUtils.isEmpty(textPwd)){

                    Toast.makeText(RegisterActivity.this,"Please Enter the Password",Toast.LENGTH_SHORT).show();
                    editTextRegisterPwd.setError("Password is Required");
                    editTextRegisterPwd.requestFocus();
                } else if (textPwd.length()<6) {
                    Toast.makeText(RegisterActivity.this,"Password should be at least 6 digit",Toast.LENGTH_SHORT).show();
                    editTextRegisterPwd.setError("Password too weak");
                    editTextRegisterPwd.requestFocus();
                    
                } else if (TextUtils.isEmpty(textConfirmPwd))
                {
                    Toast.makeText(RegisterActivity.this, "Please Enter the Confirm-Password", Toast.LENGTH_SHORT).show();
                    editTextRegisterConfirmPwd.setError("Password Confirmation is Required");
                    editTextRegisterConfirmPwd.requestFocus();
                } else if (!textPwd.equals(textConfirmPwd)) {
                    Toast.makeText(RegisterActivity.this,"Enter same same Password",Toast.LENGTH_SHORT).show();
                    editTextRegisterConfirmPwd.setError("Password Confirmation is Required");
                    editTextRegisterConfirmPwd.requestFocus();




                    editTextRegisterConfirmPwd.clearComposingText();
                    editTextRegisterConfirmPwd.clearComposingText();
                    
                }
                else{
//                    textGender = radioButtonRegisterGenderselect.getText().toString();
//                    progressBar.setVisibility(View.VISIBLE);

                    registerUser(textFullName,textEmail,textDoB,textMobile,textPwd,textConfirmPwd);

                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString(KEY_NAME,textFullName);
                        editor.putString(KEY_EMAIL,textEmail);
                        editor.putString(KEY_DOB,textDoB);
//                        editor.putString(KEY_GENDER,textGender);
                        editor.putString(KEY_MOBILE,textMobile);
                        editor.putString(KEY_PASSWORD,textPwd);
                        editor.putString(KEY_CONFIRM_PASSWORD,textConfirmPwd);
                        editor.apply();





                }


            }
        });



    }
    private void   registerUser( String textFullName,String textEmail, String textDoB, String textMobile, String textPwd, String textConfirmPwd){

        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.createUserWithEmailAndPassword(textEmail,textPwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(RegisterActivity.this, "register Successfully ", Toast.LENGTH_SHORT).show();
                    FirebaseUser firebaseUser = auth.getCurrentUser();

                    // Enter user data into the firebase realtime database
                    ReadWriteUserDetails writeUserDetails = new  ReadWriteUserDetails(textFullName,textDoB,textMobile);
//                    DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReferenceFromUrl("https://learningapp-b3391-default-rtdb.firebaseio.com/");


                    DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReferenceFromUrl("https://learningapp-b3391-default-rtdb.firebaseio.com/");

                    referenceProfile.child("users").child(firebaseUser.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });



                    // send email verification


                    Intent iLogin = new Intent(RegisterActivity.this,LoginActivity.class);
                    Toast.makeText(RegisterActivity.this,"Ragistration Successfully ..",Toast.LENGTH_SHORT).show();

                    startActivity(iLogin);
                    finish();
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Registration fialed " , Toast.LENGTH_SHORT).show();
                }
            }
        });



    }










}