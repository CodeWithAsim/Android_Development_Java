package com.example.activitylifecycle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Asim", "onCreate : ONCREATE()");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.d("Asim", "onRestart : ONRESTART()");
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d("Asim", "onStart : ONSTART()");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d("Asim", "onResume : ONRESUME()");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d("Asim", "onPause : ONPAUSE()");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d("Asim", "onStop : ONSTOP()");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d("Asim", "onDestroy : ONDESTROY()");
    }
}