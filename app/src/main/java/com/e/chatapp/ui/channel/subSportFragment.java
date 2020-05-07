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

public class subSportFragment extends Fragment {

    private List<Photo> photoList=new ArrayList<>();





    public subSportFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sub_sport_fragment, container,false);




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
            Photo sp1 = new Photo("sp1", R.drawable.sp1);
            photoList.add(sp1);
            Photo sp2 = new Photo("sp2", R.drawable.sp2);
            photoList.add(sp2);
            Photo sp3 = new Photo("sp3", R.drawable.sp3);
            photoList.add(sp3);
            Photo sp4 = new Photo("sp4", R.drawable.sp4);
            photoList.add(sp4);
            Photo sp5 = new Photo("sp5", R.drawable.sp5);
            photoList.add(sp5);
            Photo sp6 = new Photo("sp6", R.drawable.sp6);
            photoList.add(sp6);
            Photo sp7 = new Photo("sp7", R.drawable.sp7);
            photoList.add(sp7);
            Photo sp8 = new Photo("sp8", R.drawable.sp8);
            photoList.add(sp8);
            Photo sp9 = new Photo("sp9", R.drawable.sp9);
            photoList.add(sp9);
            Photo sp10 = new Photo("sp10", R.drawable.sp10);
            photoList.add(sp10);


        }
    }




}

