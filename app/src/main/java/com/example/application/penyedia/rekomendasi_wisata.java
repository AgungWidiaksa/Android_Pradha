package com.example.application.penyedia;

        import android.Manifest;
        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.database.Cursor;
        import android.graphics.Bitmap;
        import android.net.Uri;
        import android.os.Bundle;
        import android.provider.MediaStore;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.app.ActivityCompat;
        import androidx.core.content.ContextCompat;

        import com.example.application.API.APIInsertReg;
        import com.example.application.API.APIInsertWisata;
        import com.example.application.API.Retroserver;
        import com.example.application.R;
        import com.example.application.Util.SessionManager;
        import com.example.application.login;
        import com.example.application.model.ResponsModel;

        import net.gotev.uploadservice.MultipartUploadRequest;
        import net.gotev.uploadservice.UploadNotificationConfig;

        import java.io.File;
        import java.util.HashMap;
        import java.util.UUID;

        import okhttp3.MediaType;
        import okhttp3.MultipartBody;
        import okhttp3.RequestBody;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

public class rekomendasi_wisata extends AppCompatActivity {
    ImageView imageView;
    EditText NamaWisata,DeskripsiTempat,Waktu,Lokasi,Harga,Aturan;
    TextView keys;
    Button Submit;
    String part_image;
    RadioGroup RadioGroup;
    RadioButton RadioButton;
    ProgressDialog pd;
    SessionManager sm;

    private static final int STORAGE_PERMISSION_CODE=4655;
    private int PICK_IMAGE_RESULT = 1;
    private Uri filepath;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rekomendasi_wisata);


        NamaWisata = (EditText) findViewById(R.id.namawisata);
        DeskripsiTempat = (EditText) findViewById(R.id.deskripsitempat);
        Waktu = (EditText) findViewById(R.id.waktu);
        Lokasi = (EditText) findViewById(R.id.lokasi);
        Harga = (EditText) findViewById(R.id.harga);
        Aturan = (EditText) findViewById(R.id.aturan);
        RadioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        keys = (TextView) findViewById(R.id.key);
        sm = new SessionManager(rekomendasi_wisata.this);
        HashMap<String, String> map = sm.getDetailLogin();
        keys.setText(map.get(sm.KEY));
        imageView = (ImageView) findViewById(R.id.imageView);
        Submit = (Button) findViewById(R.id.submit);
        pd = new ProgressDialog(this);
        pd.setMessage("loading ...");

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               pd.show();
                RequestBody namatempat = RequestBody.create(MediaType.parse("text/plain"), NamaWisata.getText().toString().trim());
                RequestBody deskripsi = RequestBody.create(MediaType.parse("text/plain"), DeskripsiTempat.getText().toString().trim());
                RequestBody waktu = RequestBody.create(MediaType.parse("text/plain"), Waktu.getText().toString().trim());
                RequestBody alamat = RequestBody.create(MediaType.parse("text/plain"), Lokasi.getText().toString().trim());
                RequestBody harga = RequestBody.create(MediaType.parse("text/plain"), Harga.getText().toString().trim());
                RequestBody persyaratan = RequestBody.create(MediaType.parse("text/plain"), Aturan.getText().toString().trim());
                int radiobutton = RadioGroup.getCheckedRadioButtonId();
                RadioButton = (RadioButton) findViewById(radiobutton);
                RequestBody jenistempat = RequestBody.create(MediaType.parse("text/plain"), RadioButton.getText().toString().trim());
                RequestBody key = RequestBody.create(MediaType.parse("text/plain"), keys.getText().toString().trim());
                part_image = getPath(filepath);
                File imagefile = new File(part_image);
                RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"),imagefile);
                MultipartBody.Part partImage = MultipartBody.Part.createFormData("imageupload", imagefile.getName(), reqBody);
                APIInsertWisata API = Retroserver.getClient().create(APIInsertWisata.class);
                Call<ResponsModel> sendbio1 = API.sendBiodata1(namatempat,deskripsi,waktu,alamat,harga,persyaratan,jenistempat,key,partImage);
                sendbio1.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        pd.hide();
                        Log.d("RETRO", "response : " + response.body().toString());
                        String kode = response.body().getKode();
                        if (kode.equals("1")) {
                            Intent rekom = new Intent(rekomendasi_wisata.this, penyedia2.class);
                            startActivity(rekom);
                            Toast.makeText(rekomendasi_wisata.this, "Sukses Rekomendasi..", Toast.LENGTH_SHORT).show();
                        }
                        else if (kode.equals("3")) {
                            Toast.makeText(rekomendasi_wisata.this, "Nama Tempat sudah terdaftar..", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(rekomendasi_wisata.this, "Data gagal ditambahkan..", Toast.LENGTH_SHORT).show();
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

    public void selectImage(View view){
        showFileChooser();
    }
    private void showFileChooser(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"),PICK_IMAGE_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_RESULT && data != null && data.getData() != null){
            filepath= data.getData();
            try{
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                imageView.setImageBitmap(bitmap);
            }
            catch (Exception ex){

            }
        }
    }
    private String getPath(Uri uri){
        Cursor cursor=getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id=cursor.getString(0);
        document_id=document_id.substring(document_id.lastIndexOf(":")+1);
        cursor=getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null,MediaStore.Images.Media._ID+"=?",new String[]{document_id},null);
        cursor.moveToFirst();
        String path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }

    public void backafterreg(View v){
        Intent back = new Intent(rekomendasi_wisata.this, penyedia2.class);
        startActivity(back);
    }
}
