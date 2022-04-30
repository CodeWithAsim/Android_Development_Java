package com.example.volley_lib_demo_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void press (View v)
    {
        TextView t = findViewById(R.id.textView);
        t.setText("Go in logcat to see the data fetched from JSON Place Holder using the Volley Library");

        RequestQueue rq = Volley.newRequestQueue(this);

        JsonArrayRequest jar = new JsonArrayRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/todos", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0 ; i<response.length() ; i++)
                {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Log.d("Asim", "Title is : "+obj.getString("title")+" and ID is : "+obj.getInt("id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Asim", "Some thing went wrong !");
                Toast.makeText(MainActivity.this, "Some thing went wrong !", Toast.LENGTH_SHORT).show();
            }
        });

        rq.add(jar);

    }
}