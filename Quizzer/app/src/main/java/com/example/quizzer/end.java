package com.example.quizzer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;
import java.util.List;

public class end extends AppCompatActivity {

    // private final List<QuestionsList> questionsLists = new ArrayList<>();
    private List<QuestionsList> questionsLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        final TextView ach_score = findViewById(R.id.ach_score);
        final TextView tot_score = findViewById(R.id.tot_score);
        final TextView correct = findViewById(R.id.correct);
        final TextView incorrect = findViewById(R.id.incorrect);
        final AppCompatButton share = findViewById(R.id.share_btn);
        final AppCompatButton restart = findViewById(R.id.restart_btn);

        // getting questions list from the Main Activity
        questionsLists = (List<QuestionsList>) getIntent().getSerializableExtra("Qs");

        tot_score.setText(" / "+(questionsLists.size()*2));
        ach_score.setText((getCorrectAnswers()*2)+"");

        correct.setText(getCorrectAnswers()+"");
        incorrect.setText(String.valueOf(questionsLists.size()-getCorrectAnswers()));

        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        interstitialAd.show(end.this);
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Toast.makeText(end.this, "Failed to load Ad", Toast.LENGTH_SHORT).show();
                    }
                });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(end.this,MainActivity.class));

                finish();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_SEND);
                i.setType("text/*");
                i.putExtra(Intent.EXTRA_TEXT,"My Score = "+ach_score.getText());
                //Intent share_via = Intent.createChooser(i,"Choose an app to share with");
                startActivity(i);

                // Intent sendIntent = new Intent(Intent.ACTION_SEND);
                // Always use string resources for UI text.
                // This says something like "Share this photo with"
                // String title = getResources().getString(R.string.chooser_title);
                // Create intent to show the chooser dialog
                // Intent chooser = Intent.createChooser(sendIntent, title);
                // Verify the original intent will resolve to at least one activity
                // if (sendIntent.resolveActivity(getPackageManager()) != null) {
                // startActivity(chooser); }
            }
        });

    }

    public int getCorrectAnswers()
    {
        int correctAnswers = 0 ;
        for(int i=0;i<questionsLists.size();i++)
        {
            int answerOfQ = questionsLists.get(i).getAns();
            int selectedAnswer = questionsLists.get(i).getTicked();
            if(answerOfQ==selectedAnswer)
            {
                correctAnswers++;
            }
        }
        return correctAnswers;
    }

}