package com.example.safeair.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class AirportsObject implements Parcelable {

    //Field
    @SerializedName("Airports")
    private final AirportObject airportObject;

    //Parcelable implementation
    private AirportsObject(Parcel in) {
        airportObject = in.readParcelable(AirportObject.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(airportObject, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AirportsObject> CREATOR = new Creator<AirportsObject>() {
        @Override
        public AirportsObject createFromParcel(Parcel in) {
            return new AirportsObject(in);
        }

        @Override
        public AirportsObject[] newArray(int size) {
            return new AirportsObject[size];
        }
    };

    //Getter
    public AirportObject getAirportObject() {
        return airportObject;
    }
}
