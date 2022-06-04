package com.example.customtoasts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button table_number ;
    ListView lst ;
    SeekBar seek ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        table_number =  findViewById(R.id.tblnum);
        lst = findViewById(R.id.lst);
        seek = findViewById(R.id.seek);

        seek.setMax(10);

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                populateInListView(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    public void populateInListView(int tn)
    {
        ArrayList<String> al = new ArrayList<>();

        for(int i=0 ; i<=10 ; i++)
        {
            al.add(tn+" x "+i+" = "+String.valueOf(tn*i));
        }

        ArrayAdapter<String> ad = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,al);
        lst.setAdapter(ad);
        table_number.setText("Table Of "+tn);
    }

}