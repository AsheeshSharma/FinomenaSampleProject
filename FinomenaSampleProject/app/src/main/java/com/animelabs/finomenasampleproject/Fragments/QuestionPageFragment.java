package com.animelabs.finomenasampleproject.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.animelabs.finomenasampleproject.Activities.QuestionPagerActivity;
import com.animelabs.finomenasampleproject.Database.DatabaseHelper;
import com.animelabs.finomenasampleproject.Model.QuestionItemModel;
import com.animelabs.finomenasampleproject.Model.UserModel;
import com.animelabs.finomenasampleproject.R;
import com.animelabs.finomenasampleproject.Utility.SharedPrefUtility;
import com.animelabs.finomenasampleproject.library.CustomViewPager;
import com.animelabs.finomenasampleproject.library.RippleView;

/**
 * Created by asheeshsharma on 08/04/17.
 */

public class QuestionPageFragment extends Fragment implements View.OnClickListener {
    private QuestionItemModel questionItemModel;
    public static final String KEY = "keyObject";
    private CustomViewPager mViewPager;
    private ViewHolder viewHolder;
    private DatabaseHelper db;
    private UserModel userModel;
    private Context context;
    public QuestionPageFragment(){

    }
    public QuestionPageFragment(CustomViewPager viewPager, DatabaseHelper db, Context context){
        this.mViewPager =viewPager;
        this.db = db;
        this.context = context;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        questionItemModel = (QuestionItemModel)bundle.getSerializable(KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.swipe_item_view,container, false);
        viewHolder = new ViewHolder();
        TextView mQuestionText = (TextView)view.findViewById(R.id.textView3);
        TextView mQuestionCount = (TextView)view.findViewById(R.id.textView2);
        viewHolder.mOption1 = (Button)view.findViewById(R.id.button);
        viewHolder.mOption2 = (Button)view.findViewById(R.id.button2);
        viewHolder.mOption3 = (Button)view.findViewById(R.id.button3);
        mQuestionText.setText(questionItemModel.getmQuestion().toString());
        String textCount = Integer.toString(questionItemModel.getmQuestionID()) + "/" + 5;
        mQuestionCount.setText(textCount);
        viewHolder.mOption1.setText(questionItemModel.getmOptions()[0]);
        viewHolder.mOption2.setText(questionItemModel.getmOptions()[1]);
        viewHolder.mOption3.setText(questionItemModel.getmOptions()[2]);
        viewHolder.rippleView = (RippleView)view.findViewById(R.id.rippleView);
        viewHolder.rippleView2 = (RippleView)view.findViewById(R.id.rippleView2);
        viewHolder.rippleView3 = (RippleView)view.findViewById(R.id.rippleView3);
        viewHolder.rippleView.setOnClickListener(this);
        viewHolder.rippleView2.setOnClickListener(this);
        viewHolder.rippleView3.setOnClickListener(this);
        return view;
    }

    public static class ViewHolder{
        RippleView rippleView;
        RippleView rippleView2;
        RippleView rippleView3;
        Button mOption1;
        Button mOption2;
        Button mOption3;
    }

    private void setStatusAndProceed() {
        Handler handler = new Handler(Looper.myLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("User Response is", db.getResponse(questionItemModel.getmQuestionID()).getAnswer() + "-");
                if(questionItemModel.getmQuestionID() != 5)
                    mViewPager.setCurrentItem(questionItemModel.getmQuestionID());
                else {
                    SharedPrefUtility.addBoolean(context, true);
                    GraphAnalysisFragment graphAnalysisFragment = new GraphAnalysisFragment(context);
                    ((QuestionPagerActivity)context).changeFragment(graphAnalysisFragment);
                }
            }
        },500);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rippleView:
                userModel = new UserModel(questionItemModel.getmQuestionID(), viewHolder.mOption1.getText().toString());
                db.createUserResponse(userModel);
                setStatusAndProceed();
                break;
            case R.id.rippleView2:
                userModel = new UserModel(questionItemModel.getmQuestionID(), viewHolder.mOption2.getText().toString());
                db.createUserResponse(userModel);
                setStatusAndProceed();
                break;
            case R.id.rippleView3:
                userModel = new UserModel(questionItemModel.getmQuestionID(), viewHolder.mOption3.getText().toString());
                db.createUserResponse(userModel);
                setStatusAndProceed();
                break;
        }
    }
}
