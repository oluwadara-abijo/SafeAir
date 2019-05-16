package com.example.safeair.data.network;

import com.example.safeair.data.model.AirportResponse;
import com.example.safeair.data.model.ScheduleResponse;
import com.example.safeair.data.model.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkInterface {
    @FormUrlEncoded
    @POST("oauth/token")
    Call<TokenResponse> postToken(@Field("Content-Type") String content_type,
                                  @Field("client_id") String client_id,
                                  @Field("client_secret") String client_secret,
                                  @Field("grant_type") String grant_type);

    @GET("references/airports")
    Call<AirportResponse> getAirports(@Query("lang") String language, @Query("limit") String limit,
                                      @Query("LHoperated") boolean hOperated);

    @GET("operations/schedules/{origin}/{destination}/{fromDateTime}")
    Call<ScheduleResponse> getSchedules(@Path("origin") String origin, @Path("destination") String destination,
                                        @Path("fromDateTime") String fromDateTime);
}
