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

public class subPetFragment extends Fragment {

    private List<Photo> photoList=new ArrayList<>();





    public subPetFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sub_pet_fragment, container,false);




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
            Photo p1 = new Photo("p1", R.drawable.p1);
            photoList.add(p1);
            Photo p2 = new Photo("p2", R.drawable.p2);
            photoList.add(p2);
            Photo p3 = new Photo("p3", R.drawable.p3);
            photoList.add(p3);
            Photo p4 = new Photo("p4", R.drawable.p4);
            photoList.add(p4);
            Photo p5 = new Photo("p5", R.drawable.p5);
            photoList.add(p5);
            Photo p6 = new Photo("p6", R.drawable.p6);
            photoList.add(p6);
            Photo p7 = new Photo("p7", R.drawable.p7);
            photoList.add(p7);
            Photo p8 = new Photo("p8", R.drawable.p8);
            photoList.add(p8);
            Photo p9 = new Photo("p9", R.drawable.p9);
            photoList.add(p9);


        }
    }




}

