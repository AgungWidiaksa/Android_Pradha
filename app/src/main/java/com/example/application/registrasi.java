package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.application.API.APIInsertReg;
import com.example.application.API.Retroserver;
import com.example.application.model.ResponsModel;
import com.example.application.penyedia.penyedia1;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registrasi extends AppCompatActivity {
    EditText Fullname,Country,Address,Age,Email,HP,Username,Password;
    Button Submit;
    RadioGroup Radiogroup, Radiogroup1;
    RadioButton Radiobutton, Radiobutton1;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrasi);

        Fullname = (EditText) findViewById(R.id.fullname);
        Country = (EditText) findViewById(R.id.country);
        Address = (EditText) findViewById(R.id.address);
        Age = (EditText) findViewById(R.id.age);
        Email = (EditText) findViewById(R.id.email);
        HP = (EditText) findViewById(R.id.hp);
        Username = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);
        Radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        Radiogroup1 = (RadioGroup) findViewById(R.id.radiogroup1);
        Submit = (Button) findViewById(R.id.submit);
        pd = new ProgressDialog(this);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("send data ...");
                pd.setCancelable(false);
                pd.show();

                String fullnames = Fullname.getText().toString();
                String countrys = Country.getText().toString();
                String addresss = Address.getText().toString();
                String ages = Age.getText().toString();
                String emails = Email.getText().toString();
                String hps = HP.getText().toString();
                String usernames = Username.getText().toString();
                String passwords = Password.getText().toString();
                int radiobutton = Radiogroup.getCheckedRadioButtonId();
                Radiobutton = (RadioButton) findViewById(radiobutton);
                //Toast.makeText(getBaseContext(),"Anda Memilih Warna " + Radiobutton.getText(),Toast.LENGTH_SHORT).show();
                String radiogroups = Radiobutton.getText().toString();
                int radiobutton1 = Radiogroup1.getCheckedRadioButtonId();
                Radiobutton1 = (RadioButton) findViewById(radiobutton1);
                //Toast.makeText(getBaseContext(),"Anda Memilih Warna " + Radiobutton1.getText(),Toast.LENGTH_SHORT).show();
                String radiogroups1 = Radiobutton1.getText().toString();
                APIInsertReg API = Retroserver.getClient().create(APIInsertReg.class);
                Call<ResponsModel> sendbio = API.sendBiodata(fullnames, countrys, addresss,ages,emails,hps,usernames,passwords,radiogroups,radiogroups1);
                sendbio.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pd.hide();
                        Log.d("RETRO", "response : " + response.body().toString());
                        String kode = response.body().getKode();
                        if (kode.equals("1")) {
                            Toast.makeText(registrasi.this, "Sukses Registrasi..", Toast.LENGTH_SHORT).show();
                            Intent back = new Intent(registrasi.this, login.class);
                            startActivity(back);
                        }
                        else if (kode.equals("3")) {
                            Toast.makeText(registrasi.this, "Username sudah terdaftar..", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(registrasi.this, "Data gagal ditambahkan..", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        pd.hide();
                        Log.d("RETRO", "Falure : " + "Riquest Tidak Falid");
                    }
                });
            }
        });
    }
    public void back_login (View v){
        Intent back = new Intent(registrasi.this, login.class);
        startActivity(back);
    }
}