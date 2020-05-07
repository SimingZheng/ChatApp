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

public class subGameFragment extends Fragment {

    private List<Photo> photoList=new ArrayList<>();





    public subGameFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sub_game_fragment, container,false);




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
            Photo f1 = new Photo("f1", R.drawable.f1);
            photoList.add(f1);
            Photo f2 = new Photo("f2", R.drawable.f2);
            photoList.add(f2);
            Photo f3 = new Photo("f3", R.drawable.f3);
            photoList.add(f3);
            Photo f4 = new Photo("f4", R.drawable.f4);
            photoList.add(f4);
            Photo f5 = new Photo("f5", R.drawable.f5);
            photoList.add(f5);
            Photo f6 = new Photo("f6", R.drawable.f6);
            photoList.add(f6);
            Photo f7 = new Photo("f7", R.drawable.f7);
            photoList.add(f7);
            Photo f8 = new Photo("f8", R.drawable.f8);
            photoList.add(f8);
            Photo f9 = new Photo("f9", R.drawable.f9);
            photoList.add(f9);


        }
    }




}

