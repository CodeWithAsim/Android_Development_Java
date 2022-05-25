package com.example.friendlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<String> al = new ArrayList<>();
    ListView lv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        al.add("Asim");
        al.add("Hassan");
        al.add("Jawad");
        al.add("Zain");
        al.add("Mahad");
        al.add("Faizan");

        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,al);

        lv = findViewById(R.id.frndlst);
        lv.setAdapter(ad);


    }
}