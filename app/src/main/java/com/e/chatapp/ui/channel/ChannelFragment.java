package com.e.chatapp.ui.channel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.e.chatapp.R;
import com.e.chatapp.ui.channel.subchannel.SubChannel;
import com.e.chatapp.ui.channel.subchannel.SubFashion;
import com.e.chatapp.ui.channel.subchannel.SubFood;
import com.e.chatapp.ui.channel.subchannel.SubGame;
import com.e.chatapp.ui.channel.subchannel.SubMovie;
import com.e.chatapp.ui.channel.subchannel.SubMusic;
import com.e.chatapp.ui.channel.subchannel.SubPet;
import com.e.chatapp.ui.channel.subchannel.SubShoot;
import com.e.chatapp.ui.channel.subchannel.SubSport;

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
                String data = "Movie";
                Intent intent = new Intent();
                intent.setClass(getActivity(),SubChannel.class);
                intent.putExtra("extra_data",data);
                startActivity(intent);
            }
        });
        Button fashionButton = (Button)root.findViewById(R.id.fashionButton);
        fashionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Fashion";
                Intent intent = new Intent();
                intent.setClass(getActivity(), SubChannel.class);
                intent.putExtra("extra_data",data);
                startActivity(intent);
            }
        });
        Button sportButton = (Button)root.findViewById(R.id.sportButton);
        sportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Sport";
                Intent intent = new Intent();
                intent.setClass(getActivity(), SubChannel.class);
                intent.putExtra("extra_data",data);
                startActivity(intent);
            }
        });
        Button photograButton = (Button)root.findViewById(R.id.phothograButton);
        photograButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Shoot";
                Intent intent = new Intent();
                intent.setClass(getActivity(), SubChannel.class);
                intent.putExtra("extra_data",data);
                startActivity(intent);
            }
        });
      Button techButton = (Button)root.findViewById(R.id.techButton);
        techButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Technology";
                Intent intent = new Intent();
                intent.setClass(getActivity(), SubChannel.class);
                intent.putExtra("extra_data",data);
                startActivity(intent);
            }
        });
       Button foodButton = (Button)root.findViewById(R.id.foodButton);
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Food";
                Intent intent = new Intent();
                intent.setClass(getActivity(), SubChannel.class);
                intent.putExtra("extra_data",data);
                startActivity(intent);
            }
        });
        Button gameButton = (Button)root.findViewById(R.id.gameButton);
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Game";
                Intent intent = new Intent();
                intent.setClass(getActivity(), SubChannel.class);
                intent.putExtra("extra_data",data);
                startActivity(intent);
            }
        });
        Button petButton = (Button)root.findViewById(R.id.petButton);
        petButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Pet";
                Intent intent = new Intent();
                intent.setClass(getActivity(), SubChannel.class);
                intent.putExtra("extra_data",data);
                startActivity(intent);
            }
        });
        Button musicButton = (Button)root.findViewById(R.id.musicButton);
        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Music";
                Intent intent = new Intent();
                intent.setClass(getActivity(), SubChannel.class);
                intent.putExtra("extra_data",data);
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
