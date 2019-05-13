package com.example.safeair.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.example.safeair.InjectorUtils;
import com.example.safeair.R;
import com.example.safeair.data.model.Airport;
import com.example.safeair.data.model.ScheduleObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_TOKEN = "token";

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    //Token from Splash Activity
    public static String TOKEN;

    //ViewModel object
    private MainViewModel mViewModel;

    //List of airport objects
    private List<Airport> mAirports;
    private List<ScheduleObject> mScheduleObjects;

    private String[] airportCodes = new String[]{};
    private List<String> airportCodesList = new ArrayList<>();

    //UI elements
    private AutoCompleteTextView originTextView;
    private AutoCompleteTextView destinationTextView;
    private TextView dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the intent that started activity
        Intent intent = getIntent();
        if (intent != null) {
            TOKEN = intent.getStringExtra(EXTRA_TOKEN);
        }

        //Bind views
        originTextView = findViewById(R.id.tv_origin);
        destinationTextView = findViewById(R.id.tv_destination);
        dateTextView = findViewById(R.id.tv_departureDate);
        Button searchButton = findViewById(R.id.button);

        //Setup view model
        MainViewModelFactory factory = InjectorUtils.provideMainViewModelFactory();
        mViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);

        getAirports();

        searchButton.setOnClickListener(v -> getFlightSchedules());
    }

    private void getAirports() {
        String lang = "EN";
        String limit = "100";
        mViewModel.getAirports(lang, limit).observe(this, airports -> {
            mAirports = airports;
            if (mAirports != null) {
                for (Airport airport : mAirports) {
                    airportCodesList.add(airport.getAirportCode());
                }
                airportCodes = airportCodesList.toArray(new String[0]);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_dropdown_item_1line, airportCodes);
                originTextView.setAdapter(adapter);
                destinationTextView.setAdapter(adapter);
                Log.d(LOG_TAG, String.valueOf(airportCodes.length));
            }
        });
    }

    private void getFlightSchedules() {
        String origin = originTextView.getText().toString();
        String destination = destinationTextView.getText().toString();
        String dateTime = dateTextView.getText().toString();
        mViewModel.getFlightSchedules(origin, destination, dateTime).observe(this, scheduleObjects -> {
            mScheduleObjects = scheduleObjects;
            if (mScheduleObjects != null) {
                Log.d(LOG_TAG, mScheduleObjects.get(0).getFlights().get(0).getDepartureFlight().getAirportCode());
                Log.d(LOG_TAG, mScheduleObjects.get(0).getFlights().get(0).getArrivalFlight().getAirportCode());
            }
        });
    }
}
