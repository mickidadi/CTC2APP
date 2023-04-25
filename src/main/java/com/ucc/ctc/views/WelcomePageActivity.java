package com.ucc.ctc.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ucc.ctc.R;


public class WelcomePageActivity extends AppCompatActivity {

    private TextView tv;
    private ImageView logo;
    Animation atg;
    Animation atg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_welcome_page);

        tv=findViewById( R.id.tvlogo);
        logo=findViewById( R.id.ivlogo);

        atg= AnimationUtils.loadAnimation(this, R.anim.transitionstart2);
        atg2 = AnimationUtils.loadAnimation(this, R.anim.transitionstart);

        tv.startAnimation(atg);
        logo.startAnimation(atg2);

        final Intent i = new Intent(this,LoginFacilityActivity.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(i);
                finish();
            }
        },5000);
    }
}
