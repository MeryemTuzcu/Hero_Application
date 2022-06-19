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

public class BarinakEkleActivity extends AppCompatActivity {
    private Button barinakekle;

    private EditText barinakadres;
    private Switch barinakdurum;

    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barinak_ekle);
        barinakekle =(Button) findViewById(R.id.barinakekle);

        barinakadres= (EditText) findViewById(R.id.barinakadres);
        barinakdurum= (Switch) findViewById(R.id.barinakdurum);


        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        barinakekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ddRef = FirebaseDatabase.getInstance().getReference().child("Barinak");
                Barinak barinak = new Barinak();
                barinak.setBarinakAdresi(barinakadres.getText().toString());
                barinak.setBarinakDurumu(barinakdurum.isChecked());




                String adrs = barinakadres.getText().toString();

                if (TextUtils.isEmpty(adrs)) {
                    Toast.makeText(BarinakEkleActivity.this, "Adres alanı boş olamaz!", Toast.LENGTH_LONG).show();

                } else {
                    barinakdurum.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(isChecked){


                            }
                        }
                    });
                    ddRef.push().setValue(barinak);
                    Toast.makeText(BarinakEkleActivity.this, "Barınak eklenmiştir.!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(BarinakEkleActivity.this, BarinakActivity.class));
                    finish();

                }

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(BarinakEkleActivity.this, BeHizmetActivity.class));
    }
}