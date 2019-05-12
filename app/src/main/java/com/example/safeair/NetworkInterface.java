package com.example.safeair;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

interface NetworkInterface {
    @POST("api/Token")
    Call<TokenResponse> postToken(@Query("apiId") String apiId, @Query("auth_flow") String auth_flow,
    @Query("client_id") String client_id, @Query("client_secret") String client_secret);
}
