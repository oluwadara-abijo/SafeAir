package com.example.safeair.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ScheduledTimeLocal implements Parcelable {

    //Field
    @SerializedName("DateTime")
    private final String dateTime;

    //Parcelable implementation
    private ScheduledTimeLocal(Parcel in) {
        dateTime = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dateTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ScheduledTimeLocal> CREATOR = new Creator<ScheduledTimeLocal>() {
        @Override
        public ScheduledTimeLocal createFromParcel(Parcel in) {
            return new ScheduledTimeLocal(in);
        }

        @Override
        public ScheduledTimeLocal[] newArray(int size) {
            return new ScheduledTimeLocal[size];
        }
    };

    //Getter
    public String getDateTime() {
        return dateTime;
    }
}
