package com.h5200002.hero.hero.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BeHizmetActivity extends AppCompatActivity {
    Button acilkontrol;
    Button parakontrol;
    Button mamatakip;
    Button barinaktakip;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_be_hizmet);
        acilkontrol =(Button) findViewById(R.id.acilkontrol);
        parakontrol =(Button) findViewById(R.id.parakontrol);
        mamatakip = (Button) findViewById(R.id.mamatakip);
        barinaktakip = (Button) findViewById(R.id.barinaktakip);
        acilkontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(BeHizmetActivity.this, BeAcilActivity.class);
                startActivity(intentLogin);
                finish();
            }
        });

        parakontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(BeHizmetActivity.this, BeParaActivity.class);
                startActivity(intentLogin);
                finish();
            }
        });
        mamatakip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(BeHizmetActivity.this, MamaActivity.class);
                startActivity(intentLogin);
                finish();
            }
        });
        barinaktakip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(BeHizmetActivity.this, BarinakActivity.class);
                startActivity(intentLogin);
                finish();
            }
        });



    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(BeHizmetActivity.this, GirisYapActivity.class));
    }
}