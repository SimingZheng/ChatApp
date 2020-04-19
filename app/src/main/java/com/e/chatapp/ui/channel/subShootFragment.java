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

public class subShootFragment extends Fragment {

    private List<Photo> photoList=new ArrayList<>();





    public subShootFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sub_shoot_fragment, container,false);




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
            Photo s1 = new Photo("s1", R.drawable.s1);
            photoList.add(s1);
            Photo s2 = new Photo("s2", R.drawable.s2);
            photoList.add(s2);
            Photo s3 = new Photo("s3", R.drawable.s3);
            photoList.add(s3);
            Photo s4 = new Photo("s4", R.drawable.s4);
            photoList.add(s4);
            Photo s5 = new Photo("s5", R.drawable.s5);
            photoList.add(s5);
            Photo s6 = new Photo("s6", R.drawable.s6);
            photoList.add(s6);
            Photo s7 = new Photo("s7", R.drawable.s7);
            photoList.add(s7);
            Photo s8 = new Photo("s8", R.drawable.s8);
            photoList.add(s8);
            Photo s9 = new Photo("s9", R.drawable.s9);
            photoList.add(s9);

        }
    }




}

