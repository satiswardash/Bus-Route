package com.incture.mobility.busroute.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by satiswardash on 29/01/18.
 */

public class Route implements Parcelable {

    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("description")
    public String description;
    @SerializedName("accessible")
    public String accessible;
    @SerializedName("image")
    public String image;
    @SerializedName("stops")
    public List<Stops> stops;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.accessible);
        dest.writeString(this.image);
        dest.writeList(this.stops);
    }

    public Route() {
    }

    protected Route(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.accessible = in.readString();
        this.image = in.readString();
        this.stops = new ArrayList<Stops>();
        in.readList(this.stops, Stops.class.getClassLoader());
    }

    public static final Parcelable.Creator<Route> CREATOR = new Parcelable.Creator<Route>() {
        @Override
        public Route createFromParcel(Parcel source) {
            return new Route(source);
        }

        @Override
        public Route[] newArray(int size) {
            return new Route[size];
        }
    };
}
