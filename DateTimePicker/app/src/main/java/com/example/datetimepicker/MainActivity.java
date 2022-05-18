package com.example.datetimepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ImageView clck , clndr  ;
    TextView tm , dt ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clck = findViewById(R.id.clock);
        clndr = findViewById(R.id.cal);

        tm = findViewById(R.id.timeshow);
        dt = findViewById(R.id.dateshow);

        Calendar c1 = Calendar.getInstance();
        int hour = c1.get(Calendar.HOUR);
        int min = c1.get(Calendar.MINUTE);
        CharSequence t_show = DateFormat.format("hh:mm a",c1);
        tm.setText(t_show);

        Calendar c2 = Calendar.getInstance();
        int year = c2.get(Calendar.YEAR);
        int month = c2.get(Calendar.MONTH);
        int date = c2.get(Calendar.DATE);
        CharSequence d_show = DateFormat.format("EEEE,dd MMM yyyy",c2);
        dt.setText(d_show);

        tm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_time();
            }
        });

        clck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_time();
            }
        });

        dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_date();
            }
        });

        clndr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_date();
            }
        });

    }

    public void set_time()
    {
        // Log.d("Asim", "set_time : Runned");

        Calendar c = Calendar.getInstance();

        int hour = c.get(Calendar.HOUR);
        int min = c.get(Calendar.MINUTE);

        boolean is24 = DateFormat.is24HourFormat(this);

        TimePickerDialog tpd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int h, int m)
            {
                Calendar d_cal = Calendar.getInstance();
                d_cal.set(Calendar.HOUR,h);
                d_cal.set(Calendar.MINUTE,m);

                CharSequence t_show = DateFormat.format("hh:mm a",d_cal);
                tm.setText(t_show);
            }
        },hour,min,is24);

        tpd.show();

    }

    public void set_date()
    {
        Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);

        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date)
            {
                // dt.setText(Integer.toString(year));

                Calendar d_cal = Calendar.getInstance();
                d_cal.set(Calendar.YEAR,year);
                d_cal.set(Calendar.MONTH,month);
                d_cal.set(Calendar.DATE,date);

                CharSequence d_show = DateFormat.format("EEEE,dd MMM yyyy",d_cal);
                dt.setText(d_show);
            }
        },year,month,date);

        dpd.show();

    }

}