package com.animelabs.finomenasampleproject.Model;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by asheeshsharma on 08/04/17.
 */

public class QuestionItemModel implements Serializable{
    //Question is the key needs to be unique;
    private String mQuestion;
    private int mQuestionID;
    private String[] mOptions;
    private String mAnswer;
    public static  final  String ARRAY_DIVIDER = "#a1r2ra5yd2iv1i9der";
    public String[] getmOptions() {
        return mOptions;
    }

    @Override
    public String toString() {
        return this.mAnswer + "-" + this.mQuestion + "-" + this.mQuestionID + "-" + this.getmOptions();
    }

    public QuestionItemModel(){

    }
    public void setmOptions(String[] mOptions) {
        this.mOptions = mOptions;
    }

    public QuestionItemModel(String mQuestion, String[] mOptions,  String mAnswer, int mQuestionID){
        this.mQuestion = mQuestion;
        this.mOptions = mOptions;
        this.mAnswer = mAnswer;
        this.mQuestionID = mQuestionID;
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public int getmQuestionID() {
        return mQuestionID;
    }

    public void setmQuestionID(int mQuestionID) {
        this.mQuestionID = mQuestionID;
    }



    public String getmAnswer() {
        return mAnswer;
    }

    public void setmAnswer(String mAnswer) {
        this.mAnswer = mAnswer;
    }

    public String getOptionsInString(){
        return TextUtils.join(ARRAY_DIVIDER, this.getmOptions());
    }

    public String[] getOptionsInArrat(String content){
        return content.split(ARRAY_DIVIDER);
    }
}
