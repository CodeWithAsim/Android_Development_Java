package com.example.loginform;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "mydb";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null ,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE registered (ID PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS registered");
        onCreate(db);
    }

    public boolean insert(String user ,String pwd)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("username",user);
        cv.put("password",pwd);

        long result = db.insert("registered",null,cv);

        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public boolean check_uniqueness(String user)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM registered WHERE username=?",new String[] {user});
        if(c.getCount() > 0)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public boolean check_login(String user,String pwd)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM registered WHERE username=? AND password=?",new String[] {user,pwd});
        if(c.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
}
