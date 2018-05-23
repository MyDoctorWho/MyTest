package com.hw.test.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.hw.test.R;
import com.hw.test.myview.vectory_view.AnimationFactory;

public class CenterWinAndFlowerActivity extends AppCompatActivity {

    private TextView tv_count_time,tv_result,tv_round;

    private Button bt_round,bt_count,bt_result;

    private Button bt_firework;

    private int location[];

    private int count = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment1);
        initView();
    }

    private void initView(){
        location = new int[2];

        tv_round = (TextView) findViewById(R.id.tv_round);
        tv_count_time = (TextView) findViewById(R.id.tv_count_time);
        tv_result = (TextView) findViewById(R.id.tv_result);

        bt_round = (Button) findViewById(R.id.bt_round);
        bt_round.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimationRound();
            }
        });

        bt_count = (Button) findViewById(R.id.bt_count);
        bt_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimationCount();
            }
        });

        bt_result = (Button) findViewById(R.id.bt_result);
        bt_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimationResult();
            }
        });

        bt_firework = (Button) findViewById(R.id.bt_firework);
        bt_firework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimationFire();
            }
        });
    }


    private void startAnimationRound(){
        tv_round.getLocationOnScreen(location);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tv_round,"translationY",-location[1],0);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(tv_round,"scaleX",0,1.5f);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(tv_round,"scaleY",0,1.5f);
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(tv_round,"scaleX",1.5f,1);
        ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(tv_round,"scaleY",1.5f,1);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimator).with(objectAnimator1).with(objectAnimator2).before(objectAnimator3).before(objectAnimator4);
        animatorSet.setDuration(500);
        animatorSet.start();

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                roundend();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void startAnimationCount(){
        WindowManager wm = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        int height = dm.heightPixels;       // 屏幕高度（像素）

        tv_count_time.setY(0);
        tv_count_time.setScaleX(1);
        tv_count_time.setScaleY(1);
        tv_count_time.getLocationOnScreen(location);
        tv_count_time.setText((count--)+"");
        tv_count_time.setScaleX(3);
        tv_count_time.setScaleY(3);
        tv_count_time.setY(height/2-location[1]-tv_count_time.getHeight()/2);
        tv_count_time.setVisibility(View.VISIBLE);
        final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tv_count_time,"alpha",0,1,0.5f,0).setDuration(1000);
        objectAnimator.setRepeatCount(3);
        objectAnimator.start();
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                count = 3;
                tv_count_time.setVisibility(View.INVISIBLE);
                countend();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                if(count >= 0) {
                    tv_count_time.setText((count--)+"");
                }
            }
        });
    }


    private void startAnimationResult(){

        WindowManager wm = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        int height = dm.heightPixels;       // 屏幕高度（像素）

        tv_result.setY(0);
        tv_result.setScaleY(1);
        tv_result.setScaleX(1);
        tv_result.getLocationOnScreen(location);
        tv_result.setText("YOU WIN ！");
        tv_result.setY(height/2-location[1]-tv_result.getHeight()/2);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tv_result,"ScaleX",1,3,5,4);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(tv_result,"ScaleY",1,3,5,4);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimator).with(objectAnimator1);
        animatorSet.setDuration(1000);
        animatorSet.start();

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                tv_result.setY(0);

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    private void startAnimationFire(){
        AnimationFactory animationFactory = new AnimationFactory(this,R.drawable.star_pink,2);
        animationFactory.startAnimaror();
    }

    private void countend(){

    }

    private void roundend(){

    }
}
