package com.example.multiscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered);


        Intent intent = getIntent();
        String msg = intent.getStringExtra(MainActivity.MSG);

        TextView t = (TextView) findViewById(R.id.t1);
        t.setText(msg);
    }


}