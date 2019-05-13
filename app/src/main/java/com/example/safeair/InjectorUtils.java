package com.example.safeair;


import android.content.Context;

import com.example.safeair.data.Repository;
import com.example.safeair.data.network.NetworkDataSource;
import com.example.safeair.ui.MainViewModelFactory;

/**
 * Provides static methods to inject the various classes needed for SafeAir
 */
public class InjectorUtils {
    private static Repository provideRepository() {
        return Repository.getInstance();
    }

    public static NetworkDataSource provideNetworkDataSource() {
        AppExecutors executors = AppExecutors.getInstance();
        return NetworkDataSource.getInstance(executors);
    }

    public static MainViewModelFactory provideMainViewModelFactory() {
        Repository repository = provideRepository();
        return new MainViewModelFactory(repository);
    }
}
