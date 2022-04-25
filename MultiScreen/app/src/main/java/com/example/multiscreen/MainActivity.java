package com.example.multiscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String MSG = "com.example.multiscreen.orders" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void place_order (View v)
    {
        EditText e = findViewById(R.id.editTextTextPersonName);
        EditText e2 = findViewById(R.id.editTextTextPersonName2);
        EditText e3 = findViewById(R.id.editTextTextPersonName3);

        String msg = "Your order : \n"+e.getText().toString()+" \n "+e2.getText().toString()+" \n "+e3.getText().toString() +" \nis placed successfully ! ";

        Intent intent = new Intent(this , OrderedActivity.class);
        intent.putExtra(MSG,msg);
        startActivity(intent);

    }

}