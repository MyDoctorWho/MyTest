package com.hw.test.myview.vectory_view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.hw.test.myview.vectory_view.AnimationItem;
import com.hw.test.myview.vectory_view.AnimationView;

import java.util.ArrayList;


/**
 * Created by sunhewei on 2018/5/17.
 */

public class AnimationFactory {

    private int COUNT_NUMBER= 100;

    private ViewGroup viewGroup;

    private int parentlocation[];

    private ArrayList<AnimationItem> animationItems;

    private AnimationView animationView;

    private int repeatcounts  = 0;

    public AnimationFactory(Activity activity, int drawableId, int repetcounts){
        repeatcounts = repetcounts;
        Drawable drawable = activity.getResources().getDrawable(drawableId);
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
//        Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), drawableId);
        viewGroup = (ViewGroup) activity.findViewById(android.R.id.content);
        animationView = new AnimationView(viewGroup.getContext());
        viewGroup.addView(animationView);
        parentlocation = new int[2];

        viewGroup.getLocationInWindow(parentlocation);

        animationItems = new ArrayList<>();
        for(int i = 0;i<COUNT_NUMBER;i++){
            AnimationItem animationItem = new AnimationItem(bitmap,viewGroup.getWidth()/2,viewGroup.getHeight()/2);
            animationItems.add(animationItem);
        }

        animationView.setAnimationItems(animationItems);

    }


    public void startAnimaror(){
        ValueAnimator animator = ValueAnimator.ofFloat(0,1);
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(repeatcounts);
        animator.start();

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float current_percent = (float)animation.getAnimatedValue();
                for(int i = 0;i<animationItems.size();i++){
                    animationItems.get(i).update(current_percent);
                }

                animationView.postInvalidate();
            }
        });

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                viewGroup.removeView(animationView);
                viewGroup.postInvalidate();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
