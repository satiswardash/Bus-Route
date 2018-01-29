package com.incture.mobility.busroute.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by satiswardash on 29/01/18.
 */

public class Stops implements Parcelable {

    @SerializedName("name")
    public String name;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    public Stops() {
    }

    protected Stops(Parcel in) {
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Stops> CREATOR = new Parcelable.Creator<Stops>() {
        @Override
        public Stops createFromParcel(Parcel source) {
            return new Stops(source);
        }

        @Override
        public Stops[] newArray(int size) {
            return new Stops[size];
        }
    };
}
