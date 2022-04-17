package com.h5200002.hero.hero.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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



//private Toolbar actionbarGiris;
//private void init(){
//    actionbarGiris = (Toolbar) findViewById(R.id.actionbar);
//    setSupportActionBar(actionbarGiris);
//    getSupportActionBar().setTitle("Üye Giriş");
//    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//}

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

        Query checkUser = reference.orderByChild("uyemail").equalTo(userEnterEmail);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String sifreDB = snapshot.child(userEnterEmail).child("uyesifre").getValue(String.class);

                    if(sifreDB.equals(userEnterSifre)){
                        String nameDB = snapshot.child(userEnterEmail).child("uyenamesurname").getValue(String.class);
                        String numDB = snapshot.child(userEnterEmail).child("uyenum").getValue(String.class);
                        String mailDB = snapshot.child(userEnterEmail).child("uyemail").getValue(String.class);

                        Intent hizmet = new Intent(GirisYapActivity.this, HizmetlerActivity.class);
                        startActivity(hizmet);
                        finish();

                    }else{
                        sifre2.setError("Yanlış Şifre");
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}