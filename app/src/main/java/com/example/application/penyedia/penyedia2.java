package com.example.application.penyedia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.API.APIInsertReg;
import com.example.application.API.Retroserver;
import com.example.application.R;
import com.example.application.Util.SessionManager;
import com.example.application.adapter.adapteradmin1;
import com.example.application.login;
import com.example.application.model.ResponsModel;
import com.example.application.model.SelectModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class penyedia2 extends AppCompatActivity {
    RecyclerView mRecycler;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    
    private List<SelectModel> mItems = new ArrayList<>();
    SessionManager sm;
    TextView keys;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.penyedia2);
        keys = (TextView) findViewById(R.id.key);
        sm = new SessionManager(penyedia2.this);
        HashMap<String, String> map = sm.getDetailLogin();
        keys.setText(map.get(sm.KEY));
        pd = new ProgressDialog(this);
        Button tambah = (Button) findViewById(R.id.tambahwisata);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("send data ...");
                pd.setCancelable(false);
                pd.show();
                Intent tambahwisata = new Intent(penyedia2.this, com.example.application.penyedia.rekomendasi_wisata.class);
                startActivity(tambahwisata);
            }
        });

        pd.setMessage("Loading ...");
        pd.setCancelable(false);
        pd.show();
        mRecycler = (RecyclerView) findViewById(R.id.penyedia2);
        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(mManager);
        String key = keys.getText().toString();
        APIInsertReg api = Retroserver.getClient().create(APIInsertReg.class);
        Call<ResponsModel> getdata = api.sendadmin(key);
        getdata.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                pd.hide();
                Log.d("RETRO", "RESPONSE : " + response.body().getKode());
                mItems = response.body().getResult();

                mAdapter = new adapteradmin1(penyedia2.this,mItems);
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
    public void backpenyedia2 (View v){
        Intent back = new Intent(penyedia2.this, penyedia1.class);
        startActivity(back);
    }
}