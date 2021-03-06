package com.example.safeair.data.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This is to generate a token from Lufthansa's API
 */
public class TokenClient {

    private static final String BASE_URL = "https://api.lufthansa.com/v1/";
    private static NetworkInterface client = null;

    public static NetworkInterface getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(interceptor);
        if (client == null) {
            client = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL).client(httpClient.build()).build()
                    .create(NetworkInterface.class);

        }
        return client;

    }
}
