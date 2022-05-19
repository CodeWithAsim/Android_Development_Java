package com.example.google_it;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    EditText t ;
    ImageView i ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t = findViewById(R.id.search);
        i = findViewById(R.id.go);

        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = t.getText().toString();
                search_it(text);
            }
        });
    }

    private void search_it(String text) {
        Uri webpage = Uri.parse("https://www.google.com/search?q="+text);
        Intent i = new Intent(Intent.ACTION_VIEW,webpage);
        startActivity(i);
    }
}