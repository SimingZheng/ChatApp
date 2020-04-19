package com.e.chatapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Setting extends AppCompatActivity {

    public static final String string = "example";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingFragment())
                .commit();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        Boolean switchPref = sharedPreferences.getBoolean(Setting.string, false);
        Toast.makeText(Setting.this, switchPref.toString(), Toast.LENGTH_SHORT).show();
    }
}
