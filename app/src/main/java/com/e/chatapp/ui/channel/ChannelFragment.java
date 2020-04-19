package com.e.chatapp.ui.channel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.e.chatapp.Edit_profile;
import com.e.chatapp.R;
import com.e.chatapp.SubChannel;
import com.e.chatapp.SubFashion;
import com.e.chatapp.SubFood;
import com.e.chatapp.SubGame;
import com.e.chatapp.SubMovie;
import com.e.chatapp.SubMusic;
import com.e.chatapp.SubPet;
import com.e.chatapp.SubShoot;
import com.e.chatapp.SubSport;

import java.util.ArrayList;
import java.util.List;

public class ChannelFragment extends Fragment{

    private ChannelViewModel channelViewModel;
    private View v;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        /*channelViewModel=
        ViewModelProviders.of(this).get(ChannelViewModel.class);*/
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
       Button movieButton = (Button)root.findViewById(R.id.movieButton);
        movieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), SubMovie.class);
                startActivity(intent);
            }
        });
        Button fashionButton = (Button)root.findViewById(R.id.fashionButton);
        fashionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), SubFashion.class);
                startActivity(intent);
            }
        });
        Button sportButton = (Button)root.findViewById(R.id.sportButton);
        sportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), SubSport.class);
                startActivity(intent);
            }
        });
        Button photograButton = (Button)root.findViewById(R.id.phothograButton);
        photograButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), SubShoot.class);
                startActivity(intent);
            }
        });
      Button techButton = (Button)root.findViewById(R.id.techButton);
        techButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), SubChannel.class);
                startActivity(intent);
            }
        });
       Button foodButton = (Button)root.findViewById(R.id.foodButton);
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), SubFood.class);
                startActivity(intent);
            }
        });
        Button gameButton = (Button)root.findViewById(R.id.gameButton);
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), SubGame.class);
                startActivity(intent);
            }
        });
        Button petButton = (Button)root.findViewById(R.id.petButton);
        petButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), SubPet.class);
                startActivity(intent);
            }
        });
        Button musicButton = (Button)root.findViewById(R.id.musicButton);
        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), SubMusic.class);
                startActivity(intent);
            }
        });

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
