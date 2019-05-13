package com.example.safeair.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AirportResponse implements Parcelable {

    //Field
    @SerializedName("AirportResource")
    private final AirportsObject airportResource;

    //Parcelable implementation
    private AirportResponse(Parcel in) {
        airportResource = in.readParcelable(AirportsObject.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(airportResource, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AirportResponse> CREATOR = new Creator<AirportResponse>() {
        @Override
        public AirportResponse createFromParcel(Parcel in) {
            return new AirportResponse(in);
        }

        @Override
        public AirportResponse[] newArray(int size) {
            return new AirportResponse[size];
        }
    };

    //Getter
    public AirportsObject getAirportResource() {
        return airportResource;
    }
}
