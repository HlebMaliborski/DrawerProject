package com.example.hmaliborski.drawerproject.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hmaliborski.drawerproject.Data.ImageData;
import com.example.hmaliborski.drawerproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private List<ImageData> imageList;

    public RecyclerViewAdapter(Context context, List<ImageData> list)
    {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.imageList = list;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.grid_cell_layout, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Picasso.with(context).load(imageList.get(position).getImagePath()).into(holder.cellImageView);
    }

    @Override
    public int getItemCount() {
        return this.imageList.size();
    }
}
