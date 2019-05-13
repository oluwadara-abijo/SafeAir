package com.example.safeair.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class FlightObject implements Parcelable {

    //Fields
    @SerializedName("Departure")
    private final FlightDetails departureFlight;
    @SerializedName("Arrival")
    private final FlightDetails arrivalFlight;
    @SerializedName("MarketingCarrier")
    private final MarketingCarrier marketingCarrier;

    //Parcelable implementation
    private FlightObject(Parcel in) {
        departureFlight = in.readParcelable(FlightDetails.class.getClassLoader());
        arrivalFlight = in.readParcelable(FlightDetails.class.getClassLoader());
        marketingCarrier = in.readParcelable(MarketingCarrier.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(departureFlight, flags);
        dest.writeParcelable(arrivalFlight, flags);
        dest.writeParcelable(marketingCarrier, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FlightObject> CREATOR = new Creator<FlightObject>() {
        @Override
        public FlightObject createFromParcel(Parcel in) {
            return new FlightObject(in);
        }

        @Override
        public FlightObject[] newArray(int size) {
            return new FlightObject[size];
        }
    };

    //Getters
    public FlightDetails getDepartureFlight() {
        return departureFlight;
    }

    public FlightDetails getArrivalFlight() {
        return arrivalFlight;
    }

    public MarketingCarrier getMarketingCarrier() {
        return marketingCarrier;
    }
}
