package com.example.backgroundtask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    URL url ;
    HttpURLConnection conn ;

    public class BGT extends AsyncTask<String,Void,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("Asim", "onPreExecute: do");
        }

        @Override
        protected String doInBackground(String... urls) {
            Log.d("Asim", "doInBackground: did");
            try
            {
                String response = "" ;
                url=new URL(urls[0]);
                conn=(HttpURLConnection) url.openConnection();

                InputStream input = conn.getInputStream();
                InputStreamReader reader = new InputStreamReader(input);

                int data = reader.read();

                while(data != -1)
                {
                    response += (char)data;
                    data = reader.read();
                }

                return response;
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return "Some thing went wrong while creating connection to URL";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("Asim", "onPostExecute: done");

            Log.d("Asim", "onPostExecute: "+s);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BGT obj = new BGT();
        obj.execute("https://codewithharry.com/");
    }

    public void press_me(View v)
    {
        Toast.makeText(this, "Button is Pressed", Toast.LENGTH_SHORT).show();
    }
}