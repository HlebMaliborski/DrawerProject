package com.example.hmaliborski.drawerproject.Parser;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class CustomAssetsParser extends ImageParser {


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
