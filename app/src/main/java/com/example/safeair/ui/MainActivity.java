package com.example.safeair.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.safeair.InjectorUtils;
import com.example.safeair.R;
import com.example.safeair.data.model.Airport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_TOKEN = "token";

    //Token from Splash Activity
    public static String TOKEN;

    //ViewModel object
    private MainViewModel mViewModel;

    //List of airport objects
    private List<Airport> mAirports;

    private String[] airportCodes = new String[]{};
    private List<String> airportCodesList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the intent that started activity
        Intent intent = getIntent();
        if (intent != null) {
            TOKEN = intent.getStringExtra(EXTRA_TOKEN);
        }

        //Setup view model
        MainViewModelFactory factory = InjectorUtils.provideMainViewModelFactory();
        mViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);

        getAirports();
    }

    private void getAirports() {
        String lang = "EN";
        String limit = "100";
        mViewModel.getAirports(lang, limit, true).observe(this, airports -> {
            mAirports = airports;
            if (mAirports != null) {
                for (Airport airport : mAirports) {
                    airportCodesList.add(airport.getAirportCode());
                }
                airportCodes = airportCodesList.toArray(new String[0]);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_dropdown_item_1line, airportCodes);
                AutoCompleteTextView textView = findViewById(R.id.tv_origin);
                textView.setAdapter(adapter);
            }
        });
    }
}
