package com.hw.test.myview.shader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sunhewei on 2018/5/25.
 */

public class SweepGradientView extends View{

    private Paint mPaint;

    public SweepGradientView(Context context) {
        this(context,null);
    }

    public SweepGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();

        SweepGradient sweepGradient = new SweepGradient(500,500, new int[]{Color.RED,Color.GREEN,Color.BLUE,Color.RED},null);
        mPaint.setShader(sweepGradient);

    }

    public SweepGradientView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(500,500,500,mPaint);
    }
}
