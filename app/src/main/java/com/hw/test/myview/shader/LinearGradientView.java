package com.hw.test.myview.shader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sunhewei on 2018/5/25.
 */

public class LinearGradientView extends View{

    private Paint mPaint;

    public LinearGradientView(Context context) {
        this(context,null);

    }

    public LinearGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
    }

    public LinearGradientView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int color[] = new int[]{Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW};
        //pos的取值范围0-1，个数与颜色个数一致
        float pos[] = new float[]{0.2f,0.4f,0.7f,1f};

        LinearGradient linearGradient = new LinearGradient(0,0,100,100,color,pos, Shader.TileMode.MIRROR);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setShader(linearGradient);

        canvas.drawRect(0,0,1000,1000,mPaint);
    }
}
