package com.hw.test.myview.vectory_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by sunhewei on 2018/5/17.
 */

public class AnimationView extends View {

    Drawable drawable;
    Bitmap bitmap;
    Paint paint;

    private ArrayList<AnimationItem> animationItems;

    public AnimationView(Context context) {
        super(context);

        paint = new Paint();
    }

    public AnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(animationItems != null){
            synchronized (animationItems){
                for(AnimationItem item : animationItems){
                    item.draw(canvas);
                }
            }
        }

    }

    public void setAnimationItems(ArrayList<AnimationItem> animationItems) {
        this.animationItems = animationItems;
    }
}
