package com.h5200002.hero.hero.application;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ParaActivity extends AppCompatActivity {
    private Button parakydt;
    private EditText nameSurname2;
    private EditText email4, telefon4,sifre4,nameSurname5,nameSurname6,nameSurname7,nameSurname8,nameSurname9,nameSurname10;
    private FirebaseStorage storage;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_para);
        parakydt=(Button) findViewById(R.id.parakydt);
        nameSurname2=(EditText) findViewById(R.id.nameSurname2);
        email4=(EditText) findViewById(R.id.email4);
        telefon4=(EditText) findViewById(R.id.telefon4);
        sifre4=(EditText) findViewById(R.id.sifre4);
        nameSurname5=(EditText) findViewById(R.id.nameSurname5);
        nameSurname6=(EditText) findViewById(R.id.nameSurname6);
        nameSurname7=(EditText) findViewById(R.id.nameSurname7);
        nameSurname8=(EditText) findViewById(R.id.nameSurname8);
        nameSurname9=(EditText) findViewById(R.id.nameSurname9);
        nameSurname10=(EditText) findViewById(R.id.nameSurname10);


        parakydt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Para para = new Para();
                //List<Places> placesList= new ArrayList<>();
                para.setParaAd(nameSurname2.getText().toString());
                para.setParaTC(sifre4.getText().toString());
                para.setParaNum(telefon4.getText().toString());
                para.setParaEmail(email4.getText().toString());
                try {
                    para.setParaTutar(Integer.parseInt(nameSurname5.getText().toString()));
                }catch (Exception e){
                    para.setParaTutar(0);
                }

                para.setParaKIsim(nameSurname6.getText().toString());
                para.setParaKNum(nameSurname7.getText().toString());
                para.setParaYıl(nameSurname8.getText().toString());
                para.setParaAy(nameSurname9.getText().toString());
                para.setParaCvv(nameSurname10.getText().toString());

                storage = FirebaseStorage.getInstance();
                storageReference = storage.getReference();

                String Err= "";
                if (TextUtils.isEmpty(para.getParaAd())) {
                    Toast.makeText(ParaActivity.this, "Ad Soyad alanı boş olamaz!", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(para.getParaTC())) {
                    Toast.makeText(ParaActivity.this, "TC alanı boş olamaz!", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(para.getParaNum())) {
                    Toast.makeText(ParaActivity.this, "Telefon alanı boş olamaz!", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(para.getParaEmail())) {
                    Toast.makeText(ParaActivity.this, "Email alanı boş olamaz!", Toast.LENGTH_LONG).show();
                } else if(para.getParaTutar()<= 0 ){
                    Toast.makeText(ParaActivity.this, "Para Tutar alanı boş olamaz!", Toast.LENGTH_LONG).show();

                }else if(TextUtils.isEmpty(para.getParaKIsim())){
                    Toast.makeText(ParaActivity.this, "Kart İsim alanı boş olamaz!", Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(para.getParaKNum())){
                    Toast.makeText(ParaActivity.this, "Kart Numara alanı boş olamaz!", Toast.LENGTH_LONG).show();

                }else if(TextUtils.isEmpty(para.getParaYıl())){
                    Toast.makeText(ParaActivity.this, "Kart Yıl alanı boş olamaz!", Toast.LENGTH_LONG).show();

                }else if(TextUtils.isEmpty(para.getParaAy())){
                    Toast.makeText(ParaActivity.this, "Kart Ay alanı boş olamaz!", Toast.LENGTH_LONG).show();

                }else if(TextUtils.isEmpty(para.getParaCvv())){
                    Toast.makeText(ParaActivity.this, "Kart Cvv alanı boş olamaz!", Toast.LENGTH_LONG).show();

                } else {
                    DatabaseReference ddReff = FirebaseDatabase.getInstance().getReference().child("Para");
                    ddReff.push().setValue(para);
                    Toast.makeText(ParaActivity.this, "Para bağışı gerçekleştirilmiştir!", Toast.LENGTH_LONG).show();
                }


            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ParaActivity.this, HizmetlerActivity.class));
    }


}