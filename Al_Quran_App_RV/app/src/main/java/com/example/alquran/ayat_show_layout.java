package com.example.alquran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

public class ayat_show_layout extends AppCompatActivity {

    ListView ayats_list;


    List<Arbi_Translation> ayats_translation_list;
    int start_pointer_in_array_to_fetch ;
    int end_pointer_in_array_to_fetch ;

    AyatsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayat_show_layout);

        Intent ayat_intent = getIntent();
        int index_touched = ayat_intent.getIntExtra("index_touched", 0);
        int font_family = ayat_intent.getIntExtra("font_family", 2);

        ayats_list = findViewById(R.id.ayat_with_translation);

        TextView surah_name_head = findViewById(R.id.surah_head_name);

        FrameLayout frameLayout = findViewById(R.id.sheet);

        BottomSheetBehavior<FrameLayout> bottomSheetBehavior = BottomSheetBehavior.from(frameLayout);
        bottomSheetBehavior.setPeekHeight(130);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        ImageView eng_t = findViewById(R.id.english_trans);


        ArrayList<String> english_trans = new ArrayList<>();
        ArrayList<String> urdu_trans = new ArrayList<>();

        eng_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ayat_show_layout.this, "English Clicked", Toast.LENGTH_SHORT).show();

                DbHelper dbHelper = new DbHelper(ayat_show_layout.this);
                SQLiteDatabase _db_ = dbHelper.getReadableDatabase();
                Cursor c = _db_.rawQuery("Select MehmoodulHassan ,DrMohsinKhan from tayah", null);

                if (c.moveToFirst()) {
                    do {
                        urdu_trans.add(c.getString(0));
                        english_trans.add(c.getString(1));
                    } while (c.moveToNext());
                }

                c.close();

                ayats_translation_list.clear();

                for (int i = start_pointer_in_array_to_fetch; i < end_pointer_in_array_to_fetch; i++) {
                    Arbi_Translation arbi_translation = new Arbi_Translation(QuranArabic.QuranArabicText[i], english_trans.get(i));
                    ayats_translation_list.add(arbi_translation);
                }
                adapter.notifyDataSetChanged();
            }
        });

        ImageView urd_t = findViewById(R.id.urdu_trans);
        urd_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ayat_show_layout.this, "Urdu Clicked", Toast.LENGTH_SHORT).show();

                ayats_translation_list.clear();

                for (int i = start_pointer_in_array_to_fetch; i < end_pointer_in_array_to_fetch; i++) {
                    Arbi_Translation arbi_translation = new Arbi_Translation(QuranArabic.QuranArabicText[i], urdu_trans.get(i));
                    ayats_translation_list.add(arbi_translation);
                }
                adapter.notifyDataSetChanged();
            }
        });

        QuranDataInfo quranDataInfo = new QuranDataInfo();

        String surahName = quranDataInfo.urduSurahNames[index_touched];
        surah_name_head.setText(surahName);

        int ssp = quranDataInfo.SSP[index_touched];
        start_pointer_in_array_to_fetch = ssp;

        int ayats_count = quranDataInfo.surahAyatCount[index_touched];
        end_pointer_in_array_to_fetch = start_pointer_in_array_to_fetch + ayats_count;

        ayats_translation_list = new ArrayList<>();

        for (int i = start_pointer_in_array_to_fetch; i < end_pointer_in_array_to_fetch; i++) {
            Arbi_Translation arbi_translation = new Arbi_Translation(QuranArabic.QuranArabicText[i], QuranUrdu.QuranUrduText[i]);
            ayats_translation_list.add(arbi_translation);
        }

        adapter = new AyatsAdapter(ayat_show_layout.this, ayats_translation_list, font_family);
        ayats_list.setAdapter(adapter);

    }
}