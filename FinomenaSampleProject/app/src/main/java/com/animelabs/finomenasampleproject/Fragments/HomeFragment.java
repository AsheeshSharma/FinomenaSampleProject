package com.animelabs.finomenasampleproject.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.animelabs.finomenasampleproject.Activities.QuestionPagerActivity;
import com.animelabs.finomenasampleproject.Adapters.FragmentViewPagerAdapter;
import com.animelabs.finomenasampleproject.Database.DatabaseHelper;
import com.animelabs.finomenasampleproject.Model.QuestionItemModel;
import com.animelabs.finomenasampleproject.R;
import com.animelabs.finomenasampleproject.library.CustomViewPager;

import java.util.ArrayList;

/**
 * Created by asheeshsharma on 09/04/17.
 */

public class HomeFragment extends Fragment {
    private Context context;
    private FragmentManager fragmentManager;
    private ArrayList<QuestionItemModel> mQuestionItemModelArrayList;
    //Just to show DB Operations
    private ArrayList<QuestionItemModel> mQuestionItemModelArrayListDb;
    private DatabaseHelper db;
    public HomeFragment(Context context, FragmentManager fragmentManager)
    {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(context);
        initStaticData();
        initialiseDatabaseAndProceed();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_layout,container, false);
        CustomViewPager viewPager = (CustomViewPager)view.findViewById(R.id.viewpager);
        viewPager.setPagingEnabled(false);
        Log.d("Inside","Inside");
        FragmentViewPagerAdapter fragmentViewPagerAdapter = new FragmentViewPagerAdapter(fragmentManager, mQuestionItemModelArrayListDb, viewPager, db, context);
        viewPager.setAdapter(fragmentViewPagerAdapter);
        return view;
    }

    private void initialiseDatabaseAndProceed() {
        for (QuestionItemModel questionItemModel : mQuestionItemModelArrayList){
            db.createQuestion(questionItemModel);
        }
        mQuestionItemModelArrayListDb = (ArrayList<QuestionItemModel>)db.getAllToDos();
        for (QuestionItemModel t : mQuestionItemModelArrayListDb){
            Log.d("Details are", t.toString() + " -");
        }
    }

    private void initStaticData() {
        mQuestionItemModelArrayList = new ArrayList<>();
        String answersArray[] = {"Yes", "No", "Don't Know"};
        for (int i =1 ; i < 6; i++){
            QuestionItemModel questionItemModel = new QuestionItemModel("The Question Number is " + i + " ?", answersArray,"Yes", i);
            mQuestionItemModelArrayList.add(questionItemModel);
        }
    }
}
