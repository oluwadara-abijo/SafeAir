package com.example.safeair.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AirportName implements Parcelable {

    //Fields
    @SerializedName("$")
    private final String name;

    //Parcelable implementation
    private AirportName(Parcel in) {
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AirportName> CREATOR = new Creator<AirportName>() {
        @Override
        public AirportName createFromParcel(Parcel in) {
            return new AirportName(in);
        }

        @Override
        public AirportName[] newArray(int size) {
            return new AirportName[size];
        }
    };

    //Getter
    public String getName() {
        return name;
    }
}
