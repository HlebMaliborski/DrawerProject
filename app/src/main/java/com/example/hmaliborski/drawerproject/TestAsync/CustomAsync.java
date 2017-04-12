package com.example.hmaliborski.drawerproject.TestAsync;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.example.threadmodule.CustomAsyncTask;

import java.io.IOException;
import java.io.InputStream;

public class CustomAsync extends CustomAsyncTask<String, Void , Bitmap> {

    ImageView imageView;
    Context context;
    public void setImageParameters(ImageView imageView, Context context)
    {
        this.context = context;
        this.imageView = imageView;
    }

    @Override
    public Bitmap doInBackground(String... params) {
        Bitmap bitmap;
        InputStream stream = null;
        try {
            stream = context.getAssets().open(params[0]);
        } catch (IOException e) {
            Log.d("ParserIO", e.toString());
        }
        bitmap = BitmapFactory.decodeStream(stream);

        return bitmap;
    }

    @Override
    public void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
