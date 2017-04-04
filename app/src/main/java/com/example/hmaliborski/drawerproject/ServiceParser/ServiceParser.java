package com.example.hmaliborski.drawerproject.ServiceParser;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.example.hmaliborski.drawerproject.Constants.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ServiceParser {

    public static Bitmap downloadImageByUrl(String imageUrl)
    {
        Bitmap bitmap = null;

        try
        {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(imageUrl)
                    .build();

            Response response = client.newCall(request).execute();
            InputStream stream = response.body().byteStream();

            bitmap = BitmapFactory.decodeStream(stream);
        }
        catch (IOException e)
        {
            Log.d("ThreadIO", e.toString());
        }

        return bitmap;
    }

    public static boolean saveImageToFileSystem(Bitmap bitmap, String imageUrl)
    {
        String imageFolder = Environment.getExternalStorageDirectory().toString() + File.separator + Constants.EXTERNAL_IMAGE_STORAGE;
        File file = new File(imageFolder, getImageName(imageUrl));
        boolean isImageSaved = false;
        try
        {
            FileOutputStream stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.flush();
            stream.close();
            isImageSaved = true;
        }
        catch (FileNotFoundException e)
        {
            Log.d("FileNotFound", e.toString());
            return false;
        } catch (IOException e)
        {
            Log.d("IOException", e.toString());
        }

        return isImageSaved;
    }

    public static String getImageName(String imageUrl)
    {
        String imageName = null;

        try
        {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(imageUrl.getBytes());
            byte[] bytes = digest.digest();
            StringBuffer buffer = new StringBuffer();
            for(byte b : bytes)
            {
                buffer.append(String.format("%02x", b & 0xff));
            }

            imageName = buffer + "." + "PNG";
        }
        catch (NoSuchAlgorithmException e)
        {
            Log.d("AlgorithmException", e.toString());
        }

        return imageName;
    }

    public static boolean isFileExists(String fileName)
    {
        boolean isFileExists = false;
        File file = new File(Environment.getExternalStorageDirectory().toString() + File.separator + Constants.EXTERNAL_IMAGE_STORAGE, fileName);
        if (file.exists())
        {
            isFileExists = true;
        }

        return isFileExists;
    }

    public static String getFullImagePath(String imageName)
    {
        File externalPath = Environment.getExternalStorageDirectory();
        File externalFullPath = new File(externalPath + File.separator + Constants.EXTERNAL_IMAGE_STORAGE);

        return externalFullPath + File.separator + imageName;
    }
}
