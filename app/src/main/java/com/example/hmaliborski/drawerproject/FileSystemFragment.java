package com.example.hmaliborski.drawerproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hmaliborski.drawerproject.Adapters.FileSystemRecyclerViewAdapter;
import com.example.hmaliborski.drawerproject.Data.AssetsImageBuilder;
import com.example.hmaliborski.drawerproject.Data.ImageData;
import com.example.hmaliborski.drawerproject.Manager.ImageManager;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FileSystemFragment extends Fragment {

    public FileSystemFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_file_system, container, false);
        List<ImageData> list = ImageManager.GetListOfImages(new AssetsImageBuilder(), getContext());

        FileSystemRecyclerViewAdapter adapter = new FileSystemRecyclerViewAdapter(getContext(), list);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_recycler);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
        recyclerView.setAdapter(adapter);

        return view;
    }
}
