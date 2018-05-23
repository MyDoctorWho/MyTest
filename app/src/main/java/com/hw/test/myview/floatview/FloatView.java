package com.hw.test.myview.floatview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hw.test.R;

import java.util.ArrayList;


/**
 * Created by sunhewei on 2017/2/18.
 */

public class FloatView extends LinearLayout {

    private static String TAG = "FloatView";

    private boolean isshow = false;

    private int Width,Height;

    private Context context;

    private View target;

    private View view;

    private ListView listView;

    public FloatView(Context context) {
        this(context,null);
    }

    public FloatView(Context context, AttributeSet attrs) {
        this(context, attrs,null);
    }

    public FloatView(Context context, AttributeSet attrs, LinearLayout target){
        super(context,attrs);

        init(context,target);
        setWillNotDraw(false);
    }

    public void init(Context context, LinearLayout target){
        this.context = context;
        this.target = target;

        if (this.target != null) {
//            applyTo(this.target);
            Log.e(TAG,"=====");
        }

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        Log.e(TAG,"======"+b+"=="+i+"=="+i1+"=="+i2+"=="+i3);

//        Log.e(TAG,"=======childcount == "+getChildCount());
        if(getChildCount() >1 && getChildAt(0) instanceof ImageView){
            view = getChildAt(0);
            listView =(ListView) getChildAt(1);

            int height = view.getMeasuredHeight()/2;

////            view.layout(0,Height/2-height,view.getMeasuredWidth(),Height/2+height);
////            listView.layout(view.getMeasuredWidth(),0,Width,Height);
//            view.layout(Width-view.getMeasuredWidth(),Height/2-height,Width,Height/2+height);
//            listView.layout(Width,0,Width+Width-listview.getMeasuredWidth(),Height);
            view.layout(0,Height/2-height,view.getMeasuredWidth(),Height/2+height);
            listView.layout(view.getMeasuredWidth(),0,Width,Height);

            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isshow){
                        Log.e("======","====hide");
                        hideView();
                        ((ImageView) view).setImageResource(R.drawable.left_show);
                    }else{
                        Log.e("======","====show");
                        showView();
                        ((ImageView) view).setImageResource(R.drawable.right_hide);
                    }
                }
            });
        }

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
        layoutParams.rightMargin = -listView.getMeasuredWidth();
        setLayoutParams(layoutParams);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Width = measureDimens(d2p(100),widthMeasureSpec);
        Height = measureDimens(d2p(100),heightMeasureSpec);
        setMeasuredDimension(Width,Height);

    }

    public int measureDimens(int defaultsize,int measurespec){
        int specMode = MeasureSpec.getMode(measurespec);
        int specSize = MeasureSpec.getSize(measurespec);
        int result ;
        if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else{
            result = defaultsize;
        }
        return result;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


//    public void showView(){
//        if(isshow) return;
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this,"translationX",(Width-view.getWidth()),0);
//        objectAnimator.setDuration(300);
//        objectAnimator.start();
//    }
//
//    public void hideView(){
//        if(!isshow) return;
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this,"translationX",0,(Width-view.getWidth()));
//        objectAnimator.setDuration(300);
//        objectAnimator.start();
//    }

    public void showView(){
        if(isshow) return;
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this,"translationX",0,(-Width+view.getWidth()));
        objectAnimator.setDuration(300);
        objectAnimator.start();
        ((ImageView) view).setImageResource(R.drawable.right_hide);
        isshow = !isshow;
    }

    public void hideView(){
        if(!isshow) return;
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this,"translationX",(-Width+view.getWidth()),0);
        objectAnimator.setDuration(300);
        objectAnimator.start();
        ((ImageView) view).setImageResource(R.drawable.left_show);
        isshow = !isshow;
    }

    public int d2p(float dp){
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int)(dp*scale + 0.5);
    }

}
