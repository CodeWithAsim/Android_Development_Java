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

public class MyAdapter extends ArrayAdapter<SurahInfo> {

    int font_family;

    public MyAdapter(@NonNull Context context, @NonNull List<SurahInfo> objects, int _font_family) {
        super(context, 0, objects);
        font_family = _font_family;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        SurahInfo surahInfo = getItem(position);

        view = LayoutInflater.from(getContext()).inflate(R.layout.surah_data_show_layout, parent, false);

        TextView Surah_number;
        TextView SurahEnglishName;
        TextView SurahUrduName;
        TextView AyatCount;

        Typeface tf;

        if (font_family == 0) {
            tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/noorehuda.ttf");
        } else if (font_family == 1) {
            tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/jameel_noori_nastaleeq.ttf");
        } else {
            tf = Typeface.DEFAULT;
        }

        Surah_number = view.findViewById(R.id.surah_number);
        SurahEnglishName = view.findViewById(R.id.english_name);
        SurahUrduName = view.findViewById(R.id.urdu_name);
        AyatCount = view.findViewById(R.id.ayat_count);

        Surah_number.setTypeface(tf);
        SurahEnglishName.setTypeface(tf);
        SurahUrduName.setTypeface(tf);
        AyatCount.setTypeface(tf);

        Surah_number.setText(surahInfo.getSurahNumber());
        SurahEnglishName.setText(surahInfo.getSurahEnglishName());
        SurahUrduName.setText(surahInfo.getSurahUrduName());
        AyatCount.setText("Ayat " + surahInfo.getAyatCount());

        return view;
    }
}
