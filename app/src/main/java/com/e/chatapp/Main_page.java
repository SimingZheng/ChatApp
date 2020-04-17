package com.e.chatapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.auth.api.Auth;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Main_page extends AppCompatActivity {

    private String currentUserID;
    private FirebaseAuth Auth;
    private DatabaseReference root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_friend_list, R.id.navigation_channel)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        Auth = FirebaseAuth.getInstance();

        root = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    protected  void  onStart(){
        super.onStart();

        FirebaseUser currentUser = Auth.getCurrentUser();

        if (currentUser == null){
            Sign_in();
        }
        if (currentUser != null){
            updateStatus("online");
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        FirebaseUser currentUser = Auth.getCurrentUser();
        if (currentUser != null){
            updateStatus("off line");
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        FirebaseUser currentUser = Auth.getCurrentUser();
        if (currentUser != null){
            updateStatus("off line");
        }
    }

    private void Sign_in(){
        Intent intent = new Intent(this, Sign_in.class);
        startActivity(intent);
    }

    private void updateStatus(String state){
        String saveCurrentTime, saveCurrentDate;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        HashMap<String, Object> onlineStateMap = new HashMap<>();
        onlineStateMap.put("time", saveCurrentTime);
        onlineStateMap.put("date", saveCurrentDate);
        onlineStateMap.put("state", state);

        currentUserID = Auth.getCurrentUser().getUid();

        root.child("Users").child(currentUserID).child("state")
                .updateChildren(onlineStateMap);

    }
}
