package com.example.safeair.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.safeair.R;
import com.example.safeair.data.model.ScheduleObject;

import java.util.List;

public class ScheduleActivity extends AppCompatActivity implements ScheduleAdapter.ItemClickListener {

    public static final String EXTRA_SCHEDULE = "schedules";
    private static final String LOG_TAG = ScheduleActivity.class.getSimpleName();

    private List<ScheduleObject> mScheduleObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_SCHEDULE)) {
            mScheduleObjects = intent.getParcelableArrayListExtra(EXTRA_SCHEDULE);
            Log.d(LOG_TAG, mScheduleObjects.get(0).getFlights().get(0).getDepartureFlight().getAirportCode());
            Log.d(LOG_TAG, mScheduleObjects.get(0).getFlights().get(0).getArrivalFlight().getAirportCode());
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        //Get an instance of LinearLayoutManager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        //Associate the LayoutManager with the RecyclerView
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setHasFixedSize(true);

        ScheduleAdapter mAdapter = new ScheduleAdapter(mScheduleObjects, this);

        //Attach the adapter to the RecyclerView
        recyclerView.setAdapter(mAdapter);

        mAdapter.setFlights(mScheduleObjects);
    }

    @Override
    public void onItemClickListener(ScheduleObject scheduleObject) {

    }
}
