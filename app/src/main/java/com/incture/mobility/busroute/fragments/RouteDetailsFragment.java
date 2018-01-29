package com.incture.mobility.busroute.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.incture.mobility.busroute.R;
import com.incture.mobility.busroute.adapters.StopsAdapter;
import com.incture.mobility.busroute.models.Route;
import com.incture.mobility.busroute.models.Stops;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RouteDetailsFragment extends Fragment {

    private Route mRouteDetails;
    private TextView mTitle;
    private TextView mDescription;
    private ImageView mImage;
    private ImageView mAccessibilityImage;
    private RecyclerView stopsRecyclerView;

    private int container;


    /**
     *
     */
    public RouteDetailsFragment() {
        // Required empty public constructor
    }

    /**
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        container = R.id.am_main_frame;
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

        if (getArguments().containsKey(RouteListFragment.DATA)) {
            mRouteDetails = getArguments().getParcelable(RouteListFragment.DATA);
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_route_details, container, false);
        initFragmentLayout(view);
        return view;
    }

    /**
     *
     * @param view
     */
    private void initFragmentLayout(View view) {
        mTitle = view.findViewById(R.id.rd_title);
        mDescription = view.findViewById(R.id.rd_description);
        mImage = view.findViewById(R.id.rd_imageView);
        mAccessibilityImage = view.findViewById(R.id.rd_is_accessible_imageView2);
        stopsRecyclerView = view.findViewById(R.id.stops_recycler_view);

        AppBarLayout appBarLayout = getActivity().findViewById(R.id.appbar);
        appBarLayout.setVisibility(View.GONE);

    }

    /**
     *
     */
    @Override
    public void onStart() {
        super.onStart();
        bindDataModel();
    }

    /**
     *
     */
    private void bindDataModel() {
        if (mRouteDetails != null) {
            mTitle.setText(mRouteDetails.name);
            mDescription.setText(mRouteDetails.description);
            Picasso.with(getContext()).load(mRouteDetails.image)
                    .placeholder(getActivity().getResources().getDrawable(R.drawable.ic_launcher_background))
                    .into(mImage);
            if (mRouteDetails.accessible.equals("true")) {
                mAccessibilityImage.setVisibility(View.VISIBLE);
            } else {
                mAccessibilityImage.setVisibility(View.INVISIBLE);
            }
            setStopsView(mRouteDetails.stops);
        }
    }

    /**
     *
     * @param list
     */
    private void setStopsView(List<Stops> list) {
        StopsAdapter adapter = new StopsAdapter(getContext(), list);
        stopsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        stopsRecyclerView.setAdapter(adapter);
    }
}
