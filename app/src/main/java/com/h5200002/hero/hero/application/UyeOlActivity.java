package com.h5200002.hero.hero.application;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UyeOlActivity extends AppCompatActivity {


    Button devamet;
    EditText nameSurname;
    EditText telefon;
    EditText email;
    EditText sifre;
    ImageView imageView18;
    ImageView imageView16;
    ImageView imageView17;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye_ol);
        init();

    }



    public void init(){
        devamet=(Button) findViewById(R.id.devamet);
        nameSurname=(EditText) findViewById(R.id.nameSurname);
        telefon=(EditText) findViewById(R.id.telefon);
        email=(EditText) findViewById(R.id.email);
        sifre=(EditText) findViewById(R.id.sifre);
        imageView=(ImageView) findViewById(R.id.imageView);
        imageView16=(ImageView) findViewById(R.id.imageView16);
        imageView17=(ImageView) findViewById(R.id.imageView17);
        imageView18=(ImageView) findViewById(R.id.imageView18);

        devamet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference ddRef = FirebaseDatabase.getInstance().getReference().child("Uyeler");
                Model model = new Model();
                model.setUyenamesurname(nameSurname.getText().toString());
                model.setUyenum(telefon.getText().toString());
                model.setUyemail(email.getText().toString());
                model.setUyesifre(sifre.getText().toString());

                String username = nameSurname.getText().toString();
                String tel = telefon.getText().toString();
                String epost = email.getText().toString();
                String sif = sifre.getText().toString();

                if (TextUtils.isEmpty(epost)) {
                    Toast.makeText(UyeOlActivity.this, "Email alanı boş olamaz!", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(sif)) {
                    Toast.makeText(UyeOlActivity.this, "Şifre alanı boş olamaz!", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(username)) {
                    Toast.makeText(UyeOlActivity.this, "Ad Soyad alanı boş olamaz!", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(tel)) {
                    Toast.makeText(UyeOlActivity.this, "Telefon alanı boş olamaz!", Toast.LENGTH_LONG).show();
                } else {
                    ddRef.push().setValue(model);
                    Toast.makeText(UyeOlActivity.this, "Üye işleminiz gerçekleştirilmiştir!", Toast.LENGTH_LONG).show();

                }
            }

        });
    }

}