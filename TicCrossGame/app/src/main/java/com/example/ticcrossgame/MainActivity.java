package com.example.ticcrossgame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 2 flags  for flow control
    int turn_count = 0 ;

    boolean game_active = true ;

    // 0 - cross
    // 1 - tick
    int active_player = 0 ;

    // state meanings
    // 2 - empty
    // 1 - tick
    // 0 - cross
    int [] game_state = {2,2,2,2,2,2,2,2,2};

    //winning positions
    int [][] winning_positions = {
            {0,1,2},{3,4,5},{6,7,8}, // rows
            {0,3,6,},{1,4,7},{2,5,8}, // columns
            {0,4,8},{2,4,6} // diagonals
    } ;

    public void tapped(View v)
    {
        ImageView img = (ImageView)v ;
        TextView status = (TextView) findViewById(R.id.textView2);
        int tag = Integer.parseInt(img.getTag().toString()) ;

        if(game_active==false)
        {
            game_reset();
            return ;
        }

        if(game_state[tag]==2)
        {
            game_state[tag]=active_player;
            img.setTranslationY(-1000f);
            if(active_player==0)
            {
                img.setImageResource(R.drawable.crosstwo);
                active_player=1;
                status.setText("Tick Turns - Tap to play !");
            }
            else
            {
                img.setImageResource(R.drawable.tick);
                active_player=0;
                status.setText("Cross Turns - Tap to play !");
            }
            img.animate().translationYBy(1000f).setDuration(300);
            turn_count ++ ;

        }

        if(turn_count==9)
        {
            status = (TextView) findViewById(R.id.textView2);
            status.setText("Match Draws - Tap for next game");
            status.setTextColor(Color.parseColor("#2BBBB4"));

            game_active = false ;
            turn_count=0;

        }

        for(int [] winning_position : winning_positions)
        {
            if(game_state[winning_position[0]]==game_state[winning_position[1]] &&
                    game_state[winning_position[1]]==game_state[winning_position[2]] &&
                    game_state[winning_position[0]]!=2)
            {
                if(game_state[winning_position[0]]==0)
                {
                    status.setText("Cross Wins ! Tap for next game");
                    status.setTextColor(Color.parseColor("#64E330"));

                }
                else
                {
                    status.setText("Tick Wins ! Tap for next game");
                    status.setTextColor(Color.parseColor("#64E330"));
                }
                game_active = false ;
                turn_count=0;

            }

        }

    }

    public void game_reset()
    {
        active_player=0;
        TextView status = (TextView) findViewById(R.id.textView2);
        status.setText("Cross turn - Tap to play");

        status.setTextColor(Color.parseColor("#878884"));

        for(int i = 0 ; i<game_state.length ; i++)
        {
            game_state[i]=2;
        }

        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView10)).setImageResource(0);

        game_active = true ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}