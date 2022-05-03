package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.recyclerview.adapter.RecyclerViewAdapter;
import com.example.recyclerview.handler.DBHandler;
import com.example.recyclerview.model.contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv ;
    private ArrayList<contact> al ;
    private RecyclerViewAdapter ad ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHandler db = new DBHandler(MainActivity.this);

        // CREATE

        contact asim = new contact();
        asim.setName("ASIM");
        asim.setPhone_no("03094148743");

//        db.add_contact(asim);

        contact hassan = new contact();
        hassan.setName("HASSAN");
        hassan.setPhone_no("03085158844");

//        db.add_contact(hassan);

        // READ

        List<contact> l ;
        l = db.get_all();

        rv = findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<contact> al = new ArrayList<>();

        // USING ITERATOR FOR ITERATING

        for(contact con:l)
        {
            al.add(con);

        }

        ad = new RecyclerViewAdapter(this,al);
        rv.setAdapter(ad);

        // GETTING COUNT

        int total = db.get_total();
        Log.d("Asim", "Get_Total : You have "+total+" records or contacts ! ");

    }
}