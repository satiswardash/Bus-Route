package com.incture.mobility.busroute.fragments;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.incture.mobility.busroute.R;
import com.incture.mobility.busroute.adapters.RouteListAdapter;
import com.incture.mobility.busroute.models.Route;
import com.incture.mobility.busroute.models.RouteFeeds;
import com.incture.mobility.busroute.service.RouteFeedService;
import com.incture.mobility.busroute.utils.NetworkUtility;
import com.incture.mobility.busroute.utils.WebUrls;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class RouteListFragment extends Fragment
        implements RouteFeedService.RouteFeedServiceListeners, RouteListAdapter.RouteAdapterListeners{

    public static final String DATA = "item_details";
    private RouteFeedService mRouteFeedService;
    private Activity mActivity;
    private RecyclerView mRoutesRecyclerView;
    private RouteListAdapter mAdapter;

    private int container;

    /**
     *
     */
    public RouteListFragment() {
    }


    /**
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
        container = R.id.am_main_frame;container = R.id.am_main_frame;
    }

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_route_list, container, false);
        initFragmentLayout(view);
        bindRouteListAdapter();

        if (NetworkUtility.hasNetworkAccess(getContext())) {
            mRouteFeedService = new RouteFeedService(this);
            mRouteFeedService.fetchRouteFeeds(WebUrls.GET_ROUTES_URI);
        }

        return view;
    }

    /**
     *
     * @param feeds
     */
    @Override
    public void onRouteFeedsReceived(RouteFeeds feeds) {
        mAdapter.setData(feeds.routes);
        mAdapter.notifyDataSetChanged();
    }

    /**
     *
     * @param error
     */
    @Override
    public void onError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    /**
     *
     * @param view
     */
    private void initFragmentLayout(View view) {
        mRoutesRecyclerView = view.findViewById(R.id.ar_route_list_recycler_view);
        AppBarLayout appBarLayout = getActivity().findViewById(R.id.appbar);
        appBarLayout.setVisibility(View.VISIBLE);
    }

    /**
     *
     */
    private void bindRouteListAdapter() {
        mRoutesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRoutesRecyclerView.setNestedScrollingEnabled(true);

        mAdapter = new RouteListAdapter(getContext(), new ArrayList<Route>(), this);
        mRoutesRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClicked(Route route) {

        Bundle bundle = new Bundle();
        bundle.putParcelable(DATA, route);

        RouteDetailsFragment detailsFragment = new RouteDetailsFragment();
        detailsFragment.setArguments(bundle);

        getFragmentManager()
                .beginTransaction()
                .replace(container, detailsFragment)
                .addToBackStack(RouteDetailsFragment.class.toString())
                .commit();
    }
}
