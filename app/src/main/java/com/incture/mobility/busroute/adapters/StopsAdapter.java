package com.incture.mobility.busroute.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.incture.mobility.busroute.R;
import com.incture.mobility.busroute.models.Stops;

import java.util.List;

/**
 * Created by satiswardash on 29/01/18.
 */

public class StopsAdapter extends RecyclerView.Adapter<StopsAdapter.ViewHolder> {

    private Context mContext;
    private List<Stops> mData;

    public StopsAdapter(Context mContext, List<Stops> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_stop_names, parent, false);
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

        ImageView dot;
        ImageView line;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            dot = itemView.findViewById(R.id.dot_imageView);
            line = itemView.findViewById(R.id.connector_imageView2);
            name = itemView.findViewById(R.id.stop_name);
        }

        public void bind(int position) {

            if (position == 0) {
                line.setVisibility(View.GONE);
                name.setText(mData.get(position).name);
            } else {
                name.setText(mData.get(position).name);
            }
        }
    }
}
