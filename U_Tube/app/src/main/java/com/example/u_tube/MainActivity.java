package com.example.u_tube;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView vv = findViewById(R.id.player);
        vv.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.polar_bear);

        // For controlling video
        MediaController mc = new MediaController(this);
        mc.setAnchorView(vv);

        vv.setMediaController(mc);

        vv.start();
    }
}