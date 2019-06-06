package com.hw.test.myview.text_deal_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by sunhewei on 2018/5/31.
 */

public class DrawCenterText extends View{

    private Paint mPain;

    private String msg = "jdwisuenkjjsmsim今天开不开心";

    public DrawCenterText(Context context) {
        this(context,null);
    }

    public DrawCenterText(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public DrawCenterText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        mPain = new Paint();
        mPain.setTextSize(80);
        mPain.setStrokeCap(Paint.Cap.ROUND);
        mPain.setColor(Color.RED);

        mPain.setDither(true);
        mPain.setFlags(Paint.ANTI_ALIAS_FLAG);   //消除锯齿

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint.FontMetrics fontMetrics = mPain.getFontMetrics();
        float bottom = fontMetrics.bottom;
        float top = fontMetrics.top;
        float ascent = fontMetrics.ascent;
        float descent = fontMetrics.descent;
        float leading = fontMetrics.leading;

        canvas.drawLine(100,100+top,1000,100+top,mPain);
        canvas.drawLine(100,100+leading,1000,100+leading,mPain);
        canvas.drawLine(100,100+ascent,1000,100+ascent,mPain);
        canvas.drawLine(100,100+descent,1000,100+descent,mPain);
        canvas.drawLine(100,100+bottom,1000,100+bottom,mPain);

        canvas.drawLine(100,100+(descent+ascent)/2,1000,100+(descent+ascent)/2,mPain);
        canvas.drawText(msg,100,100,mPain);

        canvas.drawLine(100,300,1000,300,mPain);
        canvas.drawLine(100,500,1000,500,mPain);

        canvas.drawLine(100,400-(descent+ascent)/2,1000,400-(descent+ascent)/2,mPain);

        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(),"fonts/huawenxinwei.ttf");

//        mPain.setTypeface(typeface);
        canvas.drawText(msg,100,400-(descent+ascent)/2,mPain);
    }


}
