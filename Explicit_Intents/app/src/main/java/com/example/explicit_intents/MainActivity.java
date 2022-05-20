package com.example.explicit_intents;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
{

    ImageView i , i2 , i3;
    EditText to , place , str ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        i=findViewById(R.id.msg);
        i2=findViewById(R.id.loc);
        i3=findViewById(R.id.pic);
        to=findViewById(R.id.to);
        place=findViewById(R.id.place);
        str=findViewById(R.id.str);

        i.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String t = to.getText().toString();
                String m = str.getText().toString();
                Intent i = new Intent (Intent.ACTION_SENDTO, Uri.parse("sms:"+t));
                i.putExtra("sms_body",m);
                startActivity(i);
            }
        });

        i2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String p = place.getText().toString();
                Intent i = new Intent (Intent.ACTION_VIEW);
                i.setData(Uri.parse("geo:0,0?q="+p));
                startActivity(i);
            }
        });

    }

    public void cam(View view)
    {
        // Intent.ACTION_OPEN_DOCUMENT

        Intent i = new Intent(Intent.ACTION_GET_CONTENT);

        //i.addCategory(Intent.CATEGORY_OPENABLE);

        i.setType("image/*");

        // String[] mime = {"",""};
        // i.putExtra(Intent.EXTRA_MIME_TYPES,mime);

        // arl.launch(Intent.createChooser(i,"Choose app"));

        arl.launch(i);
        
    }
    
    ActivityResultLauncher<Intent> arl = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==Activity.RESULT_OK)
                    {
                        Intent data = result.getData();
                        Uri uri = data.getData();
                        i3.setImageURI(uri);
                    }
                }
            });
}