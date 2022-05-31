package com.example.customlistview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<student>
{
    public MyAdapter(@NonNull Context context,@NonNull ArrayList<student> objects)
    {
        super(context,0 , objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        student s = getItem(position);

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.my_layout,parent,false);

        TextView name = convertView.findViewById(R.id.name);
        TextView age = convertView.findViewById(R.id.age);
        TextView title = convertView.findViewById(R.id.tag);

        ImageView image = convertView.findViewById(R.id.image_v);

        name.setText(s.getName());
        age.setText(s.getAge());
        title.setText(s.getTitle());
        image.setImageResource(s.getImageId());

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(),show_activity.class);
                i.putExtra("ImageId",s.getImageId());
                getContext().startActivity(i);
            }
        });

        return convertView;
    }
}
