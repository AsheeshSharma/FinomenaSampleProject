package com.animelabs.finomenasampleproject.Model;

import java.io.Serializable;

/**
 * Created by asheeshsharma on 09/04/17.
 */

public class UserModel implements Serializable {
    public UserModel(int mQuestionID, String answer){
        this.mQuestionID = mQuestionID;
        this.answer = answer;
    }
    public UserModel(){

    }
    public int getmQuestionID() {
        return mQuestionID;
    }

    public void setmQuestionID(int mQuestionID) {
        this.mQuestionID = mQuestionID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    private int mQuestionID;
    private String answer;
}
