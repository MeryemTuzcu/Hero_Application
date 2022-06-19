package com.h5200002.hero.hero.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HizmetlerActivity extends AppCompatActivity {
    private FirebaseUser aktifkullanici;
    private FirebaseAuth auth;
    private Button button, kayipilanlari;
    private Button parabagis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hizmetler);
        init();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(HizmetlerActivity.this,MapActivity.class);
                startActivity(intentLogin);
                finish();
            }
        });

        kayipilanlari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log = new Intent(HizmetlerActivity.this, KayipIlanActivity.class);
                startActivity(log);
                finish();
            }
        });
        parabagis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent para = new Intent(HizmetlerActivity.this, ParaActivity.class);
                startActivity(para);
                finish();
            }
        });


    }
    public void init(){
        auth = FirebaseAuth.getInstance();
        aktifkullanici = auth.getCurrentUser();
        button =(Button) findViewById(R.id.acilkontrol);
        kayipilanlari =(Button) findViewById(R.id.kayipilanlari);
        parabagis=(Button) findViewById(R.id.parabagis);
    }
    @Override
    protected void onStart() {
        if(aktifkullanici ==null){
            Intent merhaba = new Intent(HizmetlerActivity.this, HosgeldinActivity.class);
            startActivity(merhaba);
            finish();
        }
        super.onStart();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(HizmetlerActivity.this, HosgeldinActivity.class));
    }

}