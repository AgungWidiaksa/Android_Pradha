package com.example.application.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.application.API.APIInsertReg;
import com.example.application.API.Retroserver;
import com.example.application.R;
import com.example.application.Util.SessionManager;
import com.example.application.card.detail_wisata1;
import com.example.application.card.detail_wisata2;
import com.example.application.model.ResponsModel;
import com.example.application.model.SelectModel;
import com.example.application.wisata.wisataumum;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class adapterdata1 extends RecyclerView.Adapter<adapterdata1.HolderData> {

    private List<SelectModel> mList;
    private Context ctx;

    public adapterdata1(Context ctx, List<SelectModel> mList){
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardmenu1,parent,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        SelectModel dm = mList.get(position);
        holder.Nama_tempat.setText(dm.getNama_tempat());
        holder.Deskripsi.setText(dm.getDeskripsi());
        holder.Waktu.setText(dm.getWaktu());
        holder.sm = new SessionManager(ctx);
        HashMap<String, String> map = holder.sm.getDetailLogin();
        holder.keys1.setText(map.get(holder.sm.KEY));
        //holder.keys1.setText(dm.getKeypradha1());
        holder.keys2.setText(dm.getKeypradha2());
        Glide.with(ctx).load(dm.getGambar()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.Gambar);
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HolderData extends RecyclerView.ViewHolder{
        TextView Nama_tempat,Waktu,Deskripsi,keys1,keys2;
        ImageView Gambar;
        SessionManager sm;
        public SelectModel dm;
        public HolderData (View v){
            super(v);
            Nama_tempat = (TextView) v.findViewById(R.id.card_name);
            Waktu = (TextView) v.findViewById(R.id.card_waktu);
            Deskripsi = (TextView) v.findViewById(R.id.card_deskripsi);
            Gambar = (ImageView) v.findViewById(R.id.img_item_photo);
            keys2 = (TextView) v.findViewById(R.id.key);
            keys1 = (TextView) v.findViewById(R.id.key1);
            Button detail = (Button) v.findViewById(R.id.detail);
            detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detaill = new Intent(ctx, detail_wisata2.class);
                    detaill.putExtra("keypradha2", dm.getKeypradha2());
                    detaill.putExtra("Nama_tempat", dm.getNama_tempat());
                    detaill.putExtra("Deskripsi", dm.getDeskripsi());
                    detaill.putExtra("Waktu", dm.getWaktu());
                    detaill.putExtra("Alamat_Lokasi", dm.getAlamat_Lokasi());
                    detaill.putExtra("Harga", dm.getHarga());
                    detaill.putExtra("Persyaratan", dm.getPersyaratan());
                    detaill.putExtra("Jenis_Tempat", dm.getJenis_Tempat());
                    detaill.putExtra("Gambar", dm.getGambar());
                    ctx.startActivity(detaill);
                }
            });
            Button dell = (Button) v.findViewById(R.id.boking);
            dell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String key1 = keys1.getText().toString();
                    String key2 = keys2.getText().toString();
                    APIInsertReg api = Retroserver.getClient().create(APIInsertReg.class);
                    Call<ResponsModel> boking = api.bokingTempat(key1,key2);
                    boking.enqueue(new Callback<ResponsModel>() {
                        @Override
                        public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                            Log.d("RETRO", "response : " + response.body().toString());
                            String kode = response.body().getKode();
                            if (kode.equals("1")) {
                                Intent refresh = new Intent(ctx, wisataumum.class);
                                ctx.startActivity(refresh);
                                Toast.makeText(ctx, "Success Booking..", Toast.LENGTH_SHORT).show();
                            }
                            else if(kode.equals("3")){
                                Toast.makeText(ctx, "The data it's been booking..", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<ResponsModel> call, Throwable t) {
                            Log.d("RETRO", "Falure : " + "Riquest Not Falid");
                        }
                    });
                }
            });
        }
    }
}
