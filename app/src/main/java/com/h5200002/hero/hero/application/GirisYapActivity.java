package com.h5200002.hero.hero.application;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class GirisYapActivity extends AppCompatActivity {
    ImageView imageView18;
    ImageView imageView16;
    ImageView imageView17;
    ImageView imageView;
    TextView textView2;
    EditText email2;
    EditText sifre2;
    Button sifremiunuttum;
    Button girisyap;

    private FirebaseAuth auth;
    private FirebaseUser currentUser;





    private void init(){
        girisyap=(Button) findViewById(R.id.girisyap);
        sifremiunuttum=(Button) findViewById(R.id.sifremiunuttum);
        textView2=(TextView )findViewById(R.id.textView2);
        email2=(EditText) findViewById(R.id.email2);
        sifre2=(EditText) findViewById(R.id.sifre2);
        imageView=(ImageView) findViewById(R.id.imageView);
        imageView16=(ImageView) findViewById(R.id.imageView16);
        imageView17=(ImageView) findViewById(R.id.imageView17);
        imageView18=(ImageView) findViewById(R.id.imageView18);
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        init();






        sifremiunuttum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetmail = new EditText(v.getContext());
                AlertDialog.Builder passwordReset = new AlertDialog.Builder(v.getContext());
                passwordReset.setTitle("Şifremi sıfırla");
                passwordReset.setMessage("Şifreni sıfırlamak için EMail adresinizi giriniz");
                passwordReset.setView(resetmail);

                passwordReset.setPositiveButton("evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String mail = resetmail.getText().toString();
                        if (TextUtils.isEmpty(mail)) {
                            Toast.makeText(GirisYapActivity.this, "Geçerli bir email adresi giriniz!", Toast.LENGTH_LONG).show();

                        }else{
                            auth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(GirisYapActivity.this, "Emalinize yenileme linki gönderilmiştir", Toast.LENGTH_LONG).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(GirisYapActivity.this,"Hata! Emalinize Link gönerilemedi"+ e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });

                        }

                    }
                });
                passwordReset.setNegativeButton("hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                passwordReset.create().show();

            }
        });

        girisyap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }



    private void loginUser() {
        String email = email2.getText().toString();
        String sifre =sifre2.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Email alanı boş olamaz!",Toast.LENGTH_LONG).show();

        }else if (TextUtils.isEmpty(sifre)){
            Toast.makeText(this,"Şifre alanı boş olamaz!",Toast.LENGTH_LONG).show();
        }else{
            isUser();

        }

    }

    private void isUser() {
        String userEnterEmail = email2.getText().toString();
        String userEnterSifre = sifre2.getText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference( "Uyeler");

        Query checkMail = reference.orderByChild("uyemail").equalTo(userEnterEmail);
        Query checkPassword = reference.orderByChild("uyesifre").equalTo(userEnterSifre);


        checkMail.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    DataSnapshot ds = null;
                    for (DataSnapshot temp: snapshot.getChildren()) {
                        System.out.println(temp.child("uyesifre").getValue());
                        System.out.println(temp.child("uyenamesurname").getValue());
                        if(temp.child("uyemail").getValue().equals(userEnterEmail) && temp.child("uyesifre").getValue().equals(userEnterSifre) ){
                            ds = temp;
                            break;
                        }
                    }
                    if(ds == null){
                        sifre2.setError("Şifre veya Email Yanlış!");

                    }else if (ds.child("uyetip").getValue().equals(true) ){
                        String sifreDB = ds.child("uyesifre").getValue(String.class);
                        String nameDB = ds.child("uyenamesurname").getValue(String.class);
                        String numDB = ds.child("uyenum").getValue(String.class);
                        String mailDB = ds.child("uyemail").getValue(String.class);

                        Intent hizmet = new Intent(GirisYapActivity.this, BeHizmetActivity.class);
                        startActivity(hizmet);
                        finish();
                    }else{
                        String sifreDB = ds.child("uyesifre").getValue(String.class);
                        String nameDB = ds.child("uyenamesurname").getValue(String.class);
                        String numDB = ds.child("uyenum").getValue(String.class);
                        String mailDB = ds.child("uyemail").getValue(String.class);

                        Intent hizmet = new Intent(GirisYapActivity.this, HizmetlerActivity.class);
                        startActivity(hizmet);
                        finish();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(GirisYapActivity.this, HosgeldinActivity.class));
    }


}