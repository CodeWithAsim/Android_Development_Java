package com.example.alquran;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    public static String DB_NAME = "Quran.db";
    public static String DB_LOCATION = "/data/data/com.example.alquran/databases/";
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // Checking for open
    public void Open()
    {
        String Path = context.getDatabasePath(DB_NAME).getPath();

        if(sqLiteDatabase!=null&&sqLiteDatabase.isOpen())
        {
            return;
        }

        sqLiteDatabase = SQLiteDatabase.openDatabase(Path,null,SQLiteDatabase.OPEN_READWRITE);
    }

    // Checking for close
    public void Close()
    {
        if(sqLiteDatabase!=null)
        {
            sqLiteDatabase.close();
        }
    }

    public List<SurahInfo> searchedSurah(String search)
    {
        List<SurahInfo> searched_list = new ArrayList<>();
        Open();

        Cursor c = sqLiteDatabase.rawQuery("Select SurahID,SurahNameE,SurahNameU,Nazool,SurahIntro from tsurah as S join tayah as A on S.SurahID=A.SuraID AND A.AyaNo=1 where SurahNameE like '%"+search+"%'",null);
        if(c.moveToFirst())
        {
            do{
                SurahInfo s_info = new SurahInfo(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));
                searched_list.add(s_info);
            }while(c.moveToNext());
        }

        return searched_list;
    }

}
