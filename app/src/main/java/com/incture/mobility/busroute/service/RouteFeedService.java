package com.incture.mobility.busroute.service;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.incture.mobility.busroute.models.RouteFeeds;
import com.incture.mobility.busroute.rest.ApiEndpointsService;
import com.incture.mobility.busroute.rest.WebBuilder;
import com.incture.mobility.busroute.utils.WebUrls;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by satiswardash on 29/01/18.
 */

public class RouteFeedService {

    /**
     *
     */
    public interface RouteFeedServiceListeners {
        void onRouteFeedsReceived(RouteFeeds feeds);
        void onError(String error);
    }

    private RouteFeedServiceListeners mListeners;

    /**
     *
     * @param fragment
     */
    public RouteFeedService(Fragment fragment) {
        this.mListeners = (RouteFeedServiceListeners) fragment;
    }

    /**
     *
     * @param activity
     */
    public RouteFeedService(Activity activity) {
        this.mListeners = (RouteFeedServiceListeners) activity;
    }

    /**
     *
     * @param url
     */
    public void fetchRouteFeeds(String url) {

        ApiEndpointsService endpointService = WebBuilder.build();
        endpointService.getRouteFeeds(WebUrls.GET_ROUTES_URI)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RouteFeeds>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RouteFeeds value) {
                        mListeners.onRouteFeedsReceived(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mListeners.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
