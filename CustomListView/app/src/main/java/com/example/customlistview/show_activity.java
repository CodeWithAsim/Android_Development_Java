package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class show_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Intent i = getIntent();
        int img_id = i.getIntExtra("ImageId",R.drawable.ic_baseline_error_24);

        ImageView img = findViewById(R.id.show_img);
        img.setImageResource(img_id);
    }
}