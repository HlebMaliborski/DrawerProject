package com.example.hmaliborski.drawerproject.ThreadParser;

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class InternetThreadParser extends ThreadParser {

    @Override
    public void doInBackground() {
        Thread thread = new Thread(()->
        {
            try
            {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(path)
                        .build();

                Response response = client.newCall(request).execute();
                InputStream stream = response.body().byteStream();

                bitmap = BitmapFactory.decodeStream(stream);
                handler.sendEmptyMessage(IMAGE_IS_LOADED);
            }
            catch (IOException e)
            {
                Log.d("ThreadIO", e.toString());
                handler.sendEmptyMessage(IMAGE_IS_NOT_LOADED);
            }
        });
        thread.start();
    }
}
