package com.example.application.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.application.R;
import com.example.application.card.detail_user;
import com.example.application.card.detail_wisata1;
import com.example.application.card.detail_wisata2;
import com.example.application.model.SelectModel;

import java.util.List;

public class adapterMonitoring extends RecyclerView.Adapter<adapterMonitoring.HolderData> {

    private List<SelectModel> mList;
    private Context ctx;

    public adapterMonitoring(Context ctx, List<SelectModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardmenu3,parent,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        SelectModel dm = mList.get(position);
        holder.Nama_user.setText(dm.getNama_Lengkap());
        holder.Negara.setText(dm.getNegara());
        holder.Email.setText(dm.getEmail());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HolderData extends RecyclerView.ViewHolder{
        TextView Nama_user,Negara,Email;

        public SelectModel dm;
        public HolderData (View v){
            super(v);
            Nama_user = (TextView) v.findViewById(R.id.card_name);
            Negara = (TextView) v.findViewById(R.id.country);
            Email = (TextView) v.findViewById(R.id.email);
            Button detail = (Button) v.findViewById(R.id.detail);
            detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detaill = new Intent(ctx, detail_user.class);
                    detaill.putExtra("Nama_Lengkap", dm.getNama_Lengkap());
                    detaill.putExtra("Negara", dm.getNegara());
                    detaill.putExtra("Umur", dm.getUmur());
                    detaill.putExtra("JenisKelamin", dm.getJenis_Kelamin());
                    detaill.putExtra("HP", dm.getHP());
                    detaill.putExtra("Email", dm.getEmail());
                    ctx.startActivity(detaill);
                }
            });
        }
    }
}
