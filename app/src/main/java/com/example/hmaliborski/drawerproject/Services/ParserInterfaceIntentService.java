package com.example.hmaliborski.drawerproject.Services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class ParserInterfaceIntentService extends IntentService {

    private final IBinder localBinder = new LocalBinder();

    public ParserInterfaceIntentService() {
        super("ParserInterfaceIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    public interface ServiceListener
    {
        public void onImageLoaded();
    }

    public class LocalBinder extends Binder
    {
        public ParserInterfaceIntentService getInstance()
        {
            return ParserInterfaceIntentService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }
}
