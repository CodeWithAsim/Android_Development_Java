package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.contacts.handler.DBHandler;
import com.example.contacts.model.contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHandler db = new DBHandler(MainActivity.this);

        /*Log.d("Asim", "OnCreate : In MainActivity - No error un till here");

        // CREATE

        contact asim = new contact();
        asim.setName("ASIM");
        asim.setPhone_no("03094148743");

        db.add_contact(asim);

        contact hassan = new contact();
        hassan.setName("HASSAN");
        hassan.setPhone_no("03085158844");

        db.add_contact(hassan);

        // UPDATE

        asim.setID(1);
        asim.setName("Changed Asim");
        asim.setPhone_no("000000000000");
        int rec = db.update_contact(asim);
        Log.d("Asim", "Update_Contact : Records affected or updated : "+rec);

        // DELETE

        db.delete_contact_by_id(1);

        hassan.setID(15);
        db.delete_contact(hassan);
*/
        // READ

        List<contact> l ;
        l = db.get_all();

        Log.d("Asim", "Length : "+l.size());

        // SETTING TO LIST VIEW ON FRONT END

        lv = findViewById(R.id.listView);
        ArrayList<String> al = new ArrayList<>();

        // USING ITERATOR FOR ITERATING

        for(contact con:l)
        {
            int fixed =40;

            String s1 = con.getName();
            int len1 = s1.length();
            int space1 = fixed-len1;

            String str = String.format("%-"+space1+"s %"+space1+"s",con.getName(),con.getPhone_no());

            al.add(str);

            Log.d("Asim", "Get_All : \nID : "+con.getID()+"\nName : "+con.getName()+"\nPhone# : "+con.getPhone_no());

        }

        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,al);
        lv.setAdapter(ad);

        // GETTING COUNT

        int total = db.get_total();
        Log.d("Asim", "Get_Total : You have "+total+" records or contacts ! ");
    }
}