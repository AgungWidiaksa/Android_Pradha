package com.example.application.API;

import com.example.application.model.ResponsModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIInsertReg {
    @FormUrlEncoded
    @POST("insert_identitas1.php")
    Call<ResponsModel> sendBiodata(@Field("fullnames") String fullnames,
                                   @Field("countrys") String countrys,
                                   @Field("addresss") String addresss,
                                   @Field("ages") String ages,
                                   @Field("emails") String emails,
                                   @Field("hps") String hps,
                                   @Field("usernames") String usernames,
                                   @Field("passwords") String passwords,
                                   @Field("radiogroups") String radiogroups,
                                   @Field("radiogroups1") String radiogroups1);

    @GET("select_tempatwisata.php")
    Call<ResponsModel> getBiodata();

    @GET("select_tempatwisata1.php")
    Call<ResponsModel> getBiodata1();

    @GET("select_tempatwisata_all.php")
    Call<ResponsModel> getBiodata2();

    @FormUrlEncoded
    @POST("select_tempatwisata_admin.php")
    Call<ResponsModel> sendadmin(@Field("key") String key);

    @Multipart
    @POST("update_tempat.php")
    Call<ResponsModel> sendUpdateTempat(@Part("namatempat") RequestBody namatempat,
                                        @Part("namatempat1") RequestBody namatempat1,
                                        @Part("deskripsi") RequestBody deskripsi,
                                        @Part("waktu") RequestBody waktu,
                                        @Part("alamat") RequestBody alamat,
                                        @Part("harga") RequestBody harga,
                                        @Part("persyaratan") RequestBody persyaratan,
                                        @Part("jenis_tempat") RequestBody jenis_tempat,
                                        @Part("key") RequestBody key);
    @Multipart
    @POST("update_gambar_tempat.php")
    Call<ResponsModel> sendUpdateTempat1(@Part("key") RequestBody key,
                                         @Part MultipartBody.Part image);

    @FormUrlEncoded
    @POST("delete_tempat.php")
    Call<ResponsModel> sendDeleteTempat(@Field("key") String key);

    @FormUrlEncoded
    @POST("boking_tempat.php")
    Call<ResponsModel> bokingTempat(@Field("key1") String key1,
                                    @Field("key2") String key2);

    @FormUrlEncoded
    @POST("bokingan_tempat.php")
    Call<ResponsModel> bokinganTempat(@Field("key1") String key1);

    @FormUrlEncoded
    @POST("cancel_booking.php")
    Call<ResponsModel> cancelBooking(@Field("key1") String key1,
                                    @Field("key2") String key2);

    @FormUrlEncoded
    @POST("monitoring.php")
    Call<ResponsModel> monitoringAdmin(@Field("key1") String key1,
                                     @Field("key2") String key2);

    @FormUrlEncoded
    @POST("delete_bokingan.php")
    Call<ResponsModel> deleteBooking(@Field("key1") String key1);
}
