package com.example.hmaliborski.drawerproject.Manager;


import android.content.Context;

import com.example.hmaliborski.drawerproject.Data.IImageBuilder;
import com.example.hmaliborski.drawerproject.Data.ImageData;

import java.util.List;

public class ImageManager {

    public static List<ImageData> GetListOfImages(IImageBuilder imageBuilder, Context context)
    {
        return imageBuilder.createListOfImages(context);
    }
}
