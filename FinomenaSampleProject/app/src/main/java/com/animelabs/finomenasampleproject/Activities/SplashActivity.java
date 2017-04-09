package com.animelabs.finomenasampleproject.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.animelabs.finomenasampleproject.R;
import com.animelabs.finomenasampleproject.Utility.SharedPrefUtility;

import static java.lang.Thread.sleep;

/**
 * Created by asheeshsharma on 08/04/17.
 */

public class SplashActivity extends AppCompatActivity {
    private ImageView logoImageView;
    private Animation slide_up;
    public static final String ISGRAPHFRAG = "ISGRAPHFRAG";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activty);
        /*logoImageView = (ImageView)findViewById(R.id.imageView);
        slide_up = AnimationUtils.loadAnimation(SplashActivity.this,
                R.anim.scale_up_animation);
        logoImageView.startAnimation(slide_up);*/
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                }catch (Exception e){

                }finally {
                    Intent intent;
                    if (SharedPrefUtility.getBoolean(getApplicationContext())){
                        intent = new Intent(SplashActivity.this, QuestionPagerActivity.class);
                        intent.putExtra(ISGRAPHFRAG,true);
                    }else {
                        intent = new Intent(SplashActivity.this, QuestionPagerActivity.class);

                    }
                    startActivity(intent);
                }
            }
        });
        thread.start();
        //initView();
    }

    private void initView() {
        Handler handler = new Handler(Looper.myLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (SharedPrefUtility.getBoolean(getApplicationContext())){
                    intent = new Intent(SplashActivity.this, QuestionPagerActivity.class);
                    intent.putExtra(ISGRAPHFRAG,true);
                }else {
                    intent = new Intent(SplashActivity.this, QuestionPagerActivity.class);

                }
                startActivity(intent);
            }
        },1500);
    }
}
