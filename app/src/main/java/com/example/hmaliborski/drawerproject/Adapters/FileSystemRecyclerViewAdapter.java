package com.example.hmaliborski.drawerproject.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hmaliborski.drawerproject.Data.ImageData;
import com.example.hmaliborski.drawerproject.Enums.ImageEnum;
import com.example.hmaliborski.drawerproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FileSystemRecyclerViewAdapter extends RecyclerView.Adapter<FileSystemViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private List<ImageData> imageList;
    private ImageEnum imageEnum;

    public FileSystemRecyclerViewAdapter(Context context, List<ImageData> list)
    {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.imageList = list;
    }

    @Override
    public FileSystemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.grid_cell_layout, parent, false);
        FileSystemViewHolder viewHolder = new FileSystemViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FileSystemViewHolder holder, int position) {
        Picasso.with(context).load(imageList.get(position).getImagePath()).into(holder.cellImageView);
        //holder.cellImageView.setImageBitmap(imageList.get(position).getBitmap());
    }

    @Override
    public int getItemCount() {
        return this.imageList.size();
    }
}
