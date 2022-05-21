package com.example.quizzer;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

public class InstructionsDialog extends Dialog {

    private int instructionPoints = 0;

    public InstructionsDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions_dialog_layout);

        final AppCompatButton cont = findViewById(R.id.cnt);
        final TextView inst_TV = findViewById(R.id.ITV);

        setInstructionPoints(inst_TV,"1.  You have 2 minutes to solve the quiz");
        setInstructionPoints(inst_TV,"2.  Each correct answer mark 2 points");

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }

    private void setInstructionPoints(TextView inst_tv, String intructionPoint)
    {
//        if(instructionPoints==0)
//        {
//            inst_tv.setText(intructionPoint);
//        }
//        else
//        {
            inst_tv.setText(inst_tv.getText()+"\n"+intructionPoint);
//        }
    }

}
