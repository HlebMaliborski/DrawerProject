package com.example.hmaliborski.drawerproject.Parser;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

public abstract class ImageParser extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;
    Context context;
    public void setImageParameters(ImageView imageView, Context context)
    {
        this.context = context;
        this.imageView = imageView;
    }
}
