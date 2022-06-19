package com.h5200002.hero.hero.application;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BeParaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapterPara myAdapterPara;
    ArrayList<Para> list;
    TextView textView7;
    TextView toplam;
    int toplamPara;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_be_para);


        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mMessageReceiver, new IntentFilter("ToplamPara"));

        recyclerView = findViewById(R.id.recycl3);
        textView7 = (TextView) findViewById(R.id.textView7);
        toplam = (TextView) findViewById(R.id.toplam);

        database = FirebaseDatabase.getInstance().getReference("Para");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapterPara = new MyAdapterPara(this, list);
        recyclerView.setAdapter(myAdapterPara);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    Para para = dataSnapshot.getValue(Para.class);

                    list.add(para);

                }
                myAdapterPara.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalBill = intent.getIntExtra("toplamPara", 0);
            toplam.setText(totalBill + "₺");
        }
    };
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(BeParaActivity.this, BeHizmetActivity.class));
    }
}