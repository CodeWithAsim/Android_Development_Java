package com.example.bottomsheetdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {

    Button show_sheet;
    BottomSheetDialog bottomSheetDialog ;

    Button peek_sheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show_sheet = findViewById(R.id.show_sheet);
        peek_sheet = findViewById(R.id.peek_sheet);

        peek_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,BottomPeekHeight.class);
                startActivity(i);
            }
        });

        show_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog = new BottomSheetDialog(MainActivity.this,R.style.BottomSheetTheme);
                View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_layout,null);

                v.findViewById(R.id.lay1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Shared", Toast.LENGTH_SHORT).show();
                    }
                });

                v.findViewById(R.id.lay2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Linked", Toast.LENGTH_SHORT).show();
                    }
                });

                bottomSheetDialog.setContentView(v);
                bottomSheetDialog.show();
            }
        });
    }
}