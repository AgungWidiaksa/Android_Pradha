package com.example.application.API;

import com.example.application.model.ResponsModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APILogin {
    @FormUrlEncoded
    @POST("select_login.php")
    Call<ResponsModel> sendLoginUser(@Field("username_log") String username_log,
                                     @Field("passwords_log") String passwords_log,
                                     @Field("radiogroup_log") String radiogroup_log);
}
