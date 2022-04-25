package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private EditText calculation, result ;
    private Button btn_0 , btn_1 , btn_2 , btn_3 , btn_4 , btn_5 , btn_6 , btn_7 , btn_8 , btn_9 , btn_dev , btn_mul , btn_sub , btn_add , btn_point , btn_equal , btn_clear , btn_backspace ;

    private String  curr ,res ;

    boolean dot_inserted , operator_inserted ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculation = (EditText) findViewById(R.id.calculation);
        result = (EditText) findViewById(R.id.result);

        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_dev = (Button) findViewById(R.id.btn_dev);
        btn_mul = (Button) findViewById(R.id.btn_mul);
        btn_sub = (Button) findViewById(R.id.btn_sub);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_point = (Button) findViewById(R.id.btn_dot);
        btn_equal = (Button) findViewById(R.id.btn_equal);

        btn_clear = (Button) findViewById(R.id.clear);
        btn_backspace = (Button) findViewById(R.id.backspace);

        curr = "" ;
        res = "" ;

        dot_inserted = false ;
        operator_inserted = false ;

        //displayOne();
        //displayTwo();

        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curr = curr + "0";
                displayOne();
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curr = curr + "1";
                displayOne();
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curr = curr + "2";
                displayOne();
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curr = curr + "3";
                displayOne();
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curr = curr + "4";
                displayOne();
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curr = curr + "5";
                displayOne();
            }
        });

        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curr = curr + "6";
                displayOne();
            }
        });

        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curr = curr + "7";
                displayOne();
            }
        });

        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curr = curr + "8";
                displayOne();
            }
        });

        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curr = curr + "9";
                displayOne();
            }
        });

        btn_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(curr.isEmpty())
                {
                    curr = curr + "0.";
                    dot_inserted=true;
                }
                if(dot_inserted==false)
                {
                    curr = curr + ".";
                    dot_inserted=true;
                }
                displayOne();
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
                displayOne();
                displayTwo();
            }
        });

        btn_backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backspace();
                displayOne();
            }
        });

        btn_dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!curr.isEmpty())
                {
                    if(curr.substring(curr.length()-1,curr.length()).equals("."))
                    {
                        backspace();
                    }
                    if(operator_inserted==false)
                    {
                        curr = curr + " / ";
                        operator_inserted = true ;
                    }
                }

                dot_inserted = false ;
                displayOne();
            }
        });

        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!curr.isEmpty())
                {
                    if(curr.substring(curr.length()-1,curr.length()).equals("."))
                    {
                        backspace();
                    }
                    if(operator_inserted==false)
                    {
                        curr = curr + " * ";
                        operator_inserted = true ;
                    }
                }

                dot_inserted = false ;
                displayOne();
            }
        });

        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!curr.isEmpty())
                {
                    if(curr.substring(curr.length()-1,curr.length()).equals("."))
                    {
                        backspace();
                    }
                    if(operator_inserted==false)
                    {
                        curr = curr + " - ";
                        operator_inserted = true ;
                    }
                }

                dot_inserted = false ;
                displayOne();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!curr.isEmpty())
                {
                    if(curr.substring(curr.length()-1,curr.length()).equals("."))
                    {
                        backspace();
                    }
                    if(operator_inserted==false)
                    {
                        curr = curr + " + ";
                        operator_inserted = true ;
                    }
                }

                dot_inserted = false ;
                displayOne();
            }
        });

        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(operator_inserted==true && !curr.substring(curr.length()-1,curr.length()).equals(" "))
                {
                    String [] tokens = curr.split(" ");
                    switch(tokens[1])
                    {
                        case "/":
                            res = Double.toString(Double.parseDouble(tokens[0])/Double.parseDouble(tokens[2])) ;
                            break ;
                        case "*":
                            res = Double.toString(Double.parseDouble(tokens[0])*Double.parseDouble(tokens[2])) ;
                            break ;
                        case "-":
                            res = Double.toString(Double.parseDouble(tokens[0])-Double.parseDouble(tokens[2])) ;
                            break ;
                        case "+":
                            res = Double.toString(Double.parseDouble(tokens[0])+Double.parseDouble(tokens[2])) ;
                            break ;
                    }
                    displayTwo();
                }
            }
        });

    }

    public void displayOne()
    {
        // curr = "2*3";
        calculation.setText(curr);
    }

    public void displayTwo()
    {
        // res = "6";
        result.setText(res);
    }

    public void clear()
    {
        curr = "";
        res = "";
        dot_inserted = false ;
        operator_inserted = false ;
    }

    public void backspace()
    {
        if(!curr.isEmpty())
        {
            if(curr.substring(curr.length()-1,curr.length()).equals("."))
            {
                dot_inserted=false;
            }

            if (curr.substring(curr.length()-1,curr.length()).equals(" "))
            {
                curr = curr.substring(0,curr.length()-3);
                operator_inserted = false ;
                if(curr.indexOf(".")!=-1)
                {
                    dot_inserted=true;
                }
            }
            else
            {
                curr=curr.substring(0,curr.length()-1);
            }

        }
    }

}