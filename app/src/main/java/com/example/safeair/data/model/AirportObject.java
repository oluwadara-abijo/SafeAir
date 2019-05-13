package com.example.safeair.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AirportObject implements Parcelable {

    //Field
    @SerializedName("Airport")
    private final List<Airport> airports;

    //Parcelable implementation
    private AirportObject(Parcel in) {
        airports = in.createTypedArrayList(Airport.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(airports);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AirportObject> CREATOR = new Creator<AirportObject>() {
        @Override
        public AirportObject createFromParcel(Parcel in) {
            return new AirportObject(in);
        }

        @Override
        public AirportObject[] newArray(int size) {
            return new AirportObject[size];
        }
    };

    //Getter
    public List<Airport> getAirports() {
        return airports;
    }
}
