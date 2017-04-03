package com.example.hmaliborski.drawerproject.AsyncTaskParser;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class AsyncAssetsParser extends AsyncImageParser {


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Bitmap doInBackground(String... params) {
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
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
    }
}
