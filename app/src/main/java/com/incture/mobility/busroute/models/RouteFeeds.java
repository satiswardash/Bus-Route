package com.incture.mobility.busroute.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by satiswardash on 29/01/18.
 */

public class RouteFeeds {

    @SerializedName("routes")
    public List<Route> routes;
}
