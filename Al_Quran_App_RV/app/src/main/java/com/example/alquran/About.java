package com.example.alquran;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.alquran.databinding.ActivityAboutBinding;

public class About extends DrawerBaseActivity {

    ActivityAboutBinding activityAboutBinding;

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAboutBinding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(activityAboutBinding.getRoot());
        allocateActivityTitle("About");

        txt = findViewById(R.id.testTxt2);
        txt.setText("About");

    }
}