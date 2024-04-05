package com.example.learningapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DeviceDetails extends AppCompatActivity {

    private Button addDebeviceBtn;
    private EditText deviceNameText, modelText, priceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_device_details);

        addDebeviceBtn = findViewById(R.id.button_add_device);
        deviceNameText = findViewById(R.id.editText_device_name);
        modelText = findViewById(R.id.editText_device_model);
        priceText  = findViewById(R.id.editText_device_price);

        String device_name = deviceNameText.getText().toString();
        String model_name = modelText.getText().toString();
        String device_price = priceText.getText().toString();
        addDebeviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();

                FirebaseUser firebaseUser = auth.getCurrentUser();

                // Enter user data into the firebase realtime database
                ReadWriteDeviceDetails writeDeviceDetails = new ReadWriteDeviceDetails(device_name,model_name,device_price);
//                    DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReferenceFromUrl("https://learningapp-b3391-default-rtdb.firebaseio.com/");


                DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReferenceFromUrl("https://learningapp-b3391-default-rtdb.firebaseio.com/");

                referenceProfile.child("devices").child(firebaseUser.getUid()).setValue(writeDeviceDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(DeviceDetails.this,"b hoooo",Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });








    }
}