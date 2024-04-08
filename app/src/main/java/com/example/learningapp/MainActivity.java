 package com.example.learningapp;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

 public class MainActivity extends AppCompatActivity {

    private TextView textView_profile ;
    private FloatingActionButton flatingButtonMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


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


//                Intent iDevice = new Intent(MainActivity.this,DeviceDetails.class);
//                startActivity(iDevice);





              //Dialog Box


                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.activity_device_details);

                Button addDebeviceBtn = dialog.findViewById(R.id.button_add_device);
                EditText deviceNameText =dialog.findViewById(R.id.editText_device_name);
                EditText modelText =dialog.findViewById(R.id.editText_device_model);
                EditText priceText =dialog.findViewById(R.id.editText_device_price);

                addDebeviceBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseAuth auth = FirebaseAuth.getInstance();

                        FirebaseUser firebaseUser = auth.getCurrentUser();
                        String device_name = deviceNameText.getText().toString();
                        String model_name = modelText.getText().toString();
                        String device_price = priceText.getText().toString();


                        // Enter user data into the firebase realtime database
                        ReadWriteDeviceDetails writeDeviceDetails = new ReadWriteDeviceDetails(device_name,model_name,device_price);


                        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReferenceFromUrl("https://learningapp-b3391-default-rtdb.firebaseio.com/");


                        /// write the new code
                        String deviceSubId  = referenceProfile.push().getKey();


                        String devicePath = "devices/" + firebaseUser.getUid() + "/" + deviceSubId;
                        referenceProfile.child(devicePath).setValue(writeDeviceDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(MainActivity.this,"Device Details are Succesdfully Stored ",Toast.LENGTH_SHORT).show();
                            }
                        });


                        dialog.dismiss();

                    }


                });



                dialog.show();






                //Dialog Box

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

