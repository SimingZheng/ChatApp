package com.e.chatapp.ui.friendlist;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.chatapp.Find_friends;
import com.e.chatapp.Nearby;
import com.e.chatapp.Nearby_Map;
import com.e.chatapp.R;
import com.e.chatapp.Request_list;
import com.e.chatapp.User_package.Friendlist_item;
import com.e.chatapp.User_profile;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FriendlistFragment extends Fragment {

    private static final String TAG = "FriendlistFragment";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private View root;
    private FriendlistViewModel friendlistViewModel;
    private List<Friendlist_item> friendList = new ArrayList<>();
    private RecyclerView Friend_Recycle;

    private DatabaseReference FriendRef, UsersRef;
    private FirebaseAuth Auth;
    private String currentuser;

    public FriendlistFragment() {

    }

    public View onCreateView(@Nullable LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        friendlistViewModel =
                ViewModelProviders.of(this).get(FriendlistViewModel.class);
        assert inflater != null;
        View root = inflater.inflate(R.layout.fragment_friends_list, container, false);

        Friend_Recycle = (RecyclerView) root.findViewById(R.id.friend_recycler_view);
        Friend_Recycle.setLayoutManager(new LinearLayoutManager(getContext()));

        Auth = FirebaseAuth.getInstance();
        currentuser = Auth.getCurrentUser().getUid();
        FriendRef = FirebaseDatabase.getInstance().getReference().child("Contacts").child(currentuser);
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");

        if (isServicesOK()){
            Button nearby = root.findViewById(R.id.nearby_user);
            nearby.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Nearby_Map.class);
                    startActivity(intent);
                }
            });
        }

        Button findFriend = root.findViewById(R.id.find_friends);
        findFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Find_friends.class);
                startActivity(intent);
            }
        });

        Button user_request = root.findViewById(R.id.user_request);
        user_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Request_list.class);
                startActivity(intent);
            }
        });

        return root;
    }

    private void init(){

    }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK : checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getActivity());

        if (available == ConnectionResult.SUCCESS){
            Log.d(TAG, "isServiceOK: Google Play Services is working");
            return true;
        }
        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Log.d(TAG, "isServicesOK: an error occored but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(getActivity(), available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else {
            Log.d(TAG, "You can't make map requests");
        }
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Friendlist_item> options =
                new FirebaseRecyclerOptions.Builder<Friendlist_item>()
                        .setQuery(FriendRef, Friendlist_item.class)
                        .build();

        FirebaseRecyclerAdapter<Friendlist_item, FriendListViewHolder> adapter =
                new FirebaseRecyclerAdapter<Friendlist_item, FriendListViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final FriendListViewHolder friendListViewHolder, final int i, @NonNull Friendlist_item friendlist_item) {
                        String userID = getRef(i).getKey();
                        final String[] Image = {"user_image"};

                        UsersRef.child(userID).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild("image")) {
                                    String profileImage = dataSnapshot.child("image").getValue().toString();
                                    String profileemail = dataSnapshot.child("email").getValue().toString();
                                    String profilename = dataSnapshot.child("username").getValue().toString();

                                    friendListViewHolder.username.setText(profilename);
                                    friendListViewHolder.email.setText(profileemail);
                                    Picasso.get().load(profileImage).placeholder(R.drawable.icon_user).into(friendListViewHolder.image);

                                } else {
                                    String profileemail = dataSnapshot.child("email").getValue().toString();
                                    String profilename = dataSnapshot.child("username").getValue().toString();

                                    friendListViewHolder.username.setText(profilename);
                                    friendListViewHolder.email.setText(profileemail);
                                }
                                friendListViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String user_ID = getRef(i).getKey();

                                        Intent intent = new Intent();
                                        intent.setClass(getActivity(), User_profile.class);
                                        intent.putExtra("user_ID", user_ID);
                                        startActivity(intent);
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                    @NonNull
                    @Override
                    public FriendListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_list_item, parent, false);
                        FriendListViewHolder viewHolder = new FriendListViewHolder(view);
                        return viewHolder;
                    }
                };

        Friend_Recycle.setAdapter(adapter);
        adapter.startListening();
    }

    public static class FriendListViewHolder extends RecyclerView.ViewHolder {
        TextView username, email;
        ImageView image;

        public FriendListViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.friend_name);
            email = itemView.findViewById(R.id.friend_email);
            image = itemView.findViewById(R.id.friend_portrait);
        }
    }
}
