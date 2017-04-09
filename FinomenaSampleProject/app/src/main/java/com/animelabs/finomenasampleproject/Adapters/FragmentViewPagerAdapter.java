package com.animelabs.finomenasampleproject.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.animelabs.finomenasampleproject.Database.DatabaseHelper;
import com.animelabs.finomenasampleproject.Fragments.QuestionPageFragment;
import com.animelabs.finomenasampleproject.Model.QuestionItemModel;
import com.animelabs.finomenasampleproject.library.CustomViewPager;

import java.util.ArrayList;

/**
 * Created by asheeshsharma on 08/04/17.
 */

public class FragmentViewPagerAdapter extends FragmentPagerAdapter{

    final int PAGE_COUNT = 5;
    private ArrayList<QuestionItemModel> questionItemModel;
    private CustomViewPager mCustomViewPager;
    private DatabaseHelper db;
    private Context context;
    public FragmentViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public FragmentViewPagerAdapter(FragmentManager fm, ArrayList<QuestionItemModel> questionItemModel, CustomViewPager viewPager, DatabaseHelper db, Context context){
        super(fm);
        this.questionItemModel =questionItemModel;
        this.mCustomViewPager = viewPager;
        this.db = db;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        QuestionPageFragment questionPageFragment = new QuestionPageFragment();
        questionPageFragment.setContext(context);
        questionPageFragment.setViewPager(mCustomViewPager);
        questionPageFragment.setDBHelper(db);
        Bundle data = new Bundle();
        data.putSerializable(QuestionPageFragment.KEY, questionItemModel.get(position));
        questionPageFragment.setArguments(data);
        return questionPageFragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
