package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = findViewById(R.id.list);

        ArrayList<String> al = new ArrayList<>();

        al.add("Bag");
        al.add("Register");
        al.add("Pen");
        al.add("Pointer");
        al.add("Pencil");
        al.add("Scale");
        al.add("Ink Pot");

        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , al);

        lv.setAdapter(ad);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                TextView tv = (TextView) view;
                String str = tv.getText().toString();
                String text = "Item # "+i+" "+str;
                Toast.makeText(MainActivity.this,text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}