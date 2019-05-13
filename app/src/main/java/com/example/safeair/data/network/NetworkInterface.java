package com.example.safeair.data.network;

import com.example.safeair.data.model.AirportResponse;
import com.example.safeair.data.model.TokenResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NetworkInterface {
    @POST("api/Token")
    Call<TokenResponse> postToken(@Query("apiId") String apiId, @Query("auth_flow") String auth_flow,
                                  @Query("client_id") String client_id, @Query("client_secret") String client_secret);

    @GET("references/airports")
    Call<AirportResponse> getAirports(@Query("lang") String language, @Query("limit") String limit,
                                      @Query("LHoperated") boolean hOperated);
}