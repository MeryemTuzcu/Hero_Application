package com.h5200002.hero.hero.application;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BeAcilActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapterAcil myAdapterAcil;
    ArrayList<Places> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_be_acil);

        recyclerView = findViewById(R.id.recycl2);
        database = FirebaseDatabase.getInstance().getReference("Places");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapterAcil = new MyAdapterAcil(this, list);
        recyclerView.setAdapter(myAdapterAcil);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    Places place = dataSnapshot.getValue(Places.class);

                    list.add(place);

                }
                myAdapterAcil.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(BeAcilActivity.this, BeHizmetActivity.class));
    }
}