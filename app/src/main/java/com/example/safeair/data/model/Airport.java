package com.example.safeair.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Airport implements Parcelable {
    @SerializedName("AirportCode")
    private final String airportCode;
    @SerializedName("Position")
    private final AirportPosition airportPosition;
    @SerializedName("CityCode")
    private final String cityCode;
    @SerializedName("CountryCode")
    private final String countryCode;
    @SerializedName("Names")
    private final AirportName name;

    //Parcelable implementation
    private Airport(Parcel in) {
        airportCode = in.readString();
        airportPosition = in.readParcelable(AirportPosition.class.getClassLoader());
        cityCode = in.readString();
        countryCode = in.readString();
        name = in.readParcelable(AirportName.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(airportCode);
        dest.writeParcelable(airportPosition, flags);
        dest.writeString(cityCode);
        dest.writeString(countryCode);
        dest.writeParcelable(name, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Airport> CREATOR = new Creator<Airport>() {
        @Override
        public Airport createFromParcel(Parcel in) {
            return new Airport(in);
        }

        @Override
        public Airport[] newArray(int size) {
            return new Airport[size];
        }
    };

    //Getters
    public String getAirportCode() {
        return airportCode;
    }

    public AirportPosition getAirportPosition() {
        return airportPosition;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public AirportName getName() {
        return name;
    }
}
