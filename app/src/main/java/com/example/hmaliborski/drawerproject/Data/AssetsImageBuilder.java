package com.example.hmaliborski.drawerproject.Data;


import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.util.Log;

import com.example.hmaliborski.drawerproject.Constants.Constants;
import com.example.hmaliborski.drawerproject.Enums.ImageEnum;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AssetsImageBuilder implements IImageBuilder {

    private ImageEnum imageEnum;
    public AssetsImageBuilder(ImageEnum imageEnum)
    {
        this.imageEnum = imageEnum;
    }

    @Override
    public List<ImageData> createListOfImages(Context context) {
        AssetManager assetManager = context.getAssets();
        List<ImageData> list = new ArrayList<>();

        try
        {
            String[] images = assetManager.list(Constants.ASSETS_FOLDER);
            Uri uri;
            for (String item : images)
            {
                switch (imageEnum)
                {
                    case CUSTOM_ASSETS:
                        list.add(new ImageData(Constants.ASSETS_FOLDER + File.separator + item));
                        break;
                    case PICASSO_ASSETS:
                        list.add(new ImageData(Constants.ANDROID_ASSETS_PATH + File.separator + Constants.ASSETS_FOLDER + File.separator + item));
                        break;
                }

            }
        }
        catch (IOException e)
        {
            Log.d("IOException", e.toString());
        }

        return list;
    }
}
