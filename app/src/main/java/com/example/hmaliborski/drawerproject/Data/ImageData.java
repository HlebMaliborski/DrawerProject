package com.example.hmaliborski.drawerproject.Data;

import android.graphics.Bitmap;

public class ImageData {

    String imagePath;

    public ImageData(String imagePath)
    {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
