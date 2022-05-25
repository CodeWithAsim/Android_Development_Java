package com.example.friendlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> al = new ArrayList<>();

    ListView lv ;
    EditText n ;
    ImageView plus;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        al.add("Asim");
        al.add("Hassan");
        al.add("Jawad");
        al.add("Zain");
        al.add("Mahad");
        al.add("Faizan");

        lv = findViewById(R.id.frndlst);
        n = findViewById(R.id.enter_name);
        plus = findViewById(R.id.add);

        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,al);
        lv.setAdapter(ad);

        plus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                al.add(n.getText().toString());
                ad.notifyDataSetChanged();
                n.setText("");
            }
        });


    }
}