package com.example.safeair.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AirportPosition implements Parcelable {

    //Fields
    @SerializedName("Coordinate")
    private final AirportCoordinate coordinate;

    //Parcelable implementation
    private AirportPosition(Parcel in) {
        coordinate = in.readParcelable(AirportCoordinate.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(coordinate, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AirportPosition> CREATOR = new Creator<AirportPosition>() {
        @Override
        public AirportPosition createFromParcel(Parcel in) {
            return new AirportPosition(in);
        }

        @Override
        public AirportPosition[] newArray(int size) {
            return new AirportPosition[size];
        }
    };

    //Getters
    public AirportCoordinate getCoordinate() {
        return coordinate;
    }
}
