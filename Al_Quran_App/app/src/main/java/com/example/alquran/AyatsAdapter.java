package com.example.alquran;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AyatsAdapter extends ArrayAdapter<Arbi_Translation> {

    int font_family;

    public AyatsAdapter(@NonNull Context context, @NonNull List<Arbi_Translation> objects,int _font_family) {
        super(context, 0, objects);
        font_family = _font_family;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        Arbi_Translation arbi_translation = getItem(position);

        view = LayoutInflater.from(getContext()).inflate(R.layout.ayats_show_custom_layout,parent,false);

        TextView arbiText;
        TextView udruText;

        Typeface tf;

        if (font_family == 0) {
            tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/noorehuda.ttf");
        } else if (font_family == 1) {
            tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/jameel_noori_nastaleeq.ttf");
        } else {
            tf = Typeface.DEFAULT;
        }

        arbiText = view.findViewById(R.id.arbi);
        udruText = view.findViewById(R.id.urdu);

        arbiText.setTypeface(tf);
        udruText.setTypeface(tf);

        arbiText.setText(arbi_translation.getArbi());
        udruText.setText(arbi_translation.getTranslation());

        return view;
    }
}
