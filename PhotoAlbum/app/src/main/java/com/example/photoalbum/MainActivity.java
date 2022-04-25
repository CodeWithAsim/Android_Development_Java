package com.example.photoalbum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    TextView t ;
    ImageView i ;
    int curr_pic = 0 ;

    // 0-1-2-3-4-5-0-1-2-3-4-5

    String [] names = {"Virat","Babar","Williamson","Ben Stokes","David Warner","ABD Villers"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t=findViewById(R.id.name);
    }

    public void prev(View v)
    {
        String s_id = "pic_"+curr_pic ;
        int i_id = this.getResources().getIdentifier(s_id,"id",getPackageName());
        i=findViewById(i_id);

        curr_pic= (6+curr_pic-1)%6;

        i.setAlpha(0f);

        s_id = "pic_"+curr_pic ;
        i_id = this.getResources().getIdentifier(s_id,"id",getPackageName());
        i=findViewById(i_id);

        t.setText(names[curr_pic]);
        i.setAlpha(1f);

    }

    public void next(View v)
    {

        String s_id = "pic_"+curr_pic ;
        int i_id = this.getResources().getIdentifier(s_id,"id",getPackageName());
        i=findViewById(i_id);

        curr_pic= (curr_pic+1)%6;

        i.setAlpha(0f);

        s_id = "pic_"+curr_pic ;
        i_id = this.getResources().getIdentifier(s_id,"id",getPackageName());
        i=findViewById(i_id);

        t.setText(names[curr_pic]);
        i.setAlpha(1f);

    }

}