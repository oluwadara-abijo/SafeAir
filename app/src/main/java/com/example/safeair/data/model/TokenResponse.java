package com.example.safeair.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TokenResponse implements Parcelable {

    //Fields
    @SerializedName("access_token")
    private final String access_token;
    @SerializedName("token_type")
    private final String token_type;
    @SerializedName("expires_in")
    private final int expires_in;

    //Parcelable implementation
    private TokenResponse(Parcel in) {
        access_token = in.readString();
        token_type = in.readString();
        expires_in = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(access_token);
        dest.writeString(token_type);
        dest.writeInt(expires_in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TokenResponse> CREATOR = new Creator<TokenResponse>() {
        @Override
        public TokenResponse createFromParcel(Parcel in) {
            return new TokenResponse(in);
        }

        @Override
        public TokenResponse[] newArray(int size) {
            return new TokenResponse[size];
        }
    };

    //Getters
    public String getAccessToken() {
        return access_token;
    }
}
