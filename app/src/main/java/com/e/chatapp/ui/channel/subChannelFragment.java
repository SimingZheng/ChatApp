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

public class subChannelFragment extends Fragment {

    private List<Photo> photoList=new ArrayList<>();





    public subChannelFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sub_channel_fragment, container,false);




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
            Photo a = new Photo("a", R.drawable.a);
            photoList.add(a);
            Photo b = new Photo("b", R.drawable.b);
            photoList.add(b);
            Photo c = new Photo("c", R.drawable.c);
            photoList.add(c);
            Photo d = new Photo("d", R.drawable.d);
            photoList.add(d);
            Photo e = new Photo("e", R.drawable.e);
            photoList.add(e);
            Photo f = new Photo("f", R.drawable.f);
            photoList.add(f);
            Photo g = new Photo("g", R.drawable.g);
            photoList.add(g);
            Photo h = new Photo("h", R.drawable.h);
            photoList.add(h);


        }
    }




}

