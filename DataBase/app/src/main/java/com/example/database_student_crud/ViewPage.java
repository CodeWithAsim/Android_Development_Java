package com.example.database_student_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ViewPage extends AppCompatActivity {

    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);

        lst = findViewById(R.id.lstview);

        DbHelper db = new DbHelper(this);

        ArrayList<Student> students = db.viewStudents();
        //ArrayAdapter<Student> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,students);
        MyAdapter ad = new MyAdapter(ViewPage.this,students);

        lst.setAdapter(ad);
    }
}