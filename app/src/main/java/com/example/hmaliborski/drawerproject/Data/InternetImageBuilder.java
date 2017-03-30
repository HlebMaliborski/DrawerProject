package com.example.hmaliborski.drawerproject.Data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class InternetImageBuilder implements IImageBuilder{

    @Override
    public List<ImageData> createListOfImages(Context context) {
        List<ImageData> list = new ArrayList<>();
        list.add(new ImageData("https://i.imgur.com/tGbaZCY.jpg"));

        return list;
    }
}
