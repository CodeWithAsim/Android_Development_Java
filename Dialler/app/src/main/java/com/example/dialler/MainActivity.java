package com.example.dialler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int CODE_PERM = 101;

    EditText n ;
    EditText n2 ;

    ImageView i1 ;
    ImageView i2 ;

    String number ;
    String number2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        n=findViewById(R.id.num);
        n2=findViewById(R.id.num2);

        i1=findViewById(R.id.icon1);
        i2=findViewById(R.id.icon2);

        i1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                number = n.getText().toString();
                make_call(number);
            }
        });

        i2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                number2 = n2.getText().toString();
                make_call_2(number2);
            }
        });
    }

    public void make_call(String number)
    {
        Uri contact = Uri.parse("tel:+92"+number);
        Intent i = new Intent(Intent.ACTION_DIAL,contact);

        if(i.resolveActivity(getPackageManager())!=null)
        {
            Log.d("Asim", "make_call : resource not available");
        }
        else
        {
            startActivity(i);
        }
    }

    public void make_call_2(String number)
    {
        askPermission();
    }

    private void askPermission()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String [] {Manifest.permission.CALL_PHONE}, CODE_PERM);
        }
        else
        {
            go_for_it();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODE_PERM)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                go_for_it();
            }
            else
            {
                Toast.makeText(this, "Allow permission to make a call", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void go_for_it()
    {
        Uri contact = Uri.parse("tel:+92"+number);
        Intent i = new Intent(Intent.ACTION_CALL,contact);

        startActivity(i);
    }
}