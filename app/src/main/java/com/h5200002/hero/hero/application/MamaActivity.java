package com.h5200002.hero.hero.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

public class MamaActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapterMama myAdapterMama;
    ArrayList<Mama> list;
    private Button mama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mama);
        mama =(Button) findViewById(R.id.mama);
        mama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(MamaActivity.this, MamaEkleActivity.class);
                startActivity(intentLogin);
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclmama);
        database = FirebaseDatabase.getInstance().getReference("Mama");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        myAdapterMama = new MyAdapterMama(this, list);
        recyclerView.setAdapter(myAdapterMama);



        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    Mama mama = dataSnapshot.getValue(Mama.class);
                    mama.setId(dataSnapshot.getKey());
                    if( !list.contains(mama) ) list.add(mama);

                }
                myAdapterMama.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MamaActivity.this, BeHizmetActivity.class));
    }
}