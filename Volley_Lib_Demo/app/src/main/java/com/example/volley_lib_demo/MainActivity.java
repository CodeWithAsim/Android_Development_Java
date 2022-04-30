package com.example.volley_lib_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // just made a request queue

        Log.d("Asim", "onCreate: app running fine");

        RequestQueue rq ;
        rq = Volley.newRequestQueue(this);

        // just made the request to be queued in
        // String Request is also available in Volley
                                                                                                                              // if request ke sath kuch params bhejne tw
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/todos/1", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("Asim", "onResponse: Title : " + response.getString("title"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Asim", "onErrorResponse: Some thing went wrong !");
            }
        });

        // Ab is jor ko queue mai put krna hai

        rq.add(jor);

        // Ab ye queue khudi process ho jae gi

        // Ye concept ap agr dusri URL se bhi data tw put in queue - REQUEST QUEUE which is obtained from VOLLEY
    }
}