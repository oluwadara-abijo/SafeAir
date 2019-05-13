package com.example.safeair.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.safeair.data.Repository;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Repository mRepository;

    public MainViewModelFactory(Repository repository) {
        this.mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainViewModel(mRepository);
    }

}