package com.apppartner.androidtest.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.apppartner.androidtest.R;
import com.apppartner.androidtest.api.LogInApi;
import com.apppartner.androidtest.MainActivity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements Callback<ResponseBody> {
    @BindView(R.id.username)
    EditText userName;
    @BindView(R.id.password)
    EditText password;
    private long startTime, totalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setTitle(this.getResources().getString(R.string.login));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

    }


    public void onLogin(View view) {

        // The Retrofit class generates an implementation of the LogInApi
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LogInApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        startTime = System.currentTimeMillis();
        LogInApi logInApi = retrofit.create(LogInApi.class);

        // Get the Username and Password , convert those into strings.
        Call<ResponseBody> logInCall = logInApi.logIn(userName.getText().toString(), password.getText().toString());
        logInCall.enqueue(this);
    }

    private void createAlertDialog(Context context, final Response<ResponseBody> response) {
        totalTime = System.currentTimeMillis() - startTime;
        new AlertDialog.Builder(context)
                .setTitle(context.getResources().getString(R.string.login_alert_title))
                .setMessage(context.getResources().getString(R.string.code) + ": " + response.code()
                        + "\n" + context.getResources().getString(R.string.message) + ": " + response.message()
                        + "\n" + context.getResources().getString(R.string.time) + ": " + totalTime
                        + context.getResources().getString(R.string.ms))
                .setPositiveButton(LoginActivity.this.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            dialogInterface.cancel();
                        }
                    }
                }).create().show();
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        createAlertDialog(LoginActivity.this, response);
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Log.i("LogIn error", t.getMessage());
    }
}