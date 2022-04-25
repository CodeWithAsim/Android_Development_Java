package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // declaring the views (components)
    private TextView p1_score,p2_score,game_status;
    private Button [] buttons = new Button [9] ;
    private Button reset_game ;

    // instance variables - for storing data
    private  int p1_score_count,p2_score_count,turn_count;

    boolean active_player ;

    // p1 - 0
    // p2 - 1
    // empty - 2

    int [] game_state = {2,2,2,2,2,2,2,2,2}; // initially empty

    int [][] winning_positions = {
            {0,1,2},{3,4,5},{6,7,8}, // rows
            {0,3,6},{1,4,7},{2,5,8}, // columns
            {0,4,8},{2,4,6} // diagonals
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p1_score = (TextView) findViewById(R.id.p1_score);
        p2_score = (TextView) findViewById(R.id.p2_score);
        game_status = (TextView) findViewById(R.id.game_status);

        reset_game = (Button) findViewById(R.id.reset_game);

        for (int i = 0 ; i<buttons.length ; i++)
        {
            String buttons_ID = "btn_"+i;
            int resource_ID = getResources().getIdentifier(buttons_ID ,"id",getPackageName());
            buttons[i] = (Button) findViewById(resource_ID);
            buttons[i].setOnClickListener(this);

        }

        p1_score_count=0;
        p2_score_count=0;
        turn_count=0;
        active_player=true;

    }

    @Override
    public void onClick(View view)
    {
        // Log.i("test", "I am clicked");
        if(!((Button)view).getText().toString().equals(""))
        {
            return ;
        }

        String button_ID = view.getResources().getResourceEntryName(view.getId());
        int game_state_pointer = Integer.parseInt(button_ID.substring(button_ID.length()-1,button_ID.length()));

        if(active_player)
        {
            ((Button)view).setText("X");
            ((Button)view).setTextColor(Color.parseColor("#54D85C"));
            game_state[game_state_pointer]=0;
        }
        else
        {
            ((Button)view).setText("O");
            ((Button)view).setTextColor(Color.parseColor("#54D8D2"));
            game_state[game_state_pointer]=1;
        }

        turn_count ++ ;
        if(check_winner())
        {
            if(active_player)
            {
                p1_score_count ++ ;
                update_score();
                Toast.makeText(this, "Player 1 won !", Toast.LENGTH_SHORT).show();
                play_again();
            }
            else
            {
                p2_score_count ++ ;
                update_score();
                Toast.makeText(this, "Player 2 won !", Toast.LENGTH_SHORT).show();
                play_again();
            }
        }
        else if(turn_count==9)
        {
            Toast.makeText(this, "Match Draws", Toast.LENGTH_SHORT).show();
            play_again();
        }
        else
        {
            active_player = ! active_player;
        }

        if(p1_score_count>p2_score_count)
        {
            game_status.setText("Player 1 is winning !");
        }
        else if(p2_score_count>p1_score_count)
        {
            game_status.setText("Player 2 is winning !");
        }
        else
        {
            game_status.setText("");
        }

        reset_game.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                p1_score_count = 0;
                p2_score_count = 0;
                update_score();
                play_again();
                game_status.setText("");

            }
        });
    }

    public boolean check_winner()
    {
        boolean winner = false;
        for(int [] winning_position : winning_positions)
        {
            if(game_state[winning_position[0]]==game_state[winning_position[1]] && game_state[winning_position[1]]==game_state[winning_position[2]] && game_state[winning_position[0]]!=2)
            {
                winner=true;
            }
        }
        return winner ;
    }

    public void update_score()
    {
        p1_score.setText(Integer.toString(p1_score_count));
        p2_score.setText(Integer.toString(p2_score_count));
    }

    public void play_again()
    {
        turn_count = 0;
        active_player = true ;

        for(int i = 0 ; i<buttons.length ; i++)
        {
            game_state[i]=2;
            buttons[i].setText("");
        }
    }

}