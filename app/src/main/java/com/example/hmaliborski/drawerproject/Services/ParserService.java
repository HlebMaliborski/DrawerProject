package com.example.hmaliborski.drawerproject.Services;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.example.hmaliborski.drawerproject.ServiceParser.ServiceParser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParserService extends Service {
    final Messenger serviceMessenger = new Messenger(new ParserHandler());

    public static final int IMAGE_IS_LOADED = 1;
    public static final String IS_CONNECTED = "isConnected";
    private ExecutorService executorService;
    private Messenger fragmentMessenger;

    public ParserService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        executorService = Executors.newFixedThreadPool(1);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    private void doInBackground(String message)
    {
        executorService.submit(()->
        {
            Bitmap bitmap = ServiceParser.downloadImageByUrl(message);
            if(!ServiceParser.saveImageToFileSystem(bitmap, message))
            {
                Log.d("IsImageSaved", "Image is not saved");
            }
            else
            {
                Message messageToFragment = Message.obtain(null, IMAGE_IS_LOADED);
                messageToFragment.replyTo  = serviceMessenger;
                try
                {
                    fragmentMessenger.send(messageToFragment);
                } catch (RemoteException e)
                {
                    Log.d("RemoteException", e.toString());
                }
            }
        });
    }
    @Override
    public IBinder onBind(Intent intent) {
        return serviceMessenger.getBinder();
    }


    public class ParserHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg) {

            String message = (String)msg.obj;

            switch (message)
            {
                case IS_CONNECTED:
                    break;
                default:
                    super.handleMessage(msg);
                    fragmentMessenger = msg.replyTo;
                    doInBackground(message);
            }
        }
    }
}
