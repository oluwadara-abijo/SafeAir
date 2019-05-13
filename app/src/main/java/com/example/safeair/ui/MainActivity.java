package com.example.safeair.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.safeair.R;
import com.example.safeair.data.model.AirportResponse;
import com.example.safeair.data.network.LufthansaClient;
import com.example.safeair.data.network.NetworkInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_TOKEN = "token";
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    //Token from Splash Activity
    public static String TOKEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the intent that started activity
        Intent intent = getIntent();
        if (intent != null) {
            TOKEN = intent.getStringExtra(EXTRA_TOKEN);
        }

        NetworkInterface networkInterface = LufthansaClient.getClient();
        //Define query parameters
        String lang = "EN";
        String limit = "100";

        networkInterface.getAirports(lang, limit, true).enqueue(new Callback<AirportResponse>() {
            @Override
            public void onResponse(@NonNull Call<AirportResponse> call, @NonNull Response<AirportResponse> response) {
                Log.d(LOG_TAG, response.message());
            }

            @Override
            public void onFailure(@NonNull Call<AirportResponse> call, @NonNull Throwable t) {
                Log.d(LOG_TAG, t.getMessage());
            }
        });

    }
}
