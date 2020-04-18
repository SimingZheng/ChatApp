package com.e.chatapp.ui.channel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.e.chatapp.R;

import java.util.ArrayList;
import java.util.List;

public class ChannelFragment extends Fragment{

    private ChannelViewModel channelViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //channelViewModel=
        ViewModelProviders.of(this).get(ChannelViewModel.class);
        View root = inflater.inflate(R.layout.fragment_channel, container, false);
        // final TextView textView = root.findViewById(R.id.text_channel);
        /*channelViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        /*initInterest();
        View view = null;
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        InterestAdapter adapter = new InterestAdapter(interestList);*/
        return root;
    }

   /* private void initInterest(){
        Interest movie = new Interest("Movie",R.drawable.ic_category_t15);
        interestList.add(movie);
        Interest game = new Interest("Game",R.drawable.ic_category_t17);
        interestList.add(game);
        Interest food = new Interest("Food",R.drawable.ic_category_t22);
        interestList.add(food);
        Interest music = new Interest("Music",R.drawable.ic_category_t59);
        interestList.add(music);
        Interest pet = new Interest("Pet",R.drawable.ic_category_t75);
        interestList.add(pet);
        Interest tech = new Interest("Technology",R.drawable.ic_category_t15);
        interestList.add(tech);
        Interest study = new Interest("Study",R.drawable.ic_category_t39);
        interestList.add(study);
        Interest photogra = new Interest("Photography",R.drawable.ic_category_t95);
        interestList.add(photogra);
        Interest sport = new Interest("Sport",R.drawable.ic_category_t163);
        interestList.add(sport);

    }*/
}
