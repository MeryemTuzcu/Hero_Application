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

public class MyAdapterMama extends RecyclerView.Adapter<MyAdapterMama.MyViewHolder> {
    Context context;

    ArrayList<Mama> list;
    private FirebaseStorage storage;
    private StorageReference storageReference;


    public MyAdapterMama(Context context, ArrayList<Mama> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itemmama,parent, false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Mama mama = list.get(list.size()-1-position);
        holder.adres.setText(mama.getMamaAdresi());
        holder.durum.setChecked(mama.getMamaDurumu());
        holder.durum.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mama.setMamaDurumu(isChecked);
                Map<String,Object> a= new HashMap<>();
                a.put("mamaAdresi",mama.getMamaAdresi());
                a.put("mamaDurumu",mama.getMamaDurumu());
                storage = FirebaseStorage.getInstance();
                storageReference = storage.getReference();
                DatabaseReference ddRef = FirebaseDatabase.getInstance().getReference().child("Mama");
                ddRef.child(mama.getId()).updateChildren(a);


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

            adres = itemView.findViewById(R.id.mamaad);
            durum = itemView.findViewById(R.id.switch2);



        }

    }
}
