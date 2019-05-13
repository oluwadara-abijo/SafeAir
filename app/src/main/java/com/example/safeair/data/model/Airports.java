package com.example.safeair.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Airports implements Parcelable {

    //Field
    @SerializedName("Airport")
    private final List<Airport> airports;

    //Parcelable implementation
    private Airports(Parcel in) {
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

    public static final Creator<Airports> CREATOR = new Creator<Airports>() {
        @Override
        public Airports createFromParcel(Parcel in) {
            return new Airports(in);
        }

        @Override
        public Airports[] newArray(int size) {
            return new Airports[size];
        }
    };

    //Getter
    public List<Airport> getAirports() {
        return airports;
    }
}
