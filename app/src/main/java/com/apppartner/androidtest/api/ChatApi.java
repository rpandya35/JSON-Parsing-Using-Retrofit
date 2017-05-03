package com.apppartner.androidtest.api;

import com.apppartner.androidtest.chat.ChatData;

import retrofit2.Call;
import retrofit2.http.POST;

public interface ChatApi {
    String BASE_URL = "http://dev3.apppartner.com";

    @POST("/AppPartnerDeveloperTest/scripts/chat_log.php")
    Call<ChatData> getChatData();
}
