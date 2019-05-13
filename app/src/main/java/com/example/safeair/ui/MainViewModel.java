package com.example.safeair.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.safeair.data.Repository;
import com.example.safeair.data.model.Airport;
import com.example.safeair.data.model.ScheduleObject;

import java.util.List;

class MainViewModel extends ViewModel {
    private final Repository mRepository;

    MainViewModel(Repository repository) {
        mRepository = repository;
    }

    LiveData<List<Airport>> getAirports(String lang, String limit) {
        return mRepository.getAirports(lang, limit, true);
    }

    LiveData<List<ScheduleObject>> getFlightSchedules(String origin, String destinantion, String timeDate) {
        return mRepository.getFlightSchedules(origin, destinantion, timeDate);
    }
}
