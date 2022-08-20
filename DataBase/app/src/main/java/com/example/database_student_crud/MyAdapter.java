package com.example.database_student_crud;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter<Student> {

    ArrayList<Student> stds;

    public MyAdapter(@NonNull Context context, @NonNull ArrayList<Student> objects) {
        super(context, 0, objects);
        stds = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Student s = getItem(position);

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.lst_layout, parent, false);

        TextView id = convertView.findViewById(R.id.iddb);
        TextView name = convertView.findViewById(R.id.namedb);
        TextView age = convertView.findViewById(R.id.agedb);
        ImageView img = convertView.findViewById(R.id.imgdb);

        Button delete = convertView.findViewById(R.id.delete);
        Button edit = convertView.findViewById(R.id.edit);

        id.setText(String.valueOf(s.getId()));
        name.setText(s.getName());
        age.setText(String.valueOf(s.getAge()));
        img.setImageResource(s.getImageId());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View v = LayoutInflater.from(getContext()).inflate(R.layout.update_layout, null);

                TextView id_ = v.findViewById(R.id.iddb);
                EditText name_ = v.findViewById(R.id.namedb);
                EditText age_ = v.findViewById(R.id.agedb);
                ImageView img_ = v.findViewById(R.id.imgdb);

                Button cancel = v.findViewById(R.id.cancel);
                Button submit = v.findViewById(R.id.submit);

                id_.setText(id.getText());
                name_.setText(name.getText());
                age_.setText(age.getText());
                img_.setImageResource(s.getImageId());

                builder.setView(v);
                builder.setCancelable(false);
                AlertDialog alert = builder.create();
                alert.show();

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alert.dismiss();
                    }
                });

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        
                        int _id_ = Integer.parseInt(id_.getText().toString());
                        String _name_ = name_.getText().toString();
                        int _age_ = Integer.parseInt(age_.getText().toString());
                        int _img_ = s.getImageId();

                        Student newStd = new Student(_id_, _name_, _age_, _img_);

                        DbHelper db = new DbHelper(getContext());
                        int res = db.updateStudent(newStd);
                        if (res > 0) {
                            reloadData();
                            alert.dismiss();
                        } else {
                            Toast.makeText(getContext(), "Failed ! Try Again", Toast.LENGTH_SHORT).show();
                            alert.dismiss();
                        }
                    }
                });

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Confirm Deletion");
                builder.setMessage("Are you really want to delete ?");
                builder.setCancelable(false);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        DbHelper db = new DbHelper(getContext());
                        db.deleteStudent(s.getId());

                        reloadData();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        return convertView;
    }

    public void reloadData() {
        DbHelper dbHelper = new DbHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("Select * from studentTbl", null);

        if (c.moveToFirst()) {
            stds.clear();
            do {
                int id = c.getInt(0);
                String name = c.getString(1);
                int age = c.getInt(2);
                int image = c.getInt(3);

                Student s = new Student(id, name, age, image);
                stds.add(s);

            } while (c.moveToNext());

        }

        c.close();
        notifyDataSetChanged();
    }
}
