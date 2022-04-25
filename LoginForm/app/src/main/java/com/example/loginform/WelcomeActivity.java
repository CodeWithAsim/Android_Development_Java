package com.example.loginform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView t = findViewById(R.id.textView10);

        Intent i = getIntent();
        String n = i.getStringExtra(SignupActivity.MSG);

        t.setText(n);



    }
}