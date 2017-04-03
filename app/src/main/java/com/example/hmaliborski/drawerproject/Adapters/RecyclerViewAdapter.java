package com.example.hmaliborski.drawerproject.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hmaliborski.drawerproject.Data.ImageData;
import com.example.hmaliborski.drawerproject.Enums.ImageEnum;
import com.example.hmaliborski.drawerproject.Manager.AsyncParserManager;
import com.example.hmaliborski.drawerproject.AsyncTaskParser.AsyncImageParser;
import com.example.hmaliborski.drawerproject.Manager.ThreadParserManager;
import com.example.hmaliborski.drawerproject.R;
import com.example.hmaliborski.drawerproject.ThreadParser.ThreadParser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private List<ImageData> imageList;
    private ImageEnum imageEnum;

    public RecyclerViewAdapter(Context context, List<ImageData> list, ImageEnum imageEnum)
    {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.imageList = list;
        this.imageEnum = imageEnum;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.grid_cell_layout, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        switch (imageEnum)
        {
            case THREAD_INTERNET:
                ThreadParser threadParser = ThreadParserManager.getImageParser(imageEnum);
                threadParser.setParameters(holder.cellImageView, context, imageList.get(position).getImagePath());
                threadParser.getBitmapImage();
                break;

            case CUSTOM_ASSETS:
            case CUSTOM_FILESYSTEM:
            case CUSTOM_INTERNET:
                AsyncImageParser imageParser = AsyncParserManager.getImageParser(imageEnum);
                imageParser.setImageParameters(holder.cellImageView, context);
                imageParser.execute(imageList.get(position).getImagePath());
                break;

            case PICASSO_ASSETS:
            case PICASSO_FILESYSTEM:
            case PICASSO_INTERNET:
                Picasso.with(context).load(imageList.get(position).getImagePath()).into(holder.cellImageView);
                break;

        }
    }

    @Override
    public int getItemCount() {
        return this.imageList.size();
    }
}
