package com.animelabs.finomenasampleproject.Activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.FrameLayout;

import com.animelabs.finomenasampleproject.Adapters.FragmentViewPagerAdapter;
import com.animelabs.finomenasampleproject.Database.DatabaseHelper;
import com.animelabs.finomenasampleproject.Fragments.GetStartedFragment;
import com.animelabs.finomenasampleproject.Fragments.GraphAnalysisFragment;
import com.animelabs.finomenasampleproject.Fragments.HomeFragment;
import com.animelabs.finomenasampleproject.Model.QuestionItemModel;
import com.animelabs.finomenasampleproject.R;
import com.animelabs.finomenasampleproject.library.CustomViewPager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by asheeshsharma on 08/04/17.
 */

public class QuestionPagerActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_layout);
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(getIntent().getBooleanExtra(SplashActivity.ISGRAPHFRAG, false)){
            GraphAnalysisFragment getStartedFragment = new GraphAnalysisFragment();
            getStartedFragment.setContext(QuestionPagerActivity.this);
            changeFragment(getStartedFragment);
        }else {
            GetStartedFragment getStartedFragment = new GetStartedFragment();
            getStartedFragment.setContext(QuestionPagerActivity.this);
            getStartedFragment.setFragmentManager(fragmentManager);
            changeFragment(getStartedFragment);
        }

    }



    public void changeFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }
}
