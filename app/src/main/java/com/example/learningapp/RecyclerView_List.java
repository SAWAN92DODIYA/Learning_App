package com.example.learningapp;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RecyclerView_List extends AppCompatActivity {
private  int number = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_view_list);
        final RecyclerView[] recyclerView = {findViewById(R.id.recyclerContact)};
        recyclerView[0].setLayoutManager(new LinearLayoutManager(this));
        final FirebaseAuth[] auth = {FirebaseAuth.getInstance()};

//        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReferenceFromUrl("https://learningapp-b3391-default-rtdb.firebaseio.com/");

        FirebaseUser  firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid();
        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("devices").child(uid);
        referenceProfile.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ReadWriteDeviceDetails deviceDetails = snapshot.getValue(ReadWriteDeviceDetails.class);
                    String deviceName  = deviceDetails.device_name;
                    String modelName = deviceDetails.device_model;
                    String devicePrice = deviceDetails.device_price;
                    Toast.makeText(RecyclerView_List.this, devicePrice+""+modelName +""+deviceName, Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}