package com.example.quizzer;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity
{

    private final List<QuestionsList> questionslist = new ArrayList<>();

    TextView CQ,TQ,Timer,Q;
    RelativeLayout RO1,RO2,RO3,RO4;
    TextView OTV1,OTV2,OTV3,OTV4;
    ImageView C1,C2,C3,C4;

    // Timer
    private CountDownTimer countDownTimer ;

    // default Q position
    private int currentQPosition = 0 ;

    // selected option
    private int selectedOption = 0 ; // 0 means no selection

    // create db reference
    DatabaseReference db_ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://quizzer-353e3-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CQ=findViewById(R.id.CQ);
        TQ=findViewById(R.id.TQ);
        Timer=findViewById(R.id.timer);
        Q=findViewById(R.id.Q);

        RO1=findViewById(R.id.RO1);
        RO2=findViewById(R.id.RO2);
        RO3=findViewById(R.id.RO3);
        RO4=findViewById(R.id.RO4);

        OTV1=findViewById(R.id.OTV1);
        OTV2=findViewById(R.id.OTV2);
        OTV3=findViewById(R.id.OTV3);
        OTV4=findViewById(R.id.OTV4);

        C1=findViewById(R.id.C1);
        C2=findViewById(R.id.C2);
        C3=findViewById(R.id.C3);
        C4=findViewById(R.id.C4);

        final AppCompatButton next_q = findViewById(R.id.next_q);

//        It should not be here because its Quiz Time
//        AdRequest adRequest = new AdRequest.Builder().build();
//
//        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest,
//                new InterstitialAdLoadCallback() {
//                    @Override
//                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
//                        interstitialAd.show(MainActivity.this);
//                    }
//
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        Toast.makeText(MainActivity.this, "Failed to load Ad", Toast.LENGTH_SHORT).show();
//                    }
//                });

        InstructionsDialog instDlg = new InstructionsDialog(MainActivity.this);
        instDlg.setCancelable(false);
        instDlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        instDlg.show();

        // get values ( q & time ) from db
        db_ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // final int time = Integer.parseInt(snapshot.child("time").getValue(String.class));
                // Ya essa ni krna tw direct db mai ja kr us ko string ke dalo
                final int time = snapshot.child("time").getValue(Integer.class);

                for(DataSnapshot questions : snapshot.child("questions").getChildren())
                {
                    String q = questions.child("question").getValue(String.class);
                    String o1 = questions.child("option1").getValue(String.class);
                    String o2 = questions.child("option2").getValue(String.class);
                    String o3 = questions.child("option3").getValue(String.class);
                    String o4 = questions.child("option4").getValue(String.class);
                    // int ans = Integer.parseInt(questions.child("answer").getValue(String.class));
                    int ans = (questions.child("answer").getValue(Integer.class));

                    QuestionsList ql = new QuestionsList(q,o1,o2,o3,o4,ans);
                    questionslist.add(ql);
                }

                //  set total Q
                TQ.setText("/ "+questionslist.size());

                // start quiz time
                startQuizTimer(time);

                // select Q
                setQuestion(currentQPosition);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Log.d("Asim", "onCancelled : Failed to get data from firebase db");
                Toast.makeText(MainActivity.this, "Failed to get data from Firebase db", Toast.LENGTH_SHORT).show();
            }
        });

        RO1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedOption = 1 ;
                selectOption(RO1,C1);
            }
        });
        RO2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedOption = 2 ;
                selectOption(RO2,C2);
            }
        });
        RO3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedOption = 3 ;
                selectOption(RO3,C3);
            }
        });
        RO4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedOption = 4 ;
                selectOption(RO4,C4);
            }
        });

        next_q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOption!=0)
                {
                    questionslist.get(currentQPosition).setTicked(selectedOption);
                    selectedOption=0;
                    currentQPosition++;
                    if(currentQPosition<questionslist.size())
                    {
                        setQuestion(currentQPosition);
                    }
                    else
                    {
                        countDownTimer.cancel();
                        finishQuiz();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please Select Option", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void finishQuiz()
    {
        Intent i = new Intent(MainActivity.this,end.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Qs",(Serializable) questionslist);

        i.putExtras(bundle);

        startActivity(i);

        finish();
    }

    public void startQuizTimer(int max_seconds)
    {
        countDownTimer = new CountDownTimer(max_seconds*1000 ,1000) {
            @Override
            public void onTick(long l) {
                long hour = TimeUnit.MILLISECONDS.toHours(l);
                long minute = TimeUnit.MILLISECONDS.toMinutes(l);
                long second = TimeUnit.MILLISECONDS.toSeconds(l);

                String generateTime = String.format(Locale.getDefault(),"%02d:%02d:%02d",
                        hour,
                        minute-TimeUnit.HOURS.toMinutes(hour),
                        second-TimeUnit.MINUTES.toSeconds(minute));

                Timer.setText(generateTime);
            }

            @Override
            public void onFinish() {
                countDownTimer.cancel();
                finishQuiz();
            }
        };

        countDownTimer.start();
    }

    public void setQuestion(int questionPosition)
    {
        // reset option
        resetOption();

        CQ.setText("Question "+(questionPosition+1)+" ");
        Q.setText(questionslist.get(questionPosition).getQ());
        OTV1.setText(questionslist.get(questionPosition).getO1());
        OTV2.setText(questionslist.get(questionPosition).getO2());
        OTV3.setText(questionslist.get(questionPosition).getO3());
        OTV4.setText(questionslist.get(questionPosition).getO4());
    }

    public void resetOption()
    {
        RO1.setBackgroundResource(R.drawable.option_bg);
        RO2.setBackgroundResource(R.drawable.option_bg);
        RO3.setBackgroundResource(R.drawable.option_bg);
        RO4.setBackgroundResource(R.drawable.option_bg);

        C1.setImageResource(R.drawable.choose_bg);
        C2.setImageResource(R.drawable.choose_bg);
        C3.setImageResource(R.drawable.choose_bg);
        C4.setImageResource(R.drawable.choose_bg);
    }

    public void selectOption(RelativeLayout RL , ImageView IV)
    {
        // user can select deselect options - not fixed - making simpler - no strictness
        resetOption();

        RL.setBackgroundResource(R.drawable.setted);
        IV.setImageResource(R.drawable.ic_baseline_check_circle_24);

    }
}