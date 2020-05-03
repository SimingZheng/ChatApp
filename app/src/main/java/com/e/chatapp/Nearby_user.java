package com.e.chatapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.e.chatapp.User_package.Friendlist_item;
import com.e.chatapp.User_package.LocationHelper;
import com.e.chatapp.User_package.User;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scwang.smartrefresh.header.material.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Nearby_user extends AppCompatActivity {

    public List<Friendlist_item> friendList = new ArrayList<>();
    private RecyclerView FindFriendsRecyclerList;
    private DatabaseReference UsersRef;

    public static int countFriends = 10;
    int user_image;
    String user_name;
    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_user);

        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");

        FindFriendsRecyclerList = (RecyclerView) findViewById(R.id.recycler_view);
        FindFriendsRecyclerList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Friendlist_item> options =
                new FirebaseRecyclerOptions.Builder<Friendlist_item>()
                        .setQuery(UsersRef, Friendlist_item.class)
                        .build();

        FirebaseRecyclerAdapter<Friendlist_item, FindFriendViewHolder> adapter =
                new FirebaseRecyclerAdapter<Friendlist_item, FindFriendViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final FindFriendViewHolder holder, final int position, @NonNull Friendlist_item model) {
                        final String userID = getRef(position).getKey();
                        final String[] Image = {"user_image"};
                        Auth = FirebaseAuth.getInstance();
                        final String currentuser = Auth.getCurrentUser().getUid();

                        UsersRef.addValueEventListener(new ValueEventListener() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {

                                    if (dataSnapshot.child(userID).child("location").exists()) {
                                        LocationHelper latitude = dataSnapshot.child(userID).child("location").getValue(LocationHelper.class);
                                        LocationHelper longitude = dataSnapshot.child(userID).child("location").getValue(LocationHelper.class);

                                        LocationHelper userLatitude = dataSnapshot.child(currentuser).child("location").getValue(LocationHelper.class);
                                        LocationHelper userLongitude = dataSnapshot.child(currentuser).child("location").getValue(LocationHelper.class);

                                        LatLng latLng = new LatLng(latitude.getLatitude(), longitude.getLongitude());
                                        LatLng userlatLng = new LatLng(userLatitude.getLatitude(), userLongitude.getLongitude());

                                        Double userDistance = CalculationByDistance(latLng, userlatLng);

                                        if (userDistance < 1) {

                                            if (dataSnapshot.child(userID).hasChild("image")) {
                                                String profileemail = dataSnapshot.child(userID).child("email").getValue().toString();
                                                String profilename = dataSnapshot.child(userID).child("username").getValue().toString();
//                                                String profileImage = dataSnapshot.child(userID).child("image").getValue().toString();

                                                holder.userName.setText(profilename);
                                                holder.userEmail.setText(userDistance+" km");
//                                                Picasso.get().load(profileImage).placeholder(R.drawable.icon_user).into(holder.profileImage);
                                                Image[0] = dataSnapshot.child(userID).child("image").getValue().toString();
                                                Picasso.get().load(Image[0]).into(holder.profileImage);
                                            } else {
                                                String profileemail = dataSnapshot.child(userID).child("email").getValue().toString();
                                                String profilename = dataSnapshot.child(userID).child("username").getValue().toString();

                                                holder.userName.setText(profilename);
                                                holder.userEmail.setText(userDistance+" km");
                                            }
                                            holder.itemView.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    String user_ID = getRef(position).getKey();

                                                    Intent intent = new Intent();
                                                    intent.setClass(Nearby_user.this, User_profile.class);
                                                    intent.putExtra("user_ID", user_ID);
                                                    startActivity(intent);
                                                }
                                            });
                                        }
                                        else {
                                            holder.itemView.setVisibility(View.GONE);
                                            ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
                                            params.height=0;
                                            params.width= 0;
                                            holder.itemView.setLayoutParams(params);
                                        }
                                    }
                                    else {
                                        holder.itemView.setVisibility(View.GONE);
                                        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
                                        params.height=0;
                                        params.width= 0;
                                        holder.itemView.setLayoutParams(params);
                                    }
                                }
                                else {
                                    holder.itemView.setVisibility(View.GONE);
                                    ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
                                    params.height=0;
                                    params.width= 0;
                                    holder.itemView.setLayoutParams(params);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                    @NonNull
                    @Override
                    public FindFriendViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.friend_list_item, viewGroup, false);
                        FindFriendViewHolder viewHolder = new FindFriendViewHolder(view);
                        return viewHolder;
                    }
                };
        FindFriendsRecyclerList.setAdapter(adapter);
        adapter.startListening();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return Radius * c;
    }

    public static class FindFriendViewHolder extends RecyclerView.ViewHolder {
        TextView userName, userEmail;
        ImageView profileImage;

        public FindFriendViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.friend_name);
            userEmail = itemView.findViewById(R.id.friend_email);
            profileImage = itemView.findViewById(R.id.friend_portrait);
        }
    }

//        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");
//
//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference users = database.getReference("Users");
//
//        final String currentuser = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
////        final User user = dataSnapshot.child(currentuser).getValue(User.class);
////        assert user != null;
////        String username = user.getUsername();
//
//        users.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String prevChildKey) {
////                FirebaseRecyclerOptions<ContactsContract.Contacts> options =
////                        new FirebaseRecyclerOptions.Builder<ContactsContract.Contacts>()
////                                .setQuery(UsersRef,Friendlist_item.class)
////                                .build();
////                if (dataSnapshot.exists()) {
//                User User = dataSnapshot.getValue(User.class);
//                assert User != null;
//                user_name = User.getUsername();
////                user_name =  User.();
//                System.out.println("Previous Post ID: " + prevChildKey);
//                countFriends = (int) dataSnapshot.getChildrenCount();
////                initItems(user_name);
////                }
////                else {
////                    Toast.makeText(Nearby_user.this, "No users ", Toast.LENGTH_LONG).show();
////                }
////                System.out.println(countFriends+" asdfasdfasdfdfsdfsad "+user_name+" "+friendList);
//                Friendlist_item portrait = new Friendlist_item(user_name, R.drawable.icon_user);
//                friendList.add(portrait);
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//            }
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//            }
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(Nearby_user.this, "Failed to read value.", Toast.LENGTH_LONG).show();
//            }
//        });
////        System.out.println(countFriends+" asdfasdfasdfdfsdfsad");
//        initItems(countFriends);
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        Friend_list_Adapter adapter = new Friend_list_Adapter(friendList);
//        recyclerView.setAdapter(adapter);
//    }
//
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//
//    }
//
//    private void initItems(int countFriends) {
//        for (int i = 0; i < countFriends; i++) {
//            Friendlist_item portrait = new Friendlist_item("User1", R.drawable.icon_user);
//            friendList.add(portrait);
//        }
//   }
////    private void initItems(String name) {
////        for (int i = 0; i < countFriends; i++) {
////            System.out.println(countFriends + " asdfasdfasdfsdfsdf");
////            Friendlist_item portrait = new Friendlist_item(name, R.drawable.icon_user);
////            friendList.add(portrait);
////        }
////    }
}
