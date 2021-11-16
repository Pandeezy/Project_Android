package com.example.mainpage_g5_fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyProfile extends AppCompatActivity {
    TextView Number,Password,Email,Username;
    private DatabaseReference Reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        String phoneNumb = getIntent().getExtras().get("phoneNumber").toString();

        Number = findViewById(R.id.myPhoneNumber);
        Password = findViewById(R.id.myPassword);
        Email = findViewById(R.id.myEmail);
        Username = findViewById(R.id.myUsername);

        AccessUserData(phoneNumb);
    }

    private void AccessUserData(final String phoneNumb) {
        Reference = FirebaseDatabase.getInstance().getReference().child("Users").child(phoneNumb);
        Reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String username = snapshot.child("username").getValue().toString();
                String password = snapshot.child("password").getValue().toString();
                String email = snapshot.child("email").getValue().toString();
                String number = snapshot.child("phone").getValue().toString();

                //Set text on XML file
                Username.setText(username);
                Password.setText(password);
                Email.setText(email);
                Number.setText(number);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}