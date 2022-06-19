package com.h5200002.hero.hero.application;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MamaEkleActivity extends AppCompatActivity {
    private Button mamaekle;

    private EditText mamaadres;
    private Switch mamadurum;

    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mama_ekle);
        mamaekle =(Button) findViewById(R.id.mamaekle);

        mamaadres= (EditText) findViewById(R.id.mamaadres);
        mamadurum= (Switch) findViewById(R.id.mamadurum);


        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        mamaekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ddRef = FirebaseDatabase.getInstance().getReference().child("Mama");
                Mama mama = new Mama();
                mama.setMamaAdresi(mamaadres.getText().toString());
                mama.setMamaDurumu(mamadurum.isChecked());




                String adrs = mamaadres.getText().toString();

                if (TextUtils.isEmpty(adrs)) {
                    Toast.makeText(MamaEkleActivity.this, "Adres alanı boş olamaz!", Toast.LENGTH_LONG).show();

                } else {
                    mamadurum.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(isChecked){


                            }
                        }
                    });
                    ddRef.push().setValue(mama);
                    Toast.makeText(MamaEkleActivity.this, "Kap eklenmiştir!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MamaEkleActivity.this, MamaActivity.class));
                    finish();

                }

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MamaEkleActivity.this, BeHizmetActivity.class));
    }
}