package com.animelabs.finomenasampleproject.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.animelabs.finomenasampleproject.Activities.QuestionPagerActivity;
import com.animelabs.finomenasampleproject.R;

/**
 * Created by asheeshsharma on 09/04/17.
 */

public class GetStartedFragment extends Fragment {
    private Context context;
    private FragmentManager fm;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public GetStartedFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.login_layout, container, false);
        final EditText editText = (EditText)view.findViewById(R.id.editText);
        Button getStartedButton = (Button)view.findViewById(R.id.button4);
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(editText.getText().toString().trim()) && editText.getText().toString().trim() != null && editText.getText().toString().trim() != ""){
                    HomeFragment homeFragment = new HomeFragment(context, fm);
                    ((QuestionPagerActivity)context).changeFragment(homeFragment);
                }else {
                    Snackbar snackbar = Snackbar.make(view, "Enter a valid value", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });
        return view;
    }

    public GetStartedFragment(Context context, FragmentManager fm){
        this.context = context;
        this.fm = fm;
    }
}
