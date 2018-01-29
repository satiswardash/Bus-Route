package com.incture.mobility.busroute.rest;

import com.incture.mobility.busroute.models.RouteFeeds;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by satiswardash on 29/01/18.
 */

public interface ApiEndpointsService {

    @GET Observable<RouteFeeds> getRouteFeeds (@Url String url );
}
