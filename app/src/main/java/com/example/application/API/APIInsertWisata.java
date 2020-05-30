package com.example.application.API;

import com.example.application.model.ResponsModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIInsertWisata {

    @Multipart
    @POST("insert_identitas2.php")
    Call<ResponsModel> sendBiodata1(@Part("namatempat") RequestBody namatempat,
                                    @Part("deskripsi") RequestBody deskripsi,
                                    @Part("waktu") RequestBody waktu,
                                    @Part("alamat") RequestBody alamat,
                                    @Part("harga") RequestBody harga,
                                    @Part("persyaratan") RequestBody persyaratan,
                                    @Part("jenistempat") RequestBody jenistempat,
                                    @Part("key") RequestBody key,
                                    @Part MultipartBody.Part image);
}
