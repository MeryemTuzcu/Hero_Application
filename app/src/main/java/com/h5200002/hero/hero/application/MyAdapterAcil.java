package com.h5200002.hero.hero.application;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterAcil extends RecyclerView.Adapter<MyAdapterAcil.MyViewHolder> {
    Context context;

    ArrayList<Places> list;

    public MyAdapterAcil(Context context, ArrayList<Places> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itemacil,parent, false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Places place = list.get(list.size()-1-position);
        holder.adres2.setText(place.getKonumAdresi());
        holder.ilce2.setText(place.getKonumIlce());
        holder.il22.setText(place.getKonumIl());


        byte[] imageBytes = Base64.decode(place.getKonumGorsel(), Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        holder.gorsel2.setImageBitmap(decodedImage);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView adres2, ilce2, il22;
        ImageView gorsel2;


        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            adres2 = itemView.findViewById(R.id.adres2);
            ilce2 = itemView.findViewById(R.id.ilce2);
            il22 = itemView.findViewById(R.id.il22);
            gorsel2 = itemView.findViewById(R.id.gorsel2);

        }

    }
}

