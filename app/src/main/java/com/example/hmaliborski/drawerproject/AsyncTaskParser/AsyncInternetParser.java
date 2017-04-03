package com.example.hmaliborski.drawerproject.AsyncTaskParser;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;

public class AsyncInternetParser extends AsyncImageParser {

    @Override
    protected Bitmap doInBackground(String... params) {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(params[0]);
        HttpResponse response = null;
        HttpEntity entity = null;
        BufferedHttpEntity bufferedHttpEntity = null;
        InputStream stream = null;

        try {
            response = client.execute(request);
            entity = response.getEntity();
            bufferedHttpEntity = new BufferedHttpEntity(entity);
            stream = bufferedHttpEntity.getContent();

        } catch (IOException e) {
            Log.d("Apache exception", e.toString());
        }

        Bitmap bitmap = BitmapFactory.decodeStream(stream);

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
    }
}
