package com.example.database_student_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name ;
    EditText age ;
    Button add ;
    Button view ;

    String _name;
    int _age;
    int _imageId;

    DbHelper db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        add = findViewById(R.id.add);
        view =findViewById(R.id.view);

        db = new DbHelper(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                age.onEditorAction(EditorInfo.IME_ACTION_DONE);

                _name = name.getText().toString();
                _age = Integer.parseInt(age.getText().toString());
                _imageId = R.drawable.photo;

                Student s = new Student(_name,_age,_imageId);

                try
                {
                    db.addStudent(s);
                    Toast.makeText(MainActivity.this, "Add Successfully", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    age.setText("");
                }
                catch(Exception e)
                {
                    Toast.makeText(MainActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ViewPage.class);
                startActivity(i);
            }
        });

    }
}