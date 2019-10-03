package com.res.cloudspot.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qmuiteam.qmui.arch.QMUILatestVisit;
import com.res.cloudspot.MainActivity;
import com.res.cloudspot.R;

public class LauncherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }


        setContentView(R.layout.activity_main);
        //要显示的字体
        LinearLayout tv_lin = findViewById(R.id.text_lin);
        //遮布
        LinearLayout tv_hide_lin = findViewById(R.id.text_hide_lin);
        //图片
        ImageView logo = findViewById(R.id.image);


        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash);
        logo.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animation = AnimationUtils.loadAnimation(LauncherActivity.this, R.anim.text_splash_position);
                tv_lin.startAnimation(animation);
                animation = AnimationUtils.loadAnimation(LauncherActivity.this, R.anim.text_canvas);
                tv_hide_lin.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = QMUILatestVisit.intentOfLatestVisit(LauncherActivity.this);
                        if (intent == null) {
                            intent = new Intent(LauncherActivity.this, MainActivity.class);
                        }
                        startActivity(intent);
                        overridePendingTransition(0, 0);

                        LauncherActivity.this.finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }
}
