package com.h5200002.hero.hero.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HizmetlerActivity extends AppCompatActivity {
    private FirebaseUser aktifkullanici;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hizmetler);
        init();
    }
    public void init(){
        auth = FirebaseAuth.getInstance();
        aktifkullanici = auth.getCurrentUser();
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
}