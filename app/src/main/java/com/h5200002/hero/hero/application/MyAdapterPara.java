package com.h5200002.hero.hero.application;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterPara extends RecyclerView.Adapter<MyAdapterPara.MyViewHolder> {
    Context context;

    ArrayList<Para> list;
    int toplamPara = 0;

    public MyAdapterPara(Context context, ArrayList<Para> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itempara,parent, false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Para para = list.get(list.size()-1-position);

        holder.ad.setText(para.getParaAd());
        holder.bagis.setText(String.valueOf(list.get(position).getParaTutar()));

        toplamPara = (toplamPara + list.get(position).getParaTutar());
        Intent intent= new Intent("ToplamPara");
        intent.putExtra("toplamPara", toplamPara);

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView ad, bagis;


        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            ad = itemView.findViewById(R.id.ad);
            bagis = itemView.findViewById(R.id.tc);



        }

    }
}
