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

public class subMovieFragment extends Fragment {

    private List<Photo> photoList=new ArrayList<>();





    public subMovieFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sub_movie_fragment, container,false);




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
            Photo m1 = new Photo("m1", R.drawable.m1);
            photoList.add(m1);
            Photo m2 = new Photo("m2", R.drawable.m2);
            photoList.add(m2);
            Photo m3 = new Photo("m3", R.drawable.m3);
            photoList.add(m3);
            Photo m4 = new Photo("m4", R.drawable.m4);
            photoList.add(m4);
            Photo m5 = new Photo("m5", R.drawable.m5);
            photoList.add(m5);
            Photo m6 = new Photo("m6", R.drawable.m6);
            photoList.add(m6);
            Photo m7 = new Photo("m7", R.drawable.m7);
            photoList.add(m7);
            Photo m8 = new Photo("m8", R.drawable.m8);
            photoList.add(m8);
            Photo m9 = new Photo("m9", R.drawable.m9);
            photoList.add(m9);


        }
    }




}

