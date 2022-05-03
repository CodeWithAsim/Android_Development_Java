package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent i = getIntent();
        String name = i.getStringExtra("s_name");
        String phone = i.getStringExtra("s_phone");

        TextView n = findViewById(R.id.rname);
        TextView p = findViewById(R.id.rphone);

        n.setText(name);
        p.setText(phone);

    }
}