package com.example.safeair.data.network;

import com.example.safeair.ui.MainActivity;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LufthansaClient {

    private static final String BASE_URL = "https://api.lufthansa.com/v1/";
    private static final String TOKEN = "Bearer " + MainActivity.TOKEN;

    private static NetworkInterface client = null;

    public static NetworkInterface getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(interceptor);
        httpClient.addInterceptor(chain -> {
            Request request = chain.request().newBuilder()
                    .addHeader("Authorization", TOKEN)
                    .addHeader("Accept", "application/json")
                    .build();
            return chain.proceed(request);
        });

        if (client == null) {
            client = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL).client(httpClient.build()).build()
                    .create(NetworkInterface.class);

        }
        return client;

    }
}
