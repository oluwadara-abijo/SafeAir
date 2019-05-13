package com.example.safeair.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ScheduleResponse implements Parcelable {

    //Field
    @SerializedName("ScheduleResource")
    private final Schedule schedule;

    //Parcelable implementation
    private ScheduleResponse(Parcel in) {
        schedule = in.readParcelable(Schedule.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(schedule, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ScheduleResponse> CREATOR = new Creator<ScheduleResponse>() {
        @Override
        public ScheduleResponse createFromParcel(Parcel in) {
            return new ScheduleResponse(in);
        }

        @Override
        public ScheduleResponse[] newArray(int size) {
            return new ScheduleResponse[size];
        }
    };

    //Getter
    public Schedule getSchedule() {
        return schedule;
    }
}
