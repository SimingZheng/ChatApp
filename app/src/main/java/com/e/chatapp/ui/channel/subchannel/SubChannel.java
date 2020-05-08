package com.e.chatapp.ui.channel.subchannel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.e.chatapp.R;
import com.e.chatapp.User_package.PostActivity;
import com.e.chatapp.User_package.Postzone;
import com.e.chatapp.User_package.SinglePostActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class SubChannel extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "Postzonesubchannel";
    private Button button;
    private String data;
    private FirebaseStorage storage;
    public static Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.activity_sub_channel);
        recyclerView = (RecyclerView)findViewById(R.id.post_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        //recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Postzone");
        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        data = intent.getStringExtra("Data");
        button=(Button)findViewById(R.id.PostButton);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SubChannel.this, PostActivity.class);
                intent.putExtra("Data",data);
                startActivity(intent);

                /*// 开启Pictures画面Type设定为image
                intent.setType("image/*");
                // 使用Intent.ACTION_GET_CONTENT这个Action
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // 取得相片后返回本画面
                startActivityForResult(intent, 1);*/
            }

        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG,"onstart");
        //mAuth.addAuthStateListener(mAuthListener);
        data = getIntent().getStringExtra("extra_data");
        FirebaseRecyclerOptions<Postzone> options =
                new FirebaseRecyclerOptions.Builder<Postzone>()
                        .setQuery(mDatabase.orderByChild("channel").equalTo(data), Postzone.class)
                        .build();
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Postzone, PostzoneViewHolder>(options) {
            @Override

            public PostzoneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_items, parent, false);

                return new PostzoneViewHolder(view);
            }
            protected void onBindViewHolder(@NonNull PostzoneViewHolder postzoneViewHolder, int position, @NonNull Postzone model) {
                final String post_key = getRef(position).getKey().toString();

               // if(model.getChannel().compareTo(data)==0){
                    postzoneViewHolder.setChannel(model.getChannel());
                    postzoneViewHolder.setDesc(model.getDesc());
                    postzoneViewHolder.setImageUrl(getApplicationContext(), model.getImageUrl());
                    postzoneViewHolder.setUserName(model.getUsername());
               // }
                postzoneViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent singleActivity = new Intent(SubChannel.this, SinglePostActivity.class);
                        singleActivity.putExtra("PostID", post_key);
                        singleActivity.putExtra("Data",data);
                        startActivity(singleActivity);
                    }
                });
            }

        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG,"onResume");
        //mAuth.addAuthStateListener(mAuthListener);
       /* data = getIntent().getStringExtra("extra_data");
        FirebaseRecyclerOptions<Postzone> options =
                new FirebaseRecyclerOptions.Builder<Postzone>()
                        .setQuery(mDatabase.orderByChild("channel").equalTo(data), Postzone.class)
                        .build();
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Postzone, PostzoneViewHolder>(options) {
            @Override

            public PostzoneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_items, parent, false);

                return new PostzoneViewHolder(view);
            }
            protected void onBindViewHolder(@NonNull PostzoneViewHolder postzoneViewHolder, int position, @NonNull Postzone model) {
                final String post_key = getRef(position).getKey().toString();

                // if(model.getChannel().compareTo(data)==0){
                postzoneViewHolder.setChannel(model.getChannel());
                postzoneViewHolder.setDesc(model.getDesc());
                postzoneViewHolder.setImageUrl(getApplicationContext(), model.getImageUrl());
                postzoneViewHolder.setUserName(model.getUsername());
                // }
                postzoneViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent singleActivity = new Intent(SubChannel.this, SinglePostActivity.class);
                        singleActivity.putExtra("PostID", post_key);
                        singleActivity.putExtra("Data",data);
                        startActivity(singleActivity);
                    }
                });
            }

        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();*/
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }

    public static class PostzoneViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public PostzoneViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
        public void setChannel(String channel){
            TextView post_channel = mView.findViewById(R.id.post_channel_txtview);
            post_channel.setText(channel);
        }
        public void setDesc(String desc){
            TextView post_desc = mView.findViewById(R.id.post_desc_txtview);
            post_desc.setText(desc);
        }
        public void setImageUrl(Context ctx, String imageUrl){
            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Post");
            ImageView post_image = mView.findViewById(R.id.post_image);
            //Picasso.get().load(imageUrl).into(post_image);
            Picasso.get().load(imageUrl).placeholder(R.drawable.loading).into(post_image);
            Log.w("Subchannel Picasso","successful");
        }
        public void setUserName(String userName){
            TextView postUserName = mView.findViewById(R.id.post_user);
            postUserName.setText(userName);
        }
    }
}
