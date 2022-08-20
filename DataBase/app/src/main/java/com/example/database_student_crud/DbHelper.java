package com.example.database_student_crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(@Nullable Context context) {
        super(context,"student.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createStatement = "Create table studentTbl( Id integer primary key autoincrement , Name text , Age integer , ImageId integer )";
        db.execSQL(createStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropStatement = "Drop table if exists studentTbl";
        db.execSQL(dropStatement);
        onCreate(db);
    }

    public void addStudent(Student s)
    {
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("Name",s.getName());
        cv.put("Age",s.getAge());
        cv.put("ImageId",s.getImageId());

        db.insert("studentTbl",null,cv);
        db.close();
    }

    public ArrayList<Student> viewStudents()
    {
        ArrayList<Student> arr = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * from studentTbl",null);

        if(c.moveToFirst())
        {
            do
            {
                int id = c.getInt(0);
                String name= c.getString(1);
                int age = c.getInt(2);
                int image = c.getInt(3);

                Student s = new Student(id,name,age,image);
                arr.add(s);

            }while(c.moveToNext());
        }

        return arr;
    }

    public void deleteStudent(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("studentTbl","Id="+id,null);

        db.close();
    }

    public int updateStudent(Student s)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("Name",s.getName());
        cv.put("Age",s.getAge());
        cv.put("ImageId",s.getImageId());

        int res = db.update("studentTbl",cv,"Id="+s.getId(),null);
        return res ;
    }
}
