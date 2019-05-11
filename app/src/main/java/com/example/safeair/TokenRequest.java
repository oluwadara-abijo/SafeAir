package com.example.safeair;

import android.os.Parcel;
import android.os.Parcelable;

public class TokenRequest implements Parcelable {
    private String apiId;
    private String auth_flow;
    private String client_id;
    private String client_secret;

    //Constructor
    public TokenRequest(String apiId, String auth_flow, String client_id, String client_secret) {
        this.apiId = apiId;
        this.auth_flow = auth_flow;
        this.client_id = client_id;
        this.client_secret = client_secret;
    }

    private TokenRequest(Parcel in) {
        apiId = in.readString();
        auth_flow = in.readString();
        client_id = in.readString();
        client_secret = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(apiId);
        dest.writeString(auth_flow);
        dest.writeString(client_id);
        dest.writeString(client_secret);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TokenRequest> CREATOR = new Creator<TokenRequest>() {
        @Override
        public TokenRequest createFromParcel(Parcel in) {
            return new TokenRequest(in);
        }

        @Override
        public TokenRequest[] newArray(int size) {
            return new TokenRequest[size];
        }
    };
}
