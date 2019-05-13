package com.example.safeair.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class FlightDetails implements Parcelable {

    //Fields
    @SerializedName("AirportCode")
    private final String airportCode;
    @SerializedName("ScheduledTimeLocal")
    private final ScheduledTimeLocal scheduledTimeLocal;

    //Parcelable implementation
    private FlightDetails(Parcel in) {
        airportCode = in.readString();
        scheduledTimeLocal = in.readParcelable(ScheduledTimeLocal.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(airportCode);
        dest.writeParcelable(scheduledTimeLocal, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FlightDetails> CREATOR = new Creator<FlightDetails>() {
        @Override
        public FlightDetails createFromParcel(Parcel in) {
            return new FlightDetails(in);
        }

        @Override
        public FlightDetails[] newArray(int size) {
            return new FlightDetails[size];
        }
    };

    //Getters
    public String getAirportCode() {
        return airportCode;
    }

    public ScheduledTimeLocal getScheduledTimeLocal() {
        return scheduledTimeLocal;
    }
}
