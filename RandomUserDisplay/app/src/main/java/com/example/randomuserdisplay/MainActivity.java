package com.example.randomuserdisplay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void grab(View v)
    {
        RequestQueue rq  = Volley.newRequestQueue(this);

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, "https://randomuser.me/api/", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                String JsonString = response.toString();

                int ffname = JsonString.lastIndexOf("first") + 8 ;
                int lfname = JsonString.lastIndexOf("last") - 3 ;
                String fn = JsonString.substring(ffname,lfname);

                int flname = JsonString.lastIndexOf("last") + 8 ;
                int llname = JsonString.lastIndexOf("location") - 4 ;
                String ln = JsonString.substring(flname,llname);

                TextView name = findViewById(R.id.textView7);
                name.setText(fn+" "+ln);

                int fmail = JsonString.lastIndexOf("email") + 8 ;
                int lmail = JsonString.lastIndexOf("login") - 3 ;
                String a = JsonString.substring(fmail,lmail);
                TextView age = findViewById(R.id.textView8);
                age.setText(a);

                int fcountry = JsonString.lastIndexOf("country") + 10 ;
                int lcountry = JsonString.lastIndexOf("postcode") - 3 ;
                String c = JsonString.substring(fcountry,lcountry);
                TextView country = findViewById(R.id.textView9);
                country.setText(c);

                int fcell = JsonString.lastIndexOf("cell") + 7 ;
                int lcell = JsonString.lastIndexOf("id") - 3 ;
                String cel = JsonString.substring(fcell,lcell);
                TextView cell = findViewById(R.id.textView10);
                cell.setText(cel);

                int fgen = JsonString.lastIndexOf("gender") + 9 ;
                int lgen = JsonString.lastIndexOf("title") - 11 ;
                String gen = JsonString.substring(fgen,lgen);
                TextView gender = findViewById(R.id.textView11);
                gender.setText(gen);

                int furl = JsonString.lastIndexOf("large") + 8 ;
                int lurl = JsonString.lastIndexOf("medium") - 3 ;
                String url = JsonString.substring(furl,lurl);
                ImageView img = findViewById(R.id.imageView);
                Picasso.get().load(url).into(img);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Asim", "Some thing went wrong ! ");
                Toast.makeText(MainActivity.this, "Some thing went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        rq.add(jor);

    }
}