package com.example.safeair.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TotalJourney implements Parcelable {

    //Field
    @SerializedName("Duration")
    private final String duration;

    //Parcelable implementation
    private TotalJourney(Parcel in) {
        duration = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(duration);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TotalJourney> CREATOR = new Creator<TotalJourney>() {
        @Override
        public TotalJourney createFromParcel(Parcel in) {
            return new TotalJourney(in);
        }

        @Override
        public TotalJourney[] newArray(int size) {
            return new TotalJourney[size];
        }
    };

    //Getter
    public String getDuration() {
        return duration;
    }
}
