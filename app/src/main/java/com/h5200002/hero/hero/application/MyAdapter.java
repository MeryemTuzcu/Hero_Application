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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;

    ArrayList<Ilanlar> list;

    public MyAdapter(Context context, ArrayList<Ilanlar> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent, false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Ilanlar ilan = list.get(list.size()-1-position);
        holder.adres.setText(ilan.getKayipAdresi());
        holder.ilce.setText(ilan.getKayipIlce());
        holder.numara.setText(ilan.getKayipNumara());


        byte[] imageBytes = Base64.decode(ilan.getKayipGorsel(), Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        holder.gorsel.setImageBitmap(decodedImage);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView adres, ilce, numara;
        ImageView gorsel;


        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            adres = itemView.findViewById(R.id.adres);
            ilce = itemView.findViewById(R.id.ilce);
            numara = itemView.findViewById(R.id.num);
            gorsel = itemView.findViewById(R.id.gorsel);


        }

    }
}
