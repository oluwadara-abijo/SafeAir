package com.example.safeair.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AirportCoordinate implements Parcelable {

    //Fields
    @SerializedName("Longitude")
    private final float longitude;
    @SerializedName("Latitude")
    private final float latitude;

    //Parcelable implementation
    private AirportCoordinate(Parcel in) {
        longitude = in.readFloat();
        latitude = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(longitude);
        dest.writeFloat(latitude);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AirportCoordinate> CREATOR = new Creator<AirportCoordinate>() {
        @Override
        public AirportCoordinate createFromParcel(Parcel in) {
            return new AirportCoordinate(in);
        }

        @Override
        public AirportCoordinate[] newArray(int size) {
            return new AirportCoordinate[size];
        }
    };

    //Getters
    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }
}
