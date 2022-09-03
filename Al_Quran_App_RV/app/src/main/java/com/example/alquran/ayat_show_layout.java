package com.example.alquran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        eng_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ayat_show_layout.this, "English Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        ImageView urd_t = findViewById(R.id.urdu_trans);
        urd_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ayat_show_layout.this, "Urdu Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        QuranDataInfo quranDataInfo = new QuranDataInfo();

        String surahName = quranDataInfo.urduSurahNames[index_touched];
        surah_name_head.setText(surahName);

        int ssp = quranDataInfo.SSP[index_touched];
        int start_pointer_in_array_to_fetch = ssp;

        int ayats_count = quranDataInfo.surahAyatCount[index_touched];
        int end_pointer_in_array_to_fetch = start_pointer_in_array_to_fetch + ayats_count;

        List<Arbi_Translation> ayats_translation_list = new ArrayList<>();

        for (int i = start_pointer_in_array_to_fetch; i < end_pointer_in_array_to_fetch; i++) {
            Arbi_Translation arbi_translation = new Arbi_Translation(QuranArabic.QuranArabicText[i], QuranUrdu.QuranUrduText[i]);
            ayats_translation_list.add(arbi_translation);
        }

        AyatsAdapter adapter = new AyatsAdapter(ayat_show_layout.this, ayats_translation_list, font_family);
        ayats_list.setAdapter(adapter);

    }
}