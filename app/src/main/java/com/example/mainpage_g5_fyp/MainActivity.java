package com.example.mainpage_g5_fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private String phoneNumb;
    EditText mSearchPlate;
    Button mSearch, mProfile, mMyVehicle, myProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumb = getIntent().getExtras().get("phoneNumber").toString();


        mSearch = findViewById(R.id.search);
        mSearchPlate = findViewById(R.id.searchPlate);
        mProfile = findViewById(R.id.profileBtn);
        mMyVehicle = findViewById(R.id.myVehicles);
        myProfile = findViewById(R.id.viewProfile);

        //Open My Profile Page
        myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyProfile.class);
                intent.putExtra("phoneNumber",phoneNumb);
                startActivity(intent);
            }
        });

        //Open My Vehicle Page
        mMyVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyVehiclePage.class);
                intent.putExtra("phoneNumber",phoneNumb);
                startActivity(intent);
            }
        });

        //Open Profile Page
        mProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                intent.putExtra("phoneNumber",phoneNumb);
                startActivity(intent);;
            }
        });

        //Start add vehicle activity
        Button btn = (Button)findViewById(R.id.buttonVehicle);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("phoneNumber",phoneNumb);
                startActivity(intent);;
            }
        });

        //Search Number Plate
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plate = mSearchPlate.getText().toString().trim();

                if (TextUtils.isEmpty(plate)){
                    Toast.makeText(MainActivity.this, "Please enter a number plate", Toast.LENGTH_SHORT).show();
                } else {
                    SearchNumberPlate(plate);
                }
            }
        });
    }


    private void SearchNumberPlate(String plate) {
        final DatabaseReference reff;
        reff = FirebaseDatabase.getInstance().getReference();

        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if ((snapshot.child("Cars").child(plate).exists())){
                    Intent intent = new Intent(MainActivity.this,CarInformation.class);
                    intent.putExtra("plate",plate);
                    intent.putExtra("phoneNumber",phoneNumb);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "This number plate does not exist in our database", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void logout_clicked(View view) {
        startActivity(new Intent(MainActivity.this, MainActivity4.class));

    }
}