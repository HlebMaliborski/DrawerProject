package com.example.hmaliborski.drawerproject.Data;


import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import com.example.hmaliborski.drawerproject.Constants.Constants;
import com.example.hmaliborski.drawerproject.Data.IImageBuilder;
import com.example.hmaliborski.drawerproject.Data.ImageData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSystemImageBuilder implements IImageBuilder {

    @Override
    public List<ImageData> createListOfImages(Context context) {
        List<ImageData> list = new ArrayList<>();
        File externalPath = Environment.getExternalStorageDirectory();
        File externalFullPath = new File(externalPath + File.separator + Constants.EXTERNAL_IMAGE_STORAGE);

        if(externalFullPath.exists())
        {
            String[] listOfImages = externalFullPath.list();
            Uri uri;

            for (String item : listOfImages)
            {
                uri = Uri.fromFile(new File(externalFullPath + File.separator + item));
                list.add(new ImageData(uri.toString()));
            }
        }

        return list;
    }
}
