package com.example.safeair;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NetworkInterface {
    @POST("api/Token")
    Call<TokenResponse> postToken(@Body TokenRequest tokenRequest);
}
