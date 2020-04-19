package com.e.chatapp.ui.notifications;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.chatapp.Chat;
import com.e.chatapp.Nearby_user;
import com.e.chatapp.R;
import com.e.chatapp.User_package.Friendlist_item;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationsFragment extends Fragment {

    private View root;
    private RecyclerView chatList;

    private DatabaseReference ChatRef, UserRef;
    private FirebaseAuth Auth;
    private String currentuser;

    private String Image = "user_image";

    public NotificationsFragment() {

    }

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        root = inflater.inflate(R.layout.fragment_notifications, container, false);

        Auth = FirebaseAuth.getInstance();
        currentuser = Auth.getCurrentUser().getUid();
        ChatRef = FirebaseDatabase.getInstance().getReference().child("Contacts").child(currentuser);
        UserRef = FirebaseDatabase.getInstance().getReference().child("Users");

        chatList = (RecyclerView) root.findViewById(R.id.chat_list_recycler_view);
        chatList.setLayoutManager(new LinearLayoutManager(getContext()));

        Button nearby = root.findViewById(R.id.news);
        nearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.google.com/topstories?hl=en-IE&gl=IE&ceid=IE:en"));
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Friendlist_item> options =
                new FirebaseRecyclerOptions.Builder<Friendlist_item>()
                        .setQuery(ChatRef, Friendlist_item.class)
                        .build();

        FirebaseRecyclerAdapter<Friendlist_item, ChatListHolder> adapter =
                new FirebaseRecyclerAdapter<Friendlist_item, ChatListHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final ChatListHolder chatListHolder, int position, @NonNull Friendlist_item friendlist_item) {
                        final String userID = getRef(position).getKey();
                        final String[] Image = {"user_image"};

                        UserRef.child(userID).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {

                                    if (dataSnapshot.hasChild("image")) {
                                        Image[0] = dataSnapshot.child("image").getValue().toString();
                                        Picasso.get().load(Image[0]).into(chatListHolder.image);
                                    }

                                    final String Name = dataSnapshot.child("username").getValue().toString();
                                    final String Email = dataSnapshot.child("email").getValue().toString();

                                    chatListHolder.username.setText(Name);

                                    if (dataSnapshot.child("state").hasChild("state")) {
                                        String state = dataSnapshot.child("state").child("state").getValue().toString();
                                        String date = dataSnapshot.child("state").child("date").getValue().toString();
                                        String time = dataSnapshot.child("state").child("time").getValue().toString();

                                        if (state.equals("online")){
                                            chatListHolder.online.setVisibility(View.VISIBLE);
                                            chatListHolder.online.setTextColor(0xEECBAD);
                                            chatListHolder.email.setText("online");
                                        }
                                        else if (state.equals("off line")){
                                            chatListHolder.online.setVisibility(View.VISIBLE);
                                            chatListHolder.online.setTextColor(0xCD2626);
                                            chatListHolder.email.setText("Last Seen: " + date + " " + time);
                                        }
                                    } else {
                                        chatListHolder.email.setText("never seen");
                                    }

                                    chatListHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent chatIntent = new Intent(getContext(), Chat.class);
                                            chatIntent.putExtra("user_ID", userID);
                                            chatIntent.putExtra("user_Name", Name);
                                            chatIntent.putExtra("user_Image", Image[0]);
                                            startActivity(chatIntent);
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                    @NonNull
                    @Override
                    public ChatListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(getContext()).inflate(R.layout.friend_list_item, parent, false);
                        return new ChatListHolder(view);
                    }
                };
        chatList.setAdapter(adapter);
        adapter.startListening();
    }

    public static class ChatListHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView email, username;
        TextView online;

        public ChatListHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.friend_portrait);
            username = itemView.findViewById(R.id.friend_name);
            email = itemView.findViewById(R.id.friend_email);

            online = (TextView) itemView.findViewById(R.id.state);
        }
    }
}