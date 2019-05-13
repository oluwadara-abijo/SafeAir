package com.example.safeair.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.safeair.data.Repository;
import com.example.safeair.data.model.Airport;

import java.util.List;

public class MainViewModel extends ViewModel {
    private final Repository mRepository;

    MainViewModel(Repository repository) {
        mRepository = repository;
    }

    public LiveData<List<Airport>> getAirports(String lang, String limit, boolean operated) {
        return mRepository.getAirports(lang, limit, operated);
    }
}
