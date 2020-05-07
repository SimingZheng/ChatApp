package com.e.chatapp.ui.channel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.chatapp.R;

import java.util.Iterator;
import java.util.List;

class Adapter extends RecyclerView.Adapter<Adapter.myviewholder> {
    private List<Photo> list;
    private View inflater;
    public static final int PULLUP_LOAD_MORE = 0;//上拉加载更多
    public static final int LOADING_MORE = 1;

    public Adapter(List<Photo> photoList) {
        list = photoList;
    }

    @Override
    public Adapter.myviewholder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        inflater = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_subchannel_ph,viewGroup,false);
        myviewholder myviewholder = new myviewholder(inflater);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.myviewholder holder, int position) {
        Photo photo = list.get(position);
        holder.photoImage.setImageResource(photo.getImageid());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{
        View photoView;
        ImageView photoImage;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            photoView = itemView;
            photoImage = itemView.findViewById(R.id.imageView);
        }
    }




}

