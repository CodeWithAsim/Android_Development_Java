package com.example.loginform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db ;
    EditText u;
    EditText p;
    Button l;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        u = findViewById(R.id.editTextTextPersonName);
        p = findViewById(R.id.editTextTextPersonName2);
        l = findViewById(R.id.button);

        l.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String user = u.getText().toString();
                String pwd = p.getText().toString();

                if(user.equals("")||pwd.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Credentials incomplete", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    db = new DatabaseHelper(MainActivity.this);
                    boolean check = db.check_login(user,pwd);
                    if(check)
                    {
                        Intent i = new Intent(MainActivity.this,WelcomeActivity.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

    public void signup(View v)
    {
        Intent i = new Intent(this,SignupActivity.class);
        startActivity(i);
    }

}