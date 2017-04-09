package com.animelabs.finomenasampleproject.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.animelabs.finomenasampleproject.Activities.QuestionPagerActivity;
import com.animelabs.finomenasampleproject.Database.DatabaseHelper;
import com.animelabs.finomenasampleproject.Model.QuestionAnalysisModel;
import com.animelabs.finomenasampleproject.Model.QuestionItemModel;
import com.animelabs.finomenasampleproject.Model.UserModel;
import com.animelabs.finomenasampleproject.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by asheeshsharma on 09/04/17.
 */

public class GraphAnalysisFragment extends Fragment {
    private ArrayList<QuestionItemModel> questionItemModels;
    private ArrayList<UserModel> userModels;
    private ArrayList<QuestionAnalysisModel> questionAnalysisModels;
    private DatabaseHelper db;
    private BarDataSet barDataSet1, barDataSet2;
    private Button analysisButton;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.graph_view_layout,container, false);


        final BarChart chart = (BarChart) view.findViewById(R.id.chart);
        analysisButton = (Button)view.findViewById(R.id.analyseQuestion);

        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(getXAxisValues()));
        BarData data = new BarData(getDataSet());
        chart.setData(data);
        Description description = new Description();
        description.setText("Answer Analysis");
        chart.setDescription(description);
        data.setBarWidth(0.3f);
        chart.animateXY(200, 200);
        chart.invalidate();


        analysisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionAnalysisFragment questionAnalysisFragment = new QuestionAnalysisFragment();
                questionAnalysisFragment.setContext(context);
                Bundle bundle = new Bundle();
                bundle.putSerializable(QuestionAnalysisFragment.KEY_NAME, questionAnalysisModels);
                questionAnalysisFragment.setArguments(bundle);
                ((QuestionPagerActivity)context).changeFragment(questionAnalysisFragment);
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(getActivity().getApplicationContext());
        questionAnalysisModels = new ArrayList<>();
    }
    public void setContext(Context context){
        this.context = context;
    }
    private ArrayList<IBarDataSet> getDataSet() {
        ArrayList<IBarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        questionItemModels = (ArrayList)db.getAllToDos();
        userModels = (ArrayList<UserModel>)db.getAllResponses();

        int totalCorrect = 0;
        int totalInCorrect = 0;

        for(int i = 0; i < questionItemModels.size(); i++){
            String answer = questionItemModels.get(i).getmAnswer();
            String userAnswer = db.getResponse(questionItemModels.get(i).getmQuestionID()).getAnswer();
            QuestionAnalysisModel questionAnalysisModel = new QuestionAnalysisModel(questionItemModels.get(i).getmQuestion(), answer, userAnswer);
            questionAnalysisModels.add(questionAnalysisModel);
            if (answer.equalsIgnoreCase(userAnswer)){
                totalCorrect = totalCorrect + 1;
            }else
                totalInCorrect = totalInCorrect + 1;

        }

        Log.d("Coorecncorrect are", totalCorrect + "-" + totalInCorrect);

        BarEntry v1e1 = new BarEntry(0, totalCorrect); // Jan
        valueSet1.add(v1e1);

        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(1, totalInCorrect); // Jan
        valueSet2.add(v2e1);

        barDataSet1 = new BarDataSet(valueSet1, "Correct");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        barDataSet2 = new BarDataSet(valueSet2, "Incorrect");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;
    }


    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("");
        xAxis.add("");
        return xAxis;
    }
}
