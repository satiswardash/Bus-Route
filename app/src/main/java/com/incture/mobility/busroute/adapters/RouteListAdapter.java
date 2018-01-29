package com.incture.mobility.busroute.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.incture.mobility.busroute.R;
import com.incture.mobility.busroute.models.Route;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by satiswardash on 29/01/18.
 */

public class RouteListAdapter extends RecyclerView.Adapter<RouteListAdapter.ViewHolder> {

    public interface RouteAdapterListeners {
        void onItemClicked(Route route);
    }

    private Context mContext;
    private List<Route> mData;
    private RouteAdapterListeners mListeners;

    public RouteListAdapter(Context mContext, List<Route> mData, Fragment fragment) {
        this.mContext = mContext;
        this.mData = mData;
        mListeners = (RouteAdapterListeners) fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_route_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout view;
        ImageView routeImage;
        TextView routeName;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.layout_item_root);
            routeImage = itemView.findViewById(R.id.lri_route_image);
            routeName = itemView.findViewById(R.id.lri_route_name);
        }

        public void bind(int position) {
            final Route route = mData.get(position);
            routeName.setText(route.name);
            Picasso.with(mContext).load(route.image)
                    .placeholder(mContext.getResources().getDrawable(R.drawable.ic_launcher_background))
                    .into(routeImage);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListeners.onItemClicked(route);
                }
            });
        }
    }

    public List<Route> getData() {
        return mData;
    }

    public void setData(List<Route> mData) {
        this.mData = mData;
    }
}
