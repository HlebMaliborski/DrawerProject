package com.example.hmaliborski.drawerproject.Manager;


import android.content.Context;

import com.example.hmaliborski.drawerproject.Data.AssetsImageBuilder;
import com.example.hmaliborski.drawerproject.Data.FileSystemImageBuilder;
import com.example.hmaliborski.drawerproject.Data.IImageBuilder;
import com.example.hmaliborski.drawerproject.Data.ImageData;
import com.example.hmaliborski.drawerproject.Data.InternetImageBuilder;

import java.util.List;

public class ImageManager {

    public static List<ImageData> GetListOfImages(int type, Context context)
    {
        IImageBuilder imageBuilder = null;

        switch (type)
        {
            case 0:
                imageBuilder = new FileSystemImageBuilder();
                break;

            case 1:
                imageBuilder = new AssetsImageBuilder();
                break;

            case 2:
                imageBuilder = new InternetImageBuilder();
                break;
        }

        return imageBuilder.createListOfImages(context);
    }
}
