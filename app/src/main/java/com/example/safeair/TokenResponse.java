package com.example.safeair;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TokenResponse implements Parcelable {

    //Fields
    @SerializedName("success")
    private final boolean success;
    @SerializedName("result")
    private final TokenResult tokenResult;

    //Parcelable implementation
    private TokenResponse(Parcel in) {
        success = in.readByte() != 0;
        tokenResult = in.readParcelable(TokenResult.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (success ? 1 : 0));
        dest.writeParcelable(tokenResult, flags);
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

    TokenResult getTokenResult() {
        return tokenResult;
    }
}
