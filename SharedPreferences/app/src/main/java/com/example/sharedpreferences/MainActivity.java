package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //public int number = 1;
    public int number ;

    TextView t ;
    EditText e ;
    ImageView s ;
    Button c ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t = findViewById(R.id.textView2);
        e = findViewById(R.id.editText);
        s = findViewById(R.id.imageView);
        c = findViewById(R.id.button);

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String note = e.getText().toString();

                String chk = t.getText().toString();
                if(chk.equals("All notes are removed")||chk.equals("First save note to preview here"))
                {
                    t.setText("");
                }

                SharedPreferences sp = getSharedPreferences("demo",MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();

                String number_s = sp.getString("counter","0");
                number = Integer.parseInt(number_s)+1;

                String snum = Integer.toString(number);
                edit.putString("msg_"+number,note);
                edit.putString("counter",snum);
                edit.apply(); // we have to commit changes to the shared preferences file

                t.append("\n"+number+"_"+note);
                //number ++ ;

            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                SharedPreferences sp = getSharedPreferences("demo",MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();

                edit.clear();
                edit.apply();

                t.setText("All notes are removed");
            }
        });

        SharedPreferences get_sp = getSharedPreferences("demo",MODE_PRIVATE); // just like opening a file

        String scounter = get_sp.getString("counter","0");
        int icounter = Integer.parseInt(scounter);
        String str ;

        if(icounter==0)
        {
            t.setText("First save note to preview here");
        }

        for(int i=1;i<=icounter;i++)
        {
            str = get_sp.getString("msg_"+i, "First save note to preview here");
            t.append("\n"+i+"_"+str);
        }
    }

}