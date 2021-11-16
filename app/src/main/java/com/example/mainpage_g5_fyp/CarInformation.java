package com.example.mainpage_g5_fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CarInformation extends AppCompatActivity {
    private String carPlate;
    DatabaseReference DatabaseRef;
    TextView Plate, Description, Model, Phone;
    String phoneNumb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_information);

        String carPlate = getIntent().getExtras().get("plate").toString(); //get Plate number from search in previous activity

        Plate = findViewById(R.id.carPlate);
        Description = findViewById(R.id.carDescrip);
        Model = findViewById(R.id.carModel2);
        Phone = findViewById(R.id.phoneNum);

        DisplayCarInfo(carPlate);
    }

    private void DisplayCarInfo(final String carPlate) {
        DatabaseRef = FirebaseDatabase.getInstance().getReference().child("Cars").child(carPlate); //carPlate is string from getIntent
        DatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String plate = snapshot.child("number").getValue().toString();
                String model = snapshot.child("model").getValue().toString();
                String description = snapshot.child("description").getValue().toString();
                String phone = snapshot.child("phone").getValue().toString();

                // Set text on XML File to display data from database
                Plate.setText(plate);
                Model.setText(model);
                Description.setText(description);
                Phone.setText(phone);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}