package com.example.alquran;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyVH> {

    List<SurahInfo> surah_info_list;
    int last_position = -1;
    Context context;

    private final Rv_Interface rv_interface;

    int font_family;

    public RvAdapter(List<SurahInfo> surah_info_list, Context _context, Rv_Interface rv_interface,int font_family) {
        this.surah_info_list = surah_info_list;
        this.context = _context;
        this.rv_interface = rv_interface;
        this.font_family = font_family;
    }

    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_layout, parent, false);
        return new MyVH(view,rv_interface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVH holder, int position) {
        SurahInfo surahInfo = surah_info_list.get(position);

        String _SurahNumber = surahInfo.getSurahNumber();
        String _SurahEnglishName = surahInfo.getSurahEnglishName();
        String _SurahUrduName = surahInfo.getSurahUrduName();
        String _AyatCount = surahInfo.getAyatCount();
        String _SurahInto = surahInfo.getSurahIntro();

//        holder.surah_into.setText(_SurahInto);

        boolean isVisible = surahInfo.isVisiblity();
        holder.constraintLayout.setVisibility(isVisible?View.VISIBLE:View.GONE);

        if(holder.getAdapterPosition()>last_position)
        {
            Animation animation = AnimationUtils.loadAnimation(context,R.anim.slide_in_onscroll);
            holder.itemView.startAnimation(animation); // Hum us mai khali pic pe bhi laga skte hai

            // instead of that can write the default animations
            // Animation animation = AnimationUtils.loadAnimation(holder.itemView.getcontext(),android.R.anim.slide_in_left);
            // holder.itemView.startAnimation(animation);
        }

        holder.setData(_SurahNumber, _SurahEnglishName, _SurahUrduName, _AyatCount,_SurahInto);

        last_position = holder.getAdapterPosition();

    }

    @Override
    public int getItemCount() {
        return surah_info_list.size();
    }

    public class MyVH extends RecyclerView.ViewHolder {

        TextView Surah_number_;
        TextView SurahEnglishName_;
        TextView SurahUrduName_;
        TextView AyatCount_;

        TextView view_more_;

        ConstraintLayout constraintLayout;

        TextView surah_into ;

        public MyVH(@NonNull View itemView,Rv_Interface rv_interface) {
            super(itemView);

            Surah_number_ = itemView.findViewById(R.id.surah_number);
            SurahEnglishName_ = itemView.findViewById(R.id.english_name);
            SurahUrduName_ = itemView.findViewById(R.id.urdu_name);
            AyatCount_ = itemView.findViewById(R.id.ayat_count);

            view_more_ = itemView.findViewById(R.id.view_more);

            constraintLayout = itemView.findViewById(R.id.expandableLayout);

            surah_into=itemView.findViewById(R.id.surahInto);

            Typeface tf;

            if (font_family == 0) {
                tf = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/noorehuda.ttf");
            } else if (font_family == 1) {
                tf = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/jameel_noori_nastaleeq.ttf");
            } else {
                tf = Typeface.DEFAULT;
            }

            Surah_number_.setTypeface(tf);
            SurahEnglishName_.setTypeface(tf);
            SurahUrduName_.setTypeface(tf);
            AyatCount_.setTypeface(tf);
            view_more_.setTypeface(tf);
            surah_into.setTypeface(tf);

            view_more_.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view_more_.setTextColor(Color.GRAY);
                    SurahInfo _surahInfo_ = surah_info_list.get(getAdapterPosition());
                    _surahInfo_.setVisiblity(!_surahInfo_.isVisiblity());

                    notifyItemChanged(getAdapterPosition());  // is  ke bad bound view wale method ki taraf flow jata hai

                    // also there is notify item inserted and notify item removed () methods
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(rv_interface!=null)
                    {
                        int pos_ = getAdapterPosition();
                        if(pos_ != RecyclerView.NO_POSITION)
                        {
                            rv_interface.onItemClick(pos_);
                        }
                    }
                }
            });
        }

        public void setData(String surahNumber, String surahEnglishName, String surahUrduName, String ayatCount,String surahIntro) {

            Surah_number_.setText(surahNumber);
            SurahEnglishName_.setText(surahEnglishName);
            SurahUrduName_.setText(surahUrduName);
            AyatCount_.setText("Ayat " + ayatCount);
            surah_into.setText(surahIntro);

        }
    }
}
