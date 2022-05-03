package com.example.loginform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    DatabaseHelper db ;
    EditText u;
    EditText e;
    EditText p;
    EditText cp;
    Button register ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ImageView i1 = findViewById(R.id.imageView2);
        ImageView i2 = findViewById(R.id.imageView3);
        ImageView i3 = findViewById(R.id.imageView5);

        i1.setTranslationY(300f);

        i1.animate().translationYBy(-300f).setStartDelay(100).start();

        i2.setTranslationY(300f);

        i2.animate().translationYBy(-300f).setStartDelay(100).start();

        i3.setTranslationY(300f);

        i3.animate().translationYBy(-300f).setStartDelay(100).start();

        db = new DatabaseHelper(this);
        u = findViewById(R.id.editTextTextPersonName);
        e = findViewById(R.id.editTextTextPersonName2);
        p = findViewById(R.id.editTextTextPersonName3);
        cp = findViewById(R.id.editTextTextPersonName4);
        register =findViewById(R.id.button);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String user=u.getText().toString();
                String email=e.getText().toString();
                String pwd=p.getText().toString();
                String confirm=cp.getText().toString();

                if(user.equals("")||email.equals("")||pwd.equals("")||confirm.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Credentials Incomplete", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(pwd.equals(confirm))
                    {
                        boolean check_uniqueness = db.check_uniqueness(user);

                        if(check_uniqueness==true)
                        {
                            boolean insert = db.insert(user,pwd);
                            if(insert==true)
                            {
                                u.setText("");
                                e.setText("");
                                p.setText("");
                                cp.setText("");

                                Intent i = new Intent(SignupActivity.this,WelcomeActivity.class);
                                i.putExtra("name",user);
                                startActivity(i);

                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Server Busy ! Try Again", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "User name already taken", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Confirm password does not match", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

    }

    public void login(View v)
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}