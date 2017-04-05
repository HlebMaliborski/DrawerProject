package com.example.hmaliborski.drawerproject.Manager;


import android.content.Context;

import com.example.hmaliborski.drawerproject.Data.AssetsImageBuilder;
import com.example.hmaliborski.drawerproject.Data.FileSystemImageBuilder;
import com.example.hmaliborski.drawerproject.Data.IImageBuilder;
import com.example.hmaliborski.drawerproject.Data.ImageData;
import com.example.hmaliborski.drawerproject.Data.InternetImageBuilder;
import com.example.hmaliborski.drawerproject.Enums.ImageEnum;

import java.util.List;

public class ImageManager {

    public static List<ImageData> GetListOfImages(ImageEnum type, Context context)
    {
        IImageBuilder imageBuilder = null;

        switch (type)
        {
            case PICASSO_ASSETS:
            case CUSTOM_ASSETS:
                imageBuilder = new AssetsImageBuilder(type);
                break;

            case PICASSO_FILESYSTEM:
            case CUSTOM_FILESYSTEM:
                imageBuilder = new FileSystemImageBuilder(type);
                break;

            case PICASSO_INTERNET:
            case CUSTOM_INTERNET:
            case THREAD_INTERNET:
            case SERVICE_INTERNET:
            case INTENT_SERVICE_INTERNET:
                imageBuilder = new InternetImageBuilder();
                break;
        }

        return imageBuilder.createListOfImages(context);
    }
}
