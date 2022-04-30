package com.example.multiplicationtables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tv ;
    ListView lv ;
    SeekBar sb ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        lv = findViewById(R.id.listView);
        sb = findViewById(R.id.seekBar);

        sb.setMax(20);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                // Toast.makeText(MainActivity.this, "Table # "+i, Toast.LENGTH_SHORT).show();
                populateTableInListView(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void populateTableInListView(int tableNo)
    {
        ArrayList<String> al = new ArrayList<>();
        for(int i = 1 ; i<=10 ; i++)
        {
            al.add(tableNo+" x "+i+" = "+tableNo*i);
        }
        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,al);

        tv.setText("Table Of "+tableNo);

        lv.setAdapter(ad);
    }
}