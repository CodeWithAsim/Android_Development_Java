package com.example.customtoasts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class quiz extends AppCompatActivity
{
    ArrayList<String> al = new ArrayList<>();
    ListView lst ;
    int sel ;

    Random rand = new Random();
    int upper_bound = 11 ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        al.add("One");
        al.add("Two");
        al.add("Three");
        al.add("Four");
        al.add("Five");
        al.add("Six");
        al.add("Seven");
        al.add("Eight");
        al.add("Nine");
        al.add("Ten");

        lst = findViewById(R.id.lst);

        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,al);
        lst.setAdapter(ad);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                AlertDialog.Builder b = new AlertDialog.Builder(quiz.this);

                b.setTitle("Instruction");

                b.setMessage("You can choose only one option other wise it will be considered as wrong");

                b.setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i1) {

                        sel=0;

                        int mul = rand.nextInt(upper_bound);

                        String o1 = Integer.toString((i+1)*mul);
                        String o2 = Integer.toString(((i+1)*mul)+2);
                        String o3 = Integer.toString(((i+1)*mul)+4);

                        String arr[] = {o1,o2,o3};

                        AlertDialog.Builder b2 = new AlertDialog.Builder(quiz.this);

                        b2.setTitle(Integer.toString(i+1)+" x "+mul+" is equal to ");

                        b2.setMultiChoiceItems(arr, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i2, boolean b) {

                                if(b)
                                {
                                    sel = sel + Integer.parseInt(arr[i2]);
                                }
                                else
                                {
                                    sel = sel - Integer.parseInt(arr[i2]);
                                }
                            }
                        });

                        b2.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i3) {
                                if(sel==0)
                                {
                                    LayoutInflater inf =getLayoutInflater();
                                    View v = inf.inflate(R.layout.result,null);

                                    ImageView ri = v.findViewById(R.id.res_img);
                                    ri.setImageResource(R.drawable.q);

                                    Toast t = new Toast(quiz.this);
                                    t.setView(v);
                                    t.setDuration(Toast.LENGTH_SHORT);
                                    t.setGravity(Gravity.CENTER,0,0);
                                    t.show();
                                }
                                else
                                {
                                    if(sel==(i+1)*mul)
                                    {
                                        LayoutInflater inf =getLayoutInflater();
                                        View v = inf.inflate(R.layout.result,null);

                                        ImageView ri = v.findViewById(R.id.res_img);
                                        ri.setImageResource(R.drawable.ic_baseline_verified_24);

                                        Toast t = new Toast(quiz.this);
                                        t.setView(v);
                                        t.setDuration(Toast.LENGTH_SHORT);
                                        t.setGravity(Gravity.CENTER,0,0);
                                        t.show();
                                    }
                                    else if(sel!=(i+1)*mul)
                                    {
                                        LayoutInflater inf =getLayoutInflater();
                                        View v = inf.inflate(R.layout.result,null);

                                        ImageView ri = v.findViewById(R.id.res_img);
                                        ri.setImageResource(R.drawable.c);

                                        Toast t = new Toast(quiz.this);
                                        t.setView(v);
                                        t.setDuration(Toast.LENGTH_SHORT);
                                        t.setGravity(Gravity.CENTER,0,0);
                                        t.show();
                                    }
                                }
                            }
                        });

                        b2.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i4) {
                                dialogInterface.cancel();
                            }
                        });

                        AlertDialog alert2 = b2.create();
                        alert2.show();

                    }
                });

                AlertDialog alert = b.create();
                alert.show();

            }
        });

    }
}