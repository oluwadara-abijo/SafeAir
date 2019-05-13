package com.example.safeair.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScheduleObject implements Parcelable {

    //Field
    @SerializedName("TotalJourney")
    private final TotalJourney totalJourney;
    @SerializedName("Flight")
    private final List<FlightObject> flights;

    //Parcelable implementation
    private ScheduleObject(Parcel in) {
        totalJourney = in.readParcelable(TotalJourney.class.getClassLoader());
        flights = in.createTypedArrayList(FlightObject.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(totalJourney, flags);
        dest.writeTypedList(flights);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ScheduleObject> CREATOR = new Creator<ScheduleObject>() {
        @Override
        public ScheduleObject createFromParcel(Parcel in) {
            return new ScheduleObject(in);
        }

        @Override
        public ScheduleObject[] newArray(int size) {
            return new ScheduleObject[size];
        }
    };

    //Getters
    public TotalJourney getTotalJourney() {
        return totalJourney;
    }

    public List<FlightObject> getFlights() {
        return flights;
    }
}
