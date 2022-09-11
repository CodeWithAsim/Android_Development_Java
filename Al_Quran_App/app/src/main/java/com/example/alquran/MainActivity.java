package com.example.alquran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alquran.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends DrawerBaseActivity {

    EditText editText;
    ListView SurahList;

    DbHelper dbHelper;

    MyAdapter adapter;

    List<SurahInfo> surah_info_list;

    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        allocateActivityTitle("Home");

        Intent i = getIntent();
        int font_family = i.getIntExtra("font_family", 2);

        editText = findViewById(R.id.search_txt);
        dbHelper = new DbHelper(this);

        File file = getApplicationContext().getDatabasePath(DbHelper.DB_NAME);
        if (false == file.exists()) {
            dbHelper.getReadableDatabase();
            if (CopyDataBase(this)) {
                Log.d("asim", "onCreate: Data Copied");
            } else {
                Log.d("asim", "onCreate: Error Data Copied");
                return;
            }
        }

        surah_info_list = new ArrayList<>();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                surah_info_list.clear();

//                surah_info_list = dbHelper.searchedSurah(String.valueOf(charSequence));
//                adapter = new MyAdapter(MainActivity.this,surah_info_list,font_family);
//                SurahList.setAdapter(adapter);

                _searched_Surah_(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        SurahList = findViewById(R.id.surah_list);

        QuranDataInfo quranDataInfo = new QuranDataInfo();

        List<String> surah_english_names = quranDataInfo.getEnglishSurahNames();
        List<String> surah_urdu_names = quranDataInfo.getUrduSurahNames();

        // List<SurahInfo> surah_info_list= new ArrayList<>();
        SurahInfo surahInfo;

        for (int index = 0; index < surah_english_names.size(); index++) {
            surahInfo = new SurahInfo(String.valueOf(index + 1), surah_english_names.get(index), surah_urdu_names.get(index), String.valueOf(quranDataInfo.getSurahVerses(index)));
            surah_info_list.add(surahInfo);
        }

        adapter = new MyAdapter(MainActivity.this, surah_info_list, font_family);
        SurahList.setAdapter(adapter);

        SurahList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent ayat_intent = new Intent(MainActivity.this, ayat_show_layout.class);

                ayat_intent.putExtra("index_touched", i);
                ayat_intent.putExtra("index_touched",Integer.parseInt(surah_info_list.get(i).getSurahNumber())-1);
                Log.d("asim", "onItemClick: "+(Integer.parseInt(surah_info_list.get(i).getSurahNumber())-1));
                ayat_intent.putExtra("font_family", font_family);
                startActivity(ayat_intent);
            }
        });

    }

    public boolean CopyDataBase(Context context) {
        try {
            InputStream is = context.getAssets().open(DbHelper.DB_NAME);
            String Path = DbHelper.DB_LOCATION + DbHelper.DB_NAME;
            OutputStream os = new FileOutputStream(Path);

            byte[] buffer = new byte[1024];
            int len;

            while ((len = is.read(buffer)) > 0) {
                os.write(buffer, 0, len);
            }

            os.flush();
            os.close();
            Log.d("asim", "CopyDataBase: Copied");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("asim", "CopyDataBase: Error Copied");
            return false;
        }
    }

    public void _searched_Surah_(String search) {
        SQLiteDatabase _db_ = dbHelper.getReadableDatabase();
        Cursor c = _db_.rawQuery("Select SurahID,SurahNameE,SurahNameU,Nazool from tsurah as S join tayah as A on S.SurahID=A.SuraID AND A.AyaNo=1 where SurahNameE like '%" + search + "%'", null);

        if (c.moveToFirst()) {
            surah_info_list.clear();
            do {
                SurahInfo s_info = new SurahInfo(c.getString(0), c.getString(1), c.getString(2), c.getString(3));
                surah_info_list.add(s_info);
            } while (c.moveToNext());
        }

        c.close();
        adapter.notifyDataSetChanged();
    }

}