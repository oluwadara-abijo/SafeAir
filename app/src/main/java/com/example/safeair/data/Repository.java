package com.example.safeair.data;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.safeair.InjectorUtils;
import com.example.safeair.data.model.Airport;
import com.example.safeair.data.network.NetworkDataSource;

import java.util.List;

public class Repository {

    private static final String LOG_TAG = Repository.class.getSimpleName();

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static Repository sInstance;

    //Constructor
    private Repository() {
    }

    //Singleton
    public synchronized static Repository getInstance(){
        Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new Repository();
                Log.d(LOG_TAG, "Made new repository");
            }
        }
        return sInstance;
    }

    //Network related operations
    public LiveData<List<Airport>> getAirports(String lang, String limit, boolean operated) {
        NetworkDataSource networkDataSource = InjectorUtils.provideNetworkDataSource();
        return networkDataSource.getAirports(lang, limit, operated);
    }
}
