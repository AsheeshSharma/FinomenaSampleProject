package com.animelabs.finomenasampleproject.Model;

import java.io.Serializable;

/**
 * Created by asheeshsharma on 09/04/17.
 */

public class QuestionAnalysisModel implements Serializable{
    private String mQuestion;
    private String mAnswer;
    private String mUserAnswer;

    public QuestionAnalysisModel(String mQuestion, String mAnswer, String mUserAnswer){
        this.mQuestion = mQuestion;
        this.mAnswer = mAnswer;
        this.mUserAnswer = mUserAnswer;
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public String getmAnswer() {
        return mAnswer;
    }

    public void setmAnswer(String mAnswer) {
        this.mAnswer = mAnswer;
    }

    public String getmUserAnswer() {
        return mUserAnswer;
    }

    public void setmUserAnswer(String mUserAnswer) {
        this.mUserAnswer = mUserAnswer;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    private int imgResource;
}
