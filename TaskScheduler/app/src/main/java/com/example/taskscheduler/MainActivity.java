package com.example.taskscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public int number = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this ,2000);
                Toast.makeText(MainActivity.this, "Task running for "+number+" time", Toast.LENGTH_SHORT).show();
                number ++ ;
            }
        };
        handler.post(run);

        //new CountDownTimer(30000 , 1000){
        //    @Override
        //    public void onTick(long l) {
        //       Log.d("Asim", "onTick: run for "+number+" time");
        //        number++;
        //    }

        //    @Override
        //    public void onFinish() {
        //        Log.d("Asim", "onFinish: run for last time at turn "+number);
        //    }
        //}.start();
    }
}