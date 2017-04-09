package com.animelabs.finomenasampleproject.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.animelabs.finomenasampleproject.Adapters.QAAnalysisAdapter;
import com.animelabs.finomenasampleproject.Model.QuestionAnalysisModel;
import com.animelabs.finomenasampleproject.R;

import java.util.ArrayList;

/**
 * Created by asheeshsharma on 09/04/17.
 */

public class QuestionAnalysisFragment extends Fragment {
    private Context context;
    private QAAnalysisAdapter analysisAdapter;
    private ArrayList<QuestionAnalysisModel> questionAnalysisModel;
    public static final String KEY_NAME = "updated_mode";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_list_layout,container, false);
        Bundle bundle = getArguments();
        questionAnalysisModel =(ArrayList<QuestionAnalysisModel>) bundle.getSerializable(KEY_NAME);
        if(questionAnalysisModel != null)
            analysisAdapter = new QAAnalysisAdapter(questionAnalysisModel);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(analysisAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public QuestionAnalysisFragment(){

    }

    public void setContext(Context context){
        this.context = context;
    }
}
