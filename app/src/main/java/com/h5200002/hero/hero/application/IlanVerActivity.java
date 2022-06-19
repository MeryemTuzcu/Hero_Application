package com.h5200002.hero.hero.application;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;

import me.shaohui.advancedluban.Luban;
import me.shaohui.advancedluban.OnCompressListener;


public class IlanVerActivity extends AppCompatActivity {
    private Button kayipIlan;
    private Button btnfotosec;
    private TextView kilanver;
    private EditText telefon2;
    private EditText ilce;
    private EditText adres;
    private ImageView fotose;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_ver);
        init();
        //openFileChooser();
        fotose.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(!checkCameraPermission()){
                    ActivityCompat.requestPermissions(
                            IlanVerActivity.this, new String[]{
                                    Manifest.permission.CAMERA,
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE}
                            ,1


                    );
                }else{
                    takeImgae();

                }

            }
        });
        kayipIlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ddRef = FirebaseDatabase.getInstance().getReference().child("Ilanlar");
                Ilanlar ilanlar = new Ilanlar();
                ilanlar.setKayipIlce(ilce.getText().toString());
                ilanlar.setKayipAdresi(adres.getText().toString());
                ilanlar.setKayipNumara(telefon2.getText().toString());
                ilanlar.setKayipGorsel(encodeImage);

                String ilc = ilce.getText().toString();
                String tel = telefon2.getText().toString();
                String adrs = adres.getText().toString();
                String gorsel = encodeImage;

                if (TextUtils.isEmpty(ilc)) {
                    Toast.makeText(IlanVerActivity.this, "İLçe alanı boş olamaz!", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(tel)) {
                    Toast.makeText(IlanVerActivity.this, "Telefon alanı boş olamaz!", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(adrs)) {
                    Toast.makeText(IlanVerActivity.this, "Adres alanı boş olamaz!", Toast.LENGTH_LONG).show();
                } else if(TextUtils.isEmpty(gorsel)){
                    Toast.makeText(IlanVerActivity.this, "Görsel alanı boş olamaz!", Toast.LENGTH_LONG).show();
                }else{
                    ddRef.push().setValue(ilanlar);
                    Toast.makeText(IlanVerActivity.this, "Kayıp ilanınız verilmiştir!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(IlanVerActivity.this, KayipIlanActivity.class));
                    finish();

                }

            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean checkCameraPermission(){
        int result1= checkSelfPermission( Manifest.permission.CAMERA);
        int result2= checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int result3= checkSelfPermission( Manifest.permission.READ_EXTERNAL_STORAGE);

        return  result1 == PackageManager.PERMISSION_GRANTED &&
                result2 == PackageManager.PERMISSION_GRANTED &&
                result3 == PackageManager.PERMISSION_GRANTED;
    }
    void takeImgae(){
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(IlanVerActivity.this);

        CropImage.activity().start(IlanVerActivity.this);
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CropImage.ActivityResult result=CropImage.getActivityResult(data);
        if(resultCode==RESULT_OK){
            Uri resulturi=result.getUri();
            String path= FileUtils.getPath(IlanVerActivity.this, resulturi);
            compressImage(path);
            Picasso.with(IlanVerActivity.this).load(resulturi).into(fotose);

        }
    }


    void compressImage(String path){
        Luban.compress(IlanVerActivity.this, new File(path))
                .setMaxSize(50)
                .launch(new OnCompressListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(File file) {
                        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                        ByteArrayOutputStream b =new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
                        byte[] byteArray = b.toByteArray();
                        encodeImage= android.util.Base64.encodeToString(byteArray, Base64.DEFAULT);
                        Picasso.with(IlanVerActivity.this).load(file).into(fotose);



                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }String encodeImage;


    public void init(){

        kayipIlan =(Button) findViewById(R.id.kayipIlan);

        kilanver= (TextView) findViewById(R.id.kilanver);
        telefon2= (EditText) findViewById(R.id.telefon2);
        ilce = (EditText) findViewById(R.id.ilce);
        adres=(EditText) findViewById(R.id.adres);
        fotose=(ImageView) findViewById(R.id.fotose);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(IlanVerActivity.this, KayipIlanActivity.class));
    }


}