package com.example.hmaliborski.drawerproject.ThreadParser;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

public abstract class ThreadParser {
    final int IMAGE_IS_LOADED = 1;
    final int IMAGE_IS_NOT_LOADED = 0;

    ImageView imageView;
    Context context;
    Bitmap bitmap;
    String path;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case IMAGE_IS_LOADED:
                    imageView.setImageBitmap(bitmap);
                    break;

                case IMAGE_IS_NOT_LOADED:
                    Log.d("ImageNotLoaded", "You have problem with loader");
                    break;
            }
        }
    };

    public void setParameters(ImageView imageView, Context context, String path) {
        this.imageView = imageView;
        this.context = context;
        this.path = path;
    }

    abstract public void doInBackground();
}
