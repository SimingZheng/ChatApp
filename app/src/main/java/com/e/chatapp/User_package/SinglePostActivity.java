package com.e.chatapp.User_package;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.chatapp.R;
import com.e.chatapp.ui.channel.ChannelFragment;
import com.e.chatapp.ui.channel.subchannel.SubChannel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

public class SinglePostActivity extends AppCompatActivity {

    private ImageView singelImage;
    private TextView singleChannel, singleDesc;
    String post_key = null;
    private DatabaseReference mDatabase;
    private Button deleteBtn;
    private FirebaseAuth mAuth;
    private String data;
    public static Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_post);
        mActivity=this;

        singelImage = (ImageView)findViewById(R.id.singleImageview);
        singleChannel = (TextView)findViewById(R.id.singleChannel);
        singleDesc = (TextView)findViewById(R.id.singleDesc);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Postzone");
        post_key = getIntent().getExtras().getString("PostID");
        data = getIntent().getExtras().getString("Data");
        deleteBtn = (Button)findViewById(R.id.deleteBtn);
        mAuth = FirebaseAuth.getInstance();
        deleteBtn.setVisibility(View.INVISIBLE);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDatabase.child(post_key).removeValue();
                //SubChannel.mActivity.finish();
                /*Intent mainintent = new Intent(SinglePostActivity.this, SubChannel.class);
                mainintent.putExtra("Data",data);
                startActivity(mainintent);*/
                SinglePostActivity.mActivity.finish();
            }
        });


        mDatabase.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String post_channel = (String) dataSnapshot.child("channel").getValue();
                String post_desc = (String) dataSnapshot.child("desc").getValue();
                String post_image = (String) dataSnapshot.child("imageUrl").getValue();
                String post_uid = (String) dataSnapshot.child("uid").getValue();

                singleChannel.setText(post_channel);
                singleDesc.setText(post_desc);
                Picasso.get().load(post_image).into(singelImage);
                if (mAuth.getCurrentUser().getUid().equals(post_uid)){

                    deleteBtn.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

