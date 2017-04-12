package com.example.hmaliborski.drawerproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hmaliborski.drawerproject.Enums.ImageEnum;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {

    ImageFragment fragment;
    FragmentTransaction transaction;
    private DrawerLayout drawerLayout;
    private ListView listView;
    private String[] titles;


    TextView textView;
    Test test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        isStoragePermissionGranted();

        textView = (TextView) findViewById(R.id.ura);
        test = new Test();
        test.start();

        titles = new String[]{"Picasso assets images", "Picasso file system images", "Picasso internet images",
                "Custom assets images", "Custom file system images", "Custom internet images", "Thread internet images",
        "Service custom internet image", "Intent service internet image", "Interface intent service","Pending intent service"};


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listView = (ListView) findViewById(R.id.left_drawer);
        fragment = new ImageFragment();

        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, titles));
        listView.setOnItemClickListener(new DrawerItemClickListener());
    }


    public void clickUra(View v)
    {
        Message message = test.handler.obtainMessage();
        test.handler.sendMessage(message);
    }
    public class Test extends Thread
    {
        Handler handler;
        @Override
        public void run() {
            Looper.prepare();
            handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    testMethod();
                }
            };
            Looper.loop();
        }
    }

    private void testMethod()
    {
        textView.setText("Ura");
    }
    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v("Permission", "Permission: " + permissions[0] + "was " + grantResults[0]);
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {

        Fragment fragment = new ImageFragment();

        Bundle args = new Bundle();

        args.putInt(ImageFragment.TYPE_OF_IMAGE, ImageEnum.values()[position].ordinal());
        fragment.setArguments(args);

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, fragment);
        transaction.commit();

        listView.setItemChecked(position, true);
        setTitle(titles[position]);
        drawerLayout.closeDrawer(listView);
    }

    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

}
