package com.example.application.wisata;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.API.APIInsertReg;
import com.example.application.API.Retroserver;
import com.example.application.R;
import com.example.application.Util.SessionManager;
import com.example.application.adapter.adapterdata;
import com.example.application.adapter.adapterdata1;
import com.example.application.adapter.bokingan;
import com.example.application.login;
import com.example.application.model.ResponsModel;
import com.example.application.model.SelectModel;
import com.example.application.penyedia.rekomendasi_wisata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class boking extends AppCompatActivity {
    private RecyclerView mRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private List<SelectModel> mItems = new ArrayList<>();
    ProgressDialog pd;
    SessionManager sm;
    TextView keys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bokingwisata);
        keys = (TextView) findViewById(R.id.key);
        sm = new SessionManager(boking.this);
        HashMap<String, String> map = sm.getDetailLogin();
        keys.setText(map.get(sm.KEY));

        pd = new ProgressDialog(this);
        mRecycler = (RecyclerView) findViewById(R.id.boking);
        mManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(mManager);

        pd.setMessage("Loading ...");
        pd.setCancelable(false);
        pd.show();
        String key1 = keys.getText().toString();
        APIInsertReg api = Retroserver.getClient().create(APIInsertReg.class);
        Call<ResponsModel> getdata = api.bokinganTempat(key1);
        getdata.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                pd.hide();
                Log.d("RETRO", "RESPONSE : " + response.body().getKode());
                mItems = response.body().getResult();

                mAdapter = new bokingan(boking.this,mItems);
                mRecycler.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponsModel> call, Throwable t) {
                pd.hide();
                Log.d("RETRO", "FAILED : respon gagal ");
            }
        });
    }
    public void backpublick (View v){
        Intent back = new Intent(boking.this, wisataumum.class);
        startActivity(back);
    }
    public void delete (View v){
        String key1 = keys.getText().toString();
        APIInsertReg api = Retroserver.getClient().create(APIInsertReg.class);
        Call<ResponsModel> getdata = api.deleteBooking(key1);
        getdata.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                Log.d("RETRO", "response : " + response.body().toString());
                String kode = response.body().getKode();
                if (kode.equals("1")) {
                    Intent booking = new Intent(boking.this, boking.class);
                    startActivity(booking);
                    Toast.makeText(boking.this, "Success Delete All Booking..", Toast.LENGTH_SHORT).show();
                }
        }
            public void onFailure(Call<ResponsModel> call, Throwable t) {
                Log.d("RETRO", "Falure : " + "Riquest Failed");
            }
        });
    }
}