package com.example.hmaliborski.drawerproject.Services;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.hmaliborski.drawerproject.ImageFragment;
import com.example.hmaliborski.drawerproject.ServiceParser.ServiceParser;

public class ParserIntentService extends IntentService {

    public static final String IMAGE_PATH = "imagePath";
    public static final String IMAGE_IS_LOADED = "isLoaded";

    public ParserIntentService()
    {
        super("ParserIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        String path = intent.getStringExtra(IMAGE_PATH);
        Bitmap bitmap = ServiceParser.downloadImageByUrl(path);
        if(!ServiceParser.saveImageToFileSystem(bitmap, path))
        {
            Log.d("IsImageSaved", "Image is not saved");
        }
        else
        {
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(ImageFragment.FragmentReceiver.RECEIVE_MESSAGE);
            broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
            broadcastIntent.putExtra(IMAGE_IS_LOADED, true);
            sendBroadcast(broadcastIntent);
        }
    }
}
