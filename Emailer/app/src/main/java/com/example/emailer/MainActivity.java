package com.example.emailer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText to;
    EditText sub;
    EditText msg;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        to=findViewById(R.id.to);
        sub=findViewById(R.id.sub);
        msg=findViewById(R.id.msg);
        send=findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send_mail();
            }
        });
    }

    private void send_mail() {
        String _to = to.getText().toString();
        String recep [] = _to.split(",");

        String _sub = sub.getText().toString();
        String _msg = msg.getText().toString();

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL,recep); // receives
        i.putExtra(Intent.EXTRA_SUBJECT,_sub);
        i.putExtra(Intent.EXTRA_TEXT,_msg);
        // startActivity(Intent.createChooser(i,"Choose app")) ; // not showing the title !
        startActivity(i);
    }
}