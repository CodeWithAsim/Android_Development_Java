package com.example.database_student_crud;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter<Student> {

    ArrayList<Student> stds ;

    public MyAdapter(@NonNull Context context, @NonNull ArrayList<Student> objects) {
        super(context, 0, objects);
        stds = objects ;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Student s = getItem(position);

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.lst_layout,parent,false);

        TextView id  =convertView.findViewById(R.id.iddb);
        TextView name= convertView.findViewById(R.id.namedb);
        TextView age = convertView.findViewById(R.id.agedb);
        ImageView img = convertView.findViewById(R.id.imgdb);

        Button delete = convertView.findViewById(R.id.delete);
        Button edit = convertView.findViewById(R.id.edit);

        id.setText(String.valueOf(s.getId()));
        name.setText(s.getName());
        age.setText(String.valueOf(s.getAge()));
        img.setImageResource(s.getImageId());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper db = new DbHelper(getContext());
                db.deleteStudent(s.getId());

                reloadData();
            }
        });

        return convertView;
    }

    public void reloadData()
    {
        DbHelper dbHelper = new DbHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("Select * from studentTbl",null);

        if(c.moveToFirst())
        {
            stds.clear();
            do
            {
                int id = c.getInt(0);
                String name= c.getString(1);
                int age = c.getInt(2);
                int image = c.getInt(3);

                Student s = new Student(id,name,age,image);
                stds.add(s);

            }while(c.moveToNext());

        }

        c.close();
        notifyDataSetChanged();
    }
}
