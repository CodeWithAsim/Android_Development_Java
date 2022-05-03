package com.example.recyclerview.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.details;
import com.example.recyclerview.model.contact;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{

    public Context context;
    public List<contact> contactList ;

    // CONSTRUCTOR

    public RecyclerViewAdapter(Context context, List<contact> contactList)
    {
        this.context = context;
        this.contactList = contactList;
    }

    // IMPLEMENTS METHODS

    // is ko arraylist pass hoi mile ab us ko show kis mai - Where to get view
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ViewHolder(v);
    }

    // Ab view holder is ko mile ga aur ye hi us mai sari cheezein populate kare ga
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position)
    {
        contact c = contactList.get(position);
        // holder ke ander d.m hain so dot laga kr access
        holder.n.setText(c.getName());
        holder.ph.setText(c.getPhone_no());

    }

    @Override
    public int getItemCount()
    {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView n ;
        TextView ph ;
        ImageView prof ;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            n = itemView.findViewById(R.id.name);
            ph = itemView.findViewById(R.id.phone);
            prof =itemView.findViewById(R.id.profile);

            prof.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            int index = this.getAdapterPosition();
            contact c = contactList.get(index);

            String name = c.getName();
            String phone = c.getPhone_no();

            Intent i = new Intent(context, details.class);
            i.putExtra("s_name",name);
            i.putExtra("s_phone",phone);

            context.startActivity(i);

            // Toast.makeText(context, "Clicked Or Touched", Toast.LENGTH_SHORT).show();

        }
    }
}
