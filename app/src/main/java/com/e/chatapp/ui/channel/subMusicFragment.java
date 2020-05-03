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

public class subMusicFragment extends Fragment {

    private List<Photo> photoList=new ArrayList<>();





    public subMusicFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sub_music_fragment, container,false);




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
            Photo g1 = new Photo("g1", R.drawable.g1);
            photoList.add(g1);
            Photo g2 = new Photo("g2", R.drawable.g2);
            photoList.add(g2);
            Photo g3 = new Photo("g3", R.drawable.g3);
            photoList.add(g3);
            Photo g4 = new Photo("g4", R.drawable.g4);
            photoList.add(g4);
            Photo g5 = new Photo("g5", R.drawable.g5);
            photoList.add(g5);
            Photo g6 = new Photo("g6", R.drawable.g6);
            photoList.add(g6);
            Photo g7 = new Photo("g7", R.drawable.g7);
            photoList.add(g7);
            Photo g8 = new Photo("g8", R.drawable.g8);
            photoList.add(g8);



        }
    }




}

