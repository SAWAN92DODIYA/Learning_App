package com.example.learningapp;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class RecyclerView_List extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_view_list);
//        RecyclerView recyclerView = findViewById(R.id.recyclerContact);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        FirebaseAuth auth = FirebaseAuth.getInstance();

//        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReferenceFromUrl("https://learningapp-b3391-default-rtdb.firebaseio.com/");

        FirebaseUser  firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid();
        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReferenceFromUrl("https://learningapp-b3391-default-rtdb.firebaseio.com/");
        referenceProfile.child("devices").orderByKey().get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                for(DataSnapshot snapshot : task.getResult().getChildren()) {
                    HashMap<String, Objects> deviceDetails =  (HashMap<String, Objects>) snapshot.getValue();
                    ArrayList<String> keySet = new ArrayList<>(deviceDetails.keySet());
                    Log.d(getClass().getSimpleName(),"getKey "+ keySet);
                    Log.d(getClass().getSimpleName(), "onComplete: " +  snapshot.getValue());
                }
            }
        });
        referenceProfile.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HashMap<String, Objects> deviceDetails =  (HashMap<String, Objects>) snapshot.getValue();
                    Log.d(getClass().getSimpleName(), "Ayudhya: " + deviceDetails);
                    Log.d(getClass().getSimpleName(), "Ayudhya: " + deviceDetails);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Toast.makeText(this, "i the line ", Toast.LENGTH_SHORT).show();


    }
}