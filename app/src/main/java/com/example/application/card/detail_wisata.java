package com.example.application.card;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.application.R;
import com.example.application.penyedia.penyedia1;

public class detail_wisata extends AppCompatActivity{
    TextView Key,NamaWisata,DeskripsiTempat,Waktu,Lokasi,Harga,Aturan,JenisTempat;
    ImageView Gambar;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailwisata);
        Key = (TextView) findViewById(R.id.key);
        NamaWisata = (TextView) findViewById(R.id.nama_tempat);
        DeskripsiTempat = (TextView) findViewById(R.id.deskripsitempat);
        Waktu = (TextView) findViewById(R.id.waktu);
        Lokasi = (TextView) findViewById(R.id.lokasi);
        Harga = (TextView) findViewById(R.id.harga);
        Aturan = (TextView) findViewById(R.id.aturan);
        JenisTempat = (TextView) findViewById(R.id.jenistempat);
        Gambar = (ImageView) findViewById(R.id.gambar);
        pd = new ProgressDialog(this);

        Intent data = getIntent();
        Key.setText(data.getStringExtra("keypradha2"));
        NamaWisata.setText(data.getStringExtra("Nama_tempat"));
        DeskripsiTempat.setText(data.getStringExtra("Deskripsi"));
        Waktu.setText(data.getStringExtra("Waktu"));
        Lokasi.setText(data.getStringExtra("Alamat_Lokasi"));
        Harga.setText(data.getStringExtra("Harga"));
        Aturan.setText(data.getStringExtra("Persyaratan"));
        JenisTempat.setText(data.getStringExtra("Jenis_Tempat"));
        Glide.with(this).load(data.getStringExtra("Gambar")).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(Gambar);
    }
    public void backdetail (View v){
        Intent back = new Intent(detail_wisata.this, penyedia1.class);
        startActivity(back);
    }
}