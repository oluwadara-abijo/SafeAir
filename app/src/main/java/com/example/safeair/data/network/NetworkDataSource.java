package com.example.safeair.data.network;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.safeair.AppExecutors;
import com.example.safeair.data.model.Airport;
import com.example.safeair.data.model.AirportResponse;
import com.example.safeair.data.model.ScheduleObject;
import com.example.safeair.data.model.ScheduleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkDataSource {

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static final String LOG_TAG = NetworkDataSource.class.getSimpleName();
    private static NetworkDataSource sInstance;

    private final AppExecutors mExecutors;

    //Constructor
    private NetworkDataSource(AppExecutors executors) {
        mExecutors = executors;
    }

    /**
     * Get the singleton for this class
     */
    public static NetworkDataSource getInstance(AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the network data source");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new NetworkDataSource(executors);
                Log.d(LOG_TAG, "Made new network data source");
            }
        }
        return sInstance;
    }


    /**
     * Gets the list of airports from network
     *
     * @param lang     2-letter ISO 3166-1 language code
     * @param limit    Number of records returned per request
     * @param operated Restrict the results to locations with flights operated by LH
     * @return the list of airports
     */
    public LiveData<List<Airport>> getAirports(String lang, String limit, boolean operated) {

        final MutableLiveData<List<Airport>> mutableLiveData = new MutableLiveData<>();

        mExecutors.networkIO().execute(() -> {
            NetworkInterface networkInterface = LufthansaClient.getClient();

            networkInterface.getAirports(lang, limit, operated).enqueue(new Callback<AirportResponse>() {
                @Override
                public void onResponse(@NonNull Call<AirportResponse> call, @NonNull Response<AirportResponse> response) {
                    if (response.body() != null) {
                        mutableLiveData.postValue(response.body().getAirportResource().getAirportObject().getAirports());
                    }
                    Log.d(LOG_TAG, String.valueOf(response));
                }

                @Override
                public void onFailure(@NonNull Call<AirportResponse> call, @NonNull Throwable t) {
                    Log.d(LOG_TAG, "An error occurred while getting airports");

                }
            });
        });
        return mutableLiveData;
    }

    /**
     * Gets the schedule of flights given an origin and a destination airport
     *
     * @param origin      3-letter IATA airport code of origin airport
     * @param destination 3-letter IATA airport code of destination airport
     * @param timeDate    Departure date in the local time of the departure airport
     * @return the list of flight schedules
     */
    public LiveData<List<ScheduleObject>> getFlightSchedules(String origin, String destination, String timeDate) {

        final MutableLiveData<List<ScheduleObject>> mutableLiveData = new MutableLiveData<>();

        mExecutors.networkIO().execute(() -> {
            NetworkInterface networkInterface = LufthansaClient.getClient();

            networkInterface.getSchedules(origin, destination, timeDate).enqueue(new Callback<ScheduleResponse>() {
                @Override
                public void onResponse(@NonNull Call<ScheduleResponse> call, @NonNull Response<ScheduleResponse> response) {
                    if (response.body() != null) {
                        mutableLiveData.postValue(response.body().getSchedule().getSchedules());
                    }
                    Log.d(LOG_TAG, String.valueOf(response));
                }

                @Override
                public void onFailure(@NonNull Call<ScheduleResponse> call, @NonNull Throwable t) {
                    Log.d(LOG_TAG, "An error occurred while getting flight schedules");

                }
            });
        });
        return mutableLiveData;
    }
}
