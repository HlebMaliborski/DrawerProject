package com.example.hmaliborski.drawerproject;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hmaliborski.drawerproject.Adapters.RecyclerViewAdapter;
import com.example.hmaliborski.drawerproject.Data.ImageData;
import com.example.hmaliborski.drawerproject.Enums.ImageEnum;
import com.example.hmaliborski.drawerproject.Manager.ImageManager;
import com.example.hmaliborski.drawerproject.Services.ParserService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {
    final Messenger fragmentMessenger = new Messenger(new FragmentHandler());

    public static final String TYPE_OF_IMAGE = "type";

    private ServiceConnection serviceConnection;
    private RecyclerViewAdapter adapter;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private List<Runnable> runnableList = new ArrayList<>();
    private IBinder binder;
    private Messenger serviceMessenger;

    boolean bound = false;

    public ImageFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_file_system, container, false);
        int type = getArguments().getInt(TYPE_OF_IMAGE);
        ImageEnum imageEnum = ImageEnum.values()[type];

        if(imageEnum == ImageEnum.SERVICE_INTERNET)
        {
            initializeService();
        }

        List<ImageData> list = ImageManager.GetListOfImages(imageEnum, getContext());

        adapter = new RecyclerViewAdapter(getContext(), list, imageEnum, ImageFragment.this);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_recycler);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void initializeService()
    {
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                binder = service;
                for(Runnable runnable : runnableList)
                {
                    executorService.submit(runnable);
                }
                bound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                bound = false;
            }
        };

        getContext().bindService(new Intent(getContext(), ParserService.class), serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void sendMessage(String path)
    {
        serviceMessenger = new Messenger(binder);
        Message message = new Message();
        message.obj = path;
        message.replyTo = fragmentMessenger;
        try {
            serviceMessenger.send(message);
        } catch (RemoteException e) {
            Log.d("RemoteException", e.toString());
        }
    }

    public void setList(Runnable runnable)
    {
        runnableList.add(runnable);
    }

    private class FragmentHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case ParserService.IMAGE_IS_LOADED:
                    adapter.notifyDataSetChanged();
                    break;

                default:
                    super.handleMessage(msg);
            }
        }
    }
}
