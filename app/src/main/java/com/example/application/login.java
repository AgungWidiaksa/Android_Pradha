package com.example.application;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.application.API.APILogin;
import com.example.application.API.Retroserver;
import com.example.application.Util.SessionManager;
import com.example.application.model.ResponsModel;
import com.example.application.model.SelectModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {

    private EditText Username, Password;
    private RadioGroup Radiogroup;
    private RadioButton Radiobutton;
    private ProgressDialog pd;
    private SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Username = (EditText) findViewById(R.id.username_log);
        Password = (EditText) findViewById(R.id.password_log);
        Radiogroup = (RadioGroup) findViewById(R.id.radiogroup_log);
        sm = new SessionManager(login.this);
        pd = new ProgressDialog(login.this);
    }

    public void LoginUser(View v){
        pd.setMessage("send data ...");
        pd.setCancelable(false);
        pd.show();

        String username_log = Username.getText().toString();
        String passwords_log = Password.getText().toString();
        APILogin apilogin = Retroserver.getClient().create(APILogin.class);
        int radiobutton = Radiogroup.getCheckedRadioButtonId();
        Radiobutton = (RadioButton) findViewById(radiobutton);
        String radiogroup_log = Radiobutton.getText().toString();
        Call<ResponsModel> login = apilogin.sendLoginUser(username_log, passwords_log, radiogroup_log);
        login.enqueue(new Callback<ResponsModel>() {

            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                pd.hide();
                Log.d("RETRO", "response : " + response.body().toString());
                ResponsModel res = response.body();
                List<SelectModel> user = res.getResult();
                String kode = response.body().getKode();
                if(kode.equals("5")){
                    sm.storeLogin(user.get(0).getKeypradha1());
                    Toast.makeText(login.this, "data berhasil..", Toast.LENGTH_SHORT).show();
                    Intent wisataumum = new Intent(login.this, com.example.application.wisata.wisataumum.class);
                    startActivity(wisataumum);
                }
                else if(kode.equals("6")) {
                    sm.storeLogin(user.get(0).getKeypradha1());
                    Toast.makeText(login.this, "data berhasil..", Toast.LENGTH_SHORT).show();
                    Intent rekomendasi = new Intent(login.this, com.example.application.penyedia.penyedia1.class);
                    startActivity(rekomendasi);
                }
                else if (kode.equals("2")){
                    Toast.makeText(login.this, "password salah..", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(login.this, "Data gagal..", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponsModel> call, Throwable t) {
                pd.hide();
                Log.d("RETRO", "Falure : " + "Riquest Tidak Falid");
            }
        });
    }

    public void RegistrasiUser(View v){
        Intent regis_log = new Intent(login.this,registrasi.class);
        startActivity(regis_log);
    }
}