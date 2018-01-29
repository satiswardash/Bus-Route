package com.incture.mobility.busroute.rest;

import com.incture.mobility.busroute.utils.WebUrls;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by satiswardash on 29/01/18.
 */

public class WebBuilder {

    public static ApiEndpointsService build(){
        Retrofit retrofit =
                new Retrofit.Builder().
                        baseUrl(WebUrls.BASE_URL).
                        addConverterFactory(GsonConverterFactory.create()).
                        addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                        build();
        return retrofit.create(ApiEndpointsService.class);
    }
}
