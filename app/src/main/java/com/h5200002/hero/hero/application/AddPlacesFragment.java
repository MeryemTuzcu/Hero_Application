package com.h5200002.hero.hero.application;

import static android.app.Activity.RESULT_OK;
import static androidx.core.content.ContextCompat.checkSelfPermission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;

import me.shaohui.advancedluban.Luban;
import me.shaohui.advancedluban.OnCompressListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddPlacesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class  AddPlacesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddPlacesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddPlacesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddPlacesFragment newInstance(String param1, String param2) {
        AddPlacesFragment fragment = new AddPlacesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }


    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    EditText edStreet, edIlce, edIl;
    Button btnKaydet;
    ImageView foto;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_add_places, container, false);





       edStreet=v.findViewById(R.id.edStreet);
       edIl=v.findViewById(R.id.edIl);
       edIlce=v.findViewById(R.id.edIlce);
       btnKaydet=v.findViewById(R.id.btnKaydet);
       foto=v.findViewById(R.id.foto);


       foto.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(!checkCameraPermission()){
                   ActivityCompat.requestPermissions(
                           getActivity(), new String[]{
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
       btnKaydet.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Places places = new Places();

               places.setKonumAdresi(edStreet.getText().toString());
               places.setKonumIl(edIl.getText().toString());
               places.setKonumIlce(edIlce.getText().toString());
               places.setKonumGorsel(encodeImage);
               DatabaseReference ddReff = FirebaseDatabase.getInstance().getReference().child("Places");




               String street = edStreet.getText().toString();
               String il = edIl.getText().toString();
               String ilce = edIlce.getText().toString();
               String gorsel = encodeImage;

               if (TextUtils.isEmpty(street)) {
                   Toast.makeText(getActivity(), "Adres alanı boş olamaz!", Toast.LENGTH_LONG).show();
               } else if (TextUtils.isEmpty(il)) {
                   Toast.makeText(getActivity(), "İl alanı boş olamaz!", Toast.LENGTH_LONG).show();
               } else if (TextUtils.isEmpty(ilce)) {
                   Toast.makeText(getActivity(), "İlçe alanı boş olamaz!", Toast.LENGTH_LONG).show();
               } else if (TextUtils.isEmpty(gorsel)) {
                   Toast.makeText(getActivity(), "Görsel alanı boş olamaz!", Toast.LENGTH_LONG).show();
               } else {
                   ddReff.push().setValue(places);
                   Toast.makeText(getActivity(), "Acil yardım ihbarınız gönderilmiştir!", Toast.LENGTH_LONG).show();

               }

           }
       });

       return v;
    }


    public boolean checkCameraPermission(){
        int result1= checkSelfPermission(getActivity(), Manifest.permission.CAMERA);
        int result2= checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int result3= checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);

        return  result1 == PackageManager.PERMISSION_GRANTED &&
                result2 == PackageManager.PERMISSION_GRANTED &&
                result3 == PackageManager.PERMISSION_GRANTED;
    }
    void takeImgae(){
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(getContext(),this);

        CropImage.activity().start(getContext(),this);
    }



    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CropImage.ActivityResult result=CropImage.getActivityResult(data);
        if(resultCode==RESULT_OK){
            Uri resulturi=result.getUri();
            String path= FileUtils.getPath(getContext(), resulturi);
            compressImage(path);
            Picasso.with(getContext()).load(resulturi).into(foto);

        }
    }


    void compressImage(String path){
        Luban.compress(getActivity(), new File(path))
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
                        Picasso.with(getContext()).load(file).into(foto);



                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }String encodeImage;



}