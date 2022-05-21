package com.example.quizzer;

import java.io.Serializable;

public class QuestionsList implements Serializable {

    private final String q,o1,o2,o3,o4;
    private final int ans;
    private int ticked ;

    public String getQ() {
        return q;
    }

    public String getO1() {
        return o1;
    }

    public String getO2() {
        return o2;
    }

    public String getO3() {
        return o3;
    }

    public String getO4() {
        return o4;
    }

    public int getAns() {
        return ans;
    }

    public int getTicked() {
        return ticked;
    }

    public void setTicked(int ticked) {
        this.ticked = ticked;
    }

    public QuestionsList(String q, String o1, String o2, String o3, String o4, int ans) {
        this.q = q;
        this.o1 = o1;
        this.o2 = o2;
        this.o3 = o3;
        this.o4 = o4;
        this.ans = ans;
        this.ticked = 0;
    }

}
