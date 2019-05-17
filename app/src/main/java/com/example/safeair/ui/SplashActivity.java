package com.example.safeair.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.example.safeair.BuildConfig;
import com.example.safeair.R;
import com.example.safeair.data.model.TokenResponse;
import com.example.safeair.data.network.NetworkInterface;
import com.example.safeair.data.network.TokenClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    private static final String LOG_TAG = SplashActivity.class.getSimpleName();

    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        token = getToken();

//        //Get token on a new thread
//        Thread t = new Thread() {
//            public void run() {
//                try {
//                    token = getToken();
//                    //sleep thread for 5 seconds, time in milliseconds
//                    sleep(10000);
//                    if (!TextUtils.isEmpty(token)) {
//                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                        intent.putExtra(MainActivity.EXTRA_TOKEN, token);
//                        startActivity(intent);
//                        finish();
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//
//        //start thread
//        t.start();

    }

    private String getToken() {
        //Define query parameters
        String client_id = BuildConfig.ClientId;
        String client_secret = BuildConfig.ClientSecret;
        String grant_type = "client_credentials";
        String content_type = "application/x-www-form-urlencoded";

        NetworkInterface networkInterface = TokenClient.getClient();

        networkInterface.postToken(content_type, client_id, client_secret, grant_type).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(@NonNull Call<TokenResponse> call, @NonNull Response<TokenResponse> response) {
                if (response.body() != null) {
                    token = response.body().getAccessToken();
                    if (token != null && !TextUtils.isEmpty(token)) {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        intent.putExtra(MainActivity.EXTRA_TOKEN, token);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<TokenResponse> call, @NonNull Throwable t) {
                Log.d(LOG_TAG, "An error occurred while getting token");
            }
        });
        return token;
    }
}
