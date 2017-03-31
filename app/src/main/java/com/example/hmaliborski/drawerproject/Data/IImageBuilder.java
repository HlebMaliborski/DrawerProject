package com.example.hmaliborski.drawerproject.Data;

import android.content.Context;

import com.example.hmaliborski.drawerproject.Data.ImageData;

import java.util.List;

public interface IImageBuilder {
    List<ImageData> createListOfImages(Context context);
}
