package com.apppartner.androidtest.chat;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.apppartner.androidtest.R;
import com.apppartner.androidtest.api.ChatApi;
import com.apppartner.androidtest.chat.ChatData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatActivity extends AppCompatActivity implements Callback<ChatData> {
    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setTitle(this.getResources().getString(R.string.chat));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        setContentView(R.layout.activity_chat);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        chatAdapter = new ChatAdapter(this);
        recyclerView.setAdapter(chatAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false));

        retrieveChatData();
    }

    private void retrieveChatData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ChatApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChatApi chatApi = retrofit.create(ChatApi.class);
        Call<ChatData> chatDataCall = chatApi.getChatData();
        chatDataCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<ChatData> call, Response<ChatData> response) {
        if (response.isSuccessful()) {
            ChatData chatData = response.body();
            if (!chatData.getData().isEmpty()) {
                chatAdapter.setChatLogMessageModelList(chatData.getData());
            }
        }
    }

    @Override
    public void onFailure(Call<ChatData> call, Throwable t) {
        Log.i("Error chat data", t.getMessage());
    }
}