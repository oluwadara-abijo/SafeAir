package com.example.safeair.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MarketingCarrier implements Parcelable {

    //Fields
    @SerializedName("AirlineID")
    private final String airlineId;
    @SerializedName("FlightNumber")
    private final int flightNumber;

    //Parcelable implementation
    private MarketingCarrier(Parcel in) {
        airlineId = in.readString();
        flightNumber = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(airlineId);
        dest.writeInt(flightNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MarketingCarrier> CREATOR = new Creator<MarketingCarrier>() {
        @Override
        public MarketingCarrier createFromParcel(Parcel in) {
            return new MarketingCarrier(in);
        }

        @Override
        public MarketingCarrier[] newArray(int size) {
            return new MarketingCarrier[size];
        }
    };

    //Getters
    public String getAirlineId() {
        return airlineId;
    }

    public int getFlightNumber() {
        return flightNumber;
    }
}
