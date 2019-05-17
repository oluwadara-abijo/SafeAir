package com.example.safeair.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.safeair.InjectorUtils;
import com.example.safeair.R;
import com.example.safeair.data.model.Airport;
import com.example.safeair.data.model.ScheduleObject;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private ProgressBar progressBar;
    private Button searchButton;

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
        progressBar = findViewById(R.id.progress_bar);
        searchButton = findViewById(R.id.button);

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
            progressBar.setVisibility(View.GONE);
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
        String dateTime ="2019-06-15";

        if (TextUtils.isEmpty(origin) || TextUtils.isEmpty(destination)) {
            Snackbar.make(searchButton, "You must select both origin and destination", Snackbar.LENGTH_SHORT).show();
        } else if (origin.equals(destination)) {
            Snackbar.make(searchButton, "Choose a different origin or destination", Snackbar.LENGTH_SHORT).show();
        } else if (isNetworkAvailable()) {
            progressBar.setVisibility(View.VISIBLE);
            searchButton.setVisibility(View.GONE);
            mViewModel.getFlightSchedules(origin, destination, dateTime).observe(this, scheduleObjects -> {
                mScheduleObjects = scheduleObjects;
                if (mScheduleObjects != null) {
                    Log.d(LOG_TAG, mScheduleObjects.get(0).getFlights().get(0).getDepartureFlight().getAirportCode());
                    Log.d(LOG_TAG, mScheduleObjects.get(0).getFlights().get(0).getArrivalFlight().getAirportCode());
                    Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
                    intent.putParcelableArrayListExtra(ScheduleActivity.EXTRA_SCHEDULE, (ArrayList<? extends Parcelable>) mScheduleObjects);

                    startActivity(intent);
                } else {
                    Snackbar.make(searchButton, "No flight schedules", Snackbar.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
                searchButton.setVisibility(View.VISIBLE);
            });
        } else {
            Snackbar.make(searchButton, "Check your internet connection", Snackbar.LENGTH_SHORT).show();
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = Objects.requireNonNull(connectivityManager)
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}
