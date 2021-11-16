package com.example.mainpage_g5_fyp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MyVehiclePage extends AppCompatActivity {

    List<FetchVehicleData> fetchData;
    RecyclerView recyclerView;
    HelperAdapter helperAdapter;
    DatabaseReference reference;
    private String phoneNumb;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_vehicle_page);

        phoneNumb = getIntent().getExtras().get("phoneNumber").toString();

        recyclerView = findViewById(R.id.recyclerView);

        fetchData = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Cars");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ignored :snapshot.getChildren())
                {
                    if (phoneNumb.equals(ignored.child("phone").getValue().toString())){
                        String Model = ignored.child("model").getValue().toString();
                        String Number = ignored.child("number").getValue().toString();
                        String Description = ignored.child("description").getValue().toString();

                        FetchVehicleData data = new FetchVehicleData();
                        data.setVehicleNumber(Number);
                        data.setDescription(Description);
                        data.setModel(Model);
                        fetchData.add(data);
                    }
                }

                if(helperAdapter == null){
                    helperAdapter = new HelperAdapter(fetchData);
                    mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(helperAdapter);

                }
                else{
                    helperAdapter.notifyDataSetChanged();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}