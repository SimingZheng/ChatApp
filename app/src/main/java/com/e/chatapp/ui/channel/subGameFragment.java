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
            Photo ga1 = new Photo("ga1", R.drawable.ga1);
            photoList.add(ga1);
            Photo ga2 = new Photo("ga2", R.drawable.ga2);
            photoList.add(ga2);
            Photo ga3 = new Photo("ga3", R.drawable.ga3);
            photoList.add(ga3);
            Photo ga4 = new Photo("ga4", R.drawable.ga4);
            photoList.add(ga4);
            Photo ga5 = new Photo("ga5", R.drawable.ga5);
            photoList.add(ga5);
            Photo ga6 = new Photo("ga6", R.drawable.ga6);
            photoList.add(ga6);
            Photo ga7 = new Photo("ga7", R.drawable.ga7);
            photoList.add(ga7);
            Photo ga8 = new Photo("ga8", R.drawable.ga8);
            photoList.add(ga8);
            Photo ga9 = new Photo("ga9", R.drawable.ga9);
            photoList.add(ga9);
            Photo ga10 = new Photo("ga10", R.drawable.ga10);
            photoList.add(ga10);

        }
    }




}

