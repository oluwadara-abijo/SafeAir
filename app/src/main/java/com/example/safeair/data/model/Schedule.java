package com.example.safeair.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Schedule implements Parcelable {

    //Field
    @SerializedName("Schedule")
    private final List<ScheduleObject> schedules;

    //Parcelable implementation
    private Schedule(Parcel in) {
        schedules = in.createTypedArrayList(ScheduleObject.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(schedules);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Schedule> CREATOR = new Creator<Schedule>() {
        @Override
        public Schedule createFromParcel(Parcel in) {
            return new Schedule(in);
        }

        @Override
        public Schedule[] newArray(int size) {
            return new Schedule[size];
        }
    };

    //Getter
    public List<ScheduleObject> getSchedules() {
        return schedules;
    }
}
