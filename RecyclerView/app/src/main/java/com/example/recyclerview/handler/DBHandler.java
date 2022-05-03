package com.example.recyclerview.handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.recyclerview.data.params;
import com.example.recyclerview.model.contact;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper
{
    public DBHandler(Context context)
    {
        super(context, params.DB_NAME, null, params.DB_VERSION);
        Log.d("Asim", "DBHandler : DB constructor run successfully");
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String create = "CREATE TABLE "+params.TABLE_NAME+" ("+params.KEY_ID+" INTEGER PRIMARY KEY ,"+params.NAME+" TEXT ,"+params.PHONE_NO+" TEXT )";
        db.execSQL(create);
        Log.d("Asim", "OnCreateDB: Query being run successfully is : "+create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }

    // OPERATIONAL METHODS FOR CRUD OPERATIONS

    public void add_contact(contact c)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(params.NAME,c.getName());
        values.put(params.PHONE_NO,c.getPhone_no());

        db.insert(params.TABLE_NAME,null,values);

        Log.d("Asim", "Add_Contact : Contact Added Successfully In Table");

        db.close();
    }

    public List<contact> get_all()
    {
        List<contact> l = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM "+params.TABLE_NAME ;
        Cursor c  = db.rawQuery(select,null);

        if(c.moveToFirst())
        {
            do
            {
                contact con = new contact();

                con.setID(Integer.parseInt(c.getString(0)));
                con.setName(c.getString(1));
                con.setPhone_no(c.getString(2));

                l.add(con);

            }while(c.moveToNext());
        }

        db.close();
        return l ;
    }

    public int  update_contact(contact c)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(params.NAME,c.getName());
        values.put(params.PHONE_NO,c.getPhone_no());

        return db.update(params.TABLE_NAME,values,params.KEY_ID+"=?",new String[] {String.valueOf(c.getID())});

    }

    // STRING IS BOUNDED SO , WE USED String.valueOf
    public void delete_contact_by_id (int  id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(params.TABLE_NAME,params.KEY_ID+"=?",new String[] {String.valueOf(id)});
        db.close();
    }

    public void delete_contact (contact c)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(params.TABLE_NAME,params.KEY_ID+"=?",new String[] {String.valueOf(c.getID())});
        db.close();
    }

    // GETTING COUNT

    public int get_total()
    {
        SQLiteDatabase db =this.getReadableDatabase();
        String select = "SELECT * FROM "+params.TABLE_NAME;
        Cursor c = db.rawQuery(select,null);
        int rec = c.getCount();
        db.close();
        return rec ;
    }
}
