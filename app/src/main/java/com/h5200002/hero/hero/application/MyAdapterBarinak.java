package com.h5200002.hero.hero.application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyAdapterBarinak extends RecyclerView.Adapter<MyAdapterBarinak.MyViewHolder> {
    Context context;

    ArrayList<Barinak> list;
    private FirebaseStorage storage;
    private StorageReference storageReference;


    public MyAdapterBarinak(Context context, ArrayList<Barinak> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itembarinak,parent, false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Barinak barinak = list.get(list.size()-1-position);
        holder.adres.setText(barinak.getBarinakAdresi());
        holder.durum.setChecked(barinak.getBarinakDurumu());
        holder.durum.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                barinak.setBarinakDurumu(isChecked);
                Map<String,Object> a= new HashMap<>();
                a.put("barinakAdresi",barinak.getBarinakAdresi());
                a.put("barinakDurumu",barinak.getBarinakDurumu());
                storage = FirebaseStorage.getInstance();
                storageReference = storage.getReference();
                DatabaseReference ddRef = FirebaseDatabase.getInstance().getReference().child("Barinak");
                ddRef.child(barinak.getId()).updateChildren(a);


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView adres;
        Switch durum;


        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            adres = itemView.findViewById(R.id.barinakad);
            durum = itemView.findViewById(R.id.switch1);



        }

    }
}
