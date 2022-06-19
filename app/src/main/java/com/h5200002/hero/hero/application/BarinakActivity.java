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

public class BarinakActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapterBarinak myAdapterBarinak;
    ArrayList<Barinak> list;
    private Button barinak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barinak);
        barinak =(Button) findViewById(R.id.barinak);
        barinak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(BarinakActivity.this, BarinakEkleActivity.class);
                startActivity(intentLogin);
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclbarinak);
        database = FirebaseDatabase.getInstance().getReference("Barinak");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        myAdapterBarinak = new MyAdapterBarinak(this, list);
        recyclerView.setAdapter(myAdapterBarinak);



        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    Barinak barinak = dataSnapshot.getValue(Barinak.class);
                    barinak.setId(dataSnapshot.getKey());
                    if( !list.contains(barinak) ) list.add(barinak);

                }
                myAdapterBarinak.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(BarinakActivity.this, BeHizmetActivity.class));
    }
}