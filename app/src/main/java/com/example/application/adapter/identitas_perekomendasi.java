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
import com.example.application.wisata.detail_user1;

import java.util.List;

public class identitas_perekomendasi extends RecyclerView.Adapter<identitas_perekomendasi.HolderData> {

    private List<SelectModel> mList;
    private Context ctx;

    public identitas_perekomendasi(Context ctx, List<SelectModel> mList){
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
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HolderData extends RecyclerView.ViewHolder{

        public SelectModel dm;
        public HolderData (View v){
            super(v);
                    Intent detaill = new Intent(ctx, detail_user1.class);
                    detaill.putExtra("Nama_Lengkap", dm.getNama_Lengkap());
                    detaill.putExtra("Alamat", dm.getAlamat());
                    detaill.putExtra("Umur", dm.getUmur());
                    detaill.putExtra("JenisKelamin", dm.getJenis_Kelamin());
                    detaill.putExtra("HP", dm.getHP());
                    detaill.putExtra("Email", dm.getEmail());
                    ctx.startActivity(detaill);
        }
    }
}
