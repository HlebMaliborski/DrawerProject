package com.example.hmaliborski.drawerproject.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hmaliborski.drawerproject.R;

public class FileSystemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView cellImageView;

    public FileSystemViewHolder(View itemView) {
        super(itemView);
        cellImageView = (ImageView) itemView.findViewById(R.id.cell_image);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "Ura" + getAdapterPosition(), Toast.LENGTH_SHORT).show();
    }
}
