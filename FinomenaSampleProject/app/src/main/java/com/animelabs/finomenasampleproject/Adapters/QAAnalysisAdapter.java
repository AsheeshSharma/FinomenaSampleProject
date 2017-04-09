package com.animelabs.finomenasampleproject.Adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.animelabs.finomenasampleproject.Model.QuestionAnalysisModel;
import com.animelabs.finomenasampleproject.R;

import java.util.ArrayList;

/**
 * Created by asheeshsharma on 09/04/17.
 */

public class QAAnalysisAdapter extends RecyclerView.Adapter<QAAnalysisAdapter.MyViewHolder> {
    private ArrayList<QuestionAnalysisModel> questionAnalysisModels;
    public QAAnalysisAdapter(ArrayList<QuestionAnalysisModel> questionAnalysisModels){
        this.questionAnalysisModels = questionAnalysisModels;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_analysis_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        QuestionAnalysisModel questionAnalysisModel = questionAnalysisModels.get(position);
        holder.question.setText(questionAnalysisModel.getmQuestion());
        holder.answer.setText(questionAnalysisModel.getmAnswer());
        holder.userRes.setText(questionAnalysisModel.getmUserAnswer());
        if(questionAnalysisModel.getmAnswer().equalsIgnoreCase(questionAnalysisModel.getmUserAnswer()))
            holder.imageView.setBackgroundColor(Color.GREEN);
        else
            holder.imageView.setBackgroundColor(Color.RED);
    }

    @Override
    public int getItemCount() {
        return questionAnalysisModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView question, userRes, answer;
        private ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            question = (TextView)itemView.findViewById(R.id.textView4);
            userRes = (TextView)itemView.findViewById(R.id.textView5);
            answer = (TextView)itemView.findViewById(R.id.textView6);
            imageView = (ImageView)itemView.findViewById(R.id.imageView5);
        }
    }
}
