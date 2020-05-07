package com.e.chatapp.ui.channel;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toolbar;

import com.e.chatapp.R;

import java.util.ArrayList;
import java.util.List;

public class subFoodFragment extends Fragment {

    private List<Photo> photoList=new ArrayList<>();





    public subFoodFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sub_food_fragment, container,false);




        Button button=(Button)view.findViewById(R.id.PostButton);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
                intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
                intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
                startActivityForResult(intent, 1);
            }

        });





        RecyclerView recyclerView;
        initPhotos();
        recyclerView = view.findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        Adapter adpter = new Adapter(photoList);
        recyclerView.setAdapter(adpter);



        return view;
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    private void initPhotos(){
        for(int i=0;i<10;i++){
            Photo FO1 = new Photo("FO1", R.drawable.fo1);
            photoList.add(FO1);
            Photo FO2 = new Photo("FO2", R.drawable.fo2);
            photoList.add(FO2);
            Photo FO3 = new Photo("FO3", R.drawable.fo3);
            photoList.add(FO3);
            Photo FO4 = new Photo("FO4", R.drawable.fo4);
            photoList.add(FO4);
            Photo FO5 = new Photo("FO5", R.drawable.fo5);
            photoList.add(FO5);
            Photo FO6 = new Photo("FO6", R.drawable.fo6);
            photoList.add(FO6);
            Photo FO7 = new Photo("FO7", R.drawable.fo7);
            photoList.add(FO7);
            Photo FO8 = new Photo("FO8", R.drawable.fo8);
            photoList.add(FO8);
            Photo FO9 = new Photo("FO9", R.drawable.fo9);
            photoList.add(FO9);
            Photo FO10 = new Photo("FO10", R.drawable.fo10);
            photoList.add(FO10);
            Photo FO11 = new Photo("FO11", R.drawable.fo11);
            photoList.add(FO11);
            Photo FO12 = new Photo("FO12", R.drawable.fo12);
            photoList.add(FO12);



        }
    }




}

