package com.h5200002.hero.hero.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HosgeldinActivity extends AppCompatActivity {




    private Button button, button6;
    public void init(){
        button =(Button) findViewById(R.id.button);
        button6 =(Button) findViewById(R.id.button6);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana);
        init();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(HosgeldinActivity.this, GirisYapActivity.class);
                startActivity(intentLogin);
                finish();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentUyeOl = new Intent(HosgeldinActivity.this, UyeOlActivity.class);
                startActivity(intentUyeOl);
                finish();
            }
        });
    }


}