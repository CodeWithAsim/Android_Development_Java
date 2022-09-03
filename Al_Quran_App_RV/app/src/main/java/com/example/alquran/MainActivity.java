package com.example.alquran;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alquran.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends DrawerBaseActivity implements Rv_Interface {

    EditText editText;

//    ListView SurahList;

    DbHelper dbHelper;

//    MyAdapter adapter;

//    List<SurahInfo> surah_info_list;

    ActivityMainBinding activityMainBinding;


    RecyclerView rv_recyclerView;
    LinearLayoutManager rv_linearLayout;

    List<SurahInfo> rv_surah_list_info;
    RvAdapter rv_rvAdapter;

    int font_family;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        allocateActivityTitle("Home");

        Intent i = getIntent();
        font_family = i.getIntExtra("font_family", 2);

        editText = findViewById(R.id.search_txt);
        dbHelper = new DbHelper(this);

        // Copying the database

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

//        surah_info_list = new ArrayList<>();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

////                surah_info_list.clear();
////
////                surah_info_list = dbHelper.searchedSurah(String.valueOf(charSequence));
////                adapter = new MyAdapter(MainActivity.this,surah_info_list,font_family);
////                SurahList.setAdapter(adapter);

                _searched_Surah_(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // Initializing the data
//        QuranDataInfo quranDataInfo = new QuranDataInfo();
//
//        List<String> surah_english_names = quranDataInfo.getEnglishSurahNames();
//        List<String> surah_urdu_names = quranDataInfo.getUrduSurahNames();

        SurahInfo surahInfo;
        rv_surah_list_info = new ArrayList<>();


        SQLiteDatabase _db_ = dbHelper.getReadableDatabase();
        Cursor c = _db_.rawQuery("Select SurahID,SurahNameE,SurahNameU,Nazool,SurahIntro from tsurah as S join tayah as A on S.SurahID=A.SuraID AND A.AyaNo=1", null);

        if (c.moveToFirst()) {
            do {
                int bracket_index = c.getString(1).indexOf("(");
                String extract_EName = "";
                if (bracket_index != -1) {
                    extract_EName = c.getString(1).substring(0, bracket_index);
                } else {
                    extract_EName = c.getString(1);
                }
                SurahInfo s_info = new SurahInfo(c.getString(0), extract_EName, c.getString(2), c.getString(3),c.getString(4));
                rv_surah_list_info.add(s_info);
            } while (c.moveToNext());
        }

        c.close();

//        for (int index = 0; index < surah_english_names.size(); index++) {
//            surahInfo = new SurahInfo(String.valueOf(index + 1), surah_english_names.get(index), surah_urdu_names.get(index), String.valueOf(quranDataInfo.getSurahVerses(index)));
//            rv_surah_list_info.add(surahInfo);
//        }

        // Setting the recycler view
        rv_recyclerView = findViewById(R.id.surah_list);

        rv_linearLayout = new LinearLayoutManager(this);
        rv_linearLayout.setOrientation(RecyclerView.VERTICAL);

        rv_recyclerView.setLayoutManager(rv_linearLayout);

//        rv_recyclerView.setHasFixedSize(true);

//        rv_recyclerView.setLayoutManager(new GridLayoutManager(this,2));
//        rv_recyclerView.setItemAnimator(new DefaultItemAnimator());

        rv_rvAdapter = new RvAdapter(rv_surah_list_info, this, this, font_family);
        rv_recyclerView.setAdapter(rv_rvAdapter);

        rv_rvAdapter.notifyDataSetChanged();


        // Setting the animation effect on recycler view
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(rv_recyclerView.getContext(), R.anim.layout_slide_left);
        rv_recyclerView.setLayoutAnimation(layoutAnimationController);
        rv_recyclerView.getAdapter().notifyDataSetChanged();
        rv_recyclerView.scheduleLayoutAnimation();

//        SurahList = findViewById(R.id.surah_list);
//
//        QuranDataInfo quranDataInfo = new QuranDataInfo();
//
//        List<String> surah_english_names = quranDataInfo.getEnglishSurahNames();
//        List<String> surah_urdu_names = quranDataInfo.getUrduSurahNames();
//
//        // List<SurahInfo> surah_info_list= new ArrayList<>();
//        SurahInfo surahInfo;
//
//        for (int index = 0; index < surah_english_names.size(); index++) {
//            surahInfo = new SurahInfo(String.valueOf(index + 1), surah_english_names.get(index), surah_urdu_names.get(index), String.valueOf(quranDataInfo.getSurahVerses(index)));
//            surah_info_list.add(surahInfo);
//        }
//
//        adapter = new MyAdapter(MainActivity.this, surah_info_list, font_family);
//        SurahList.setAdapter(adapter);

//        SurahList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent ayat_intent = new Intent(MainActivity.this, ayat_show_layout.class);
//
//                ayat_intent.putExtra("index_touched", i);
//                ayat_intent.putExtra("index_touched",Integer.parseInt(surah_info_list.get(i).getSurahNumber())-1);
//                Log.d("asim", "onItemClick: "+(Integer.parseInt(surah_info_list.get(i).getSurahNumber())-1));
//                ayat_intent.putExtra("font_family", font_family);
//                startActivity(ayat_intent);
//            }
//        });


        // info point
        // We can also get string from resources as following method:
        // getString()

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
        Cursor c = _db_.rawQuery("Select SurahID,SurahNameE,SurahNameU,Nazool,SurahIntro from tsurah as S join tayah as A on S.SurahID=A.SuraID AND A.AyaNo=1 where SurahNameE like '%" + search + "%'", null);

        if (c.moveToFirst()) {
//            surah_info_list.clear();
            rv_surah_list_info.clear();
            do {
                int bracket_index = c.getString(1).indexOf("(");
                String extract_EName = "";
                if (bracket_index != -1) {
                    extract_EName = c.getString(1).substring(0, bracket_index);
                } else {
                    extract_EName = c.getString(1);
                }
                SurahInfo s_info = new SurahInfo(c.getString(0), extract_EName, c.getString(2), c.getString(3),c.getString(4));
//                surah_info_list.add(s_info);
                rv_surah_list_info.add(s_info);
            } while (c.moveToNext());
        }

        c.close();
//        adapter.notifyDataSetChanged();
        rv_rvAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {

//        Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show(); - For checking

        Intent ayat_intent = new Intent(MainActivity.this, ayat_show_layout.class);

        ayat_intent.putExtra("index_touched", position);
        ayat_intent.putExtra("index_touched", Integer.parseInt(rv_surah_list_info.get(position).getSurahNumber()) - 1);
        Log.d("asim", "onItemClick: " + (Integer.parseInt(rv_surah_list_info.get(position).getSurahNumber()) - 1));
        ayat_intent.putExtra("font_family", font_family);
        startActivity(ayat_intent);

    }
}