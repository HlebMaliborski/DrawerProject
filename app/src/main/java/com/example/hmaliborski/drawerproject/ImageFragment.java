package com.example.hmaliborski.drawerproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hmaliborski.drawerproject.Adapters.RecyclerViewAdapter;
import com.example.hmaliborski.drawerproject.Data.ImageData;
import com.example.hmaliborski.drawerproject.Enums.ImageEnum;
import com.example.hmaliborski.drawerproject.Manager.ImageManager;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {

    public static final String TYPE_OF_IMAGE = "type";

    public ImageFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_file_system, container, false);
        int type = getArguments().getInt(TYPE_OF_IMAGE);
        ImageEnum imageEnum = ImageEnum.values()[type];
        List<ImageData> list = ImageManager.GetListOfImages(imageEnum, getContext());

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), list, imageEnum);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_recycler);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
        recyclerView.setAdapter(adapter);

        return view;
    }
}
