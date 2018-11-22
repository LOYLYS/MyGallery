package com.example.vhlee.mygallery;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    public static final long DELAY_WELCOME = 3000;
    private ImageView mCircle;
    private Animation mLinearAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
        startHome();
    }

    private void startHome() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        },DELAY_WELCOME);
    }

    public void init() {
        mCircle = findViewById(R.id.image_circle);
        mLinearAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.linear_dot);
        mCircle.startAnimation(mLinearAnim);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
