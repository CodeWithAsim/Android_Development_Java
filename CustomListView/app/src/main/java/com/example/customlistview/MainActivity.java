package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    ListView lv ;

    ArrayList<student> al = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lstview);

        al.add(new student("Mualana Tariq Jamil","50","Alim",R.drawable.maulana));
        al.add(new student("Imran Khan","60","Politician",R.drawable.imran));
        al.add(new student("Kholi","40","Cricket Player",R.drawable.kholi));
        al.add(new student("Mazz Safdar","22","YouTuber",R.drawable.mazz));
        al.add(new student("Ronaldo","42","Footballer",R.drawable.ronaldo));
        al.add(new student("Atif Aslam","45","Singer",R.drawable.atifaslam));

        MyAdapter ad = new MyAdapter(this , al);
        lv.setAdapter(ad);

    }
}