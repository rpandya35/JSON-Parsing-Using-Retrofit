package com.apppartner.androidtest.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LogInApi {
    String BASE_URL = "http://dev3.apppartner.com";


    // Retrofit turns HTTP API into java interface
    @FormUrlEncoded
    @POST("/AppPartnerDeveloperTest/scripts/login.php")
    Call<ResponseBody> logIn(@Field("username") String username,
                             @Field("password") String password);
}
