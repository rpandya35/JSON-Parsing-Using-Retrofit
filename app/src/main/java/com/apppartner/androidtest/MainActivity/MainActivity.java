package com.apppartner.androidtest.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.apppartner.androidtest.R;
import com.apppartner.androidtest.animation.AnimationActivity;
import com.apppartner.androidtest.chat.ChatActivity;
import com.apppartner.androidtest.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.activity_main_title);
        setContentView(R.layout.activity_main);

    }

    public void onChatClicked(View v) {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }

    public void onLoginClicked(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onAnimationClicked(View v) {
        Intent intent = new Intent(this, AnimationActivity.class);
        startActivity(intent);
    }
}