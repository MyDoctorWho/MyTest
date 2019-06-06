package com.hw.test.myview.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.hw.test.R;

/**
 * Created by sunhewei on 2018/5/25.
 */

public class RadialGradientView extends View{

    private Paint mPaint;

    public RadialGradientView(Context context) {
        this(context,null);
    }

    public RadialGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);

        RadialGradient radialGradient = new RadialGradient(50,50,50, Color.RED,Color.YELLOW, Shader.TileMode.MIRROR);
        mPaint = new Paint();
        mPaint.setShader(radialGradient);

    }

    public RadialGradientView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0,0,200,200,mPaint);

        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setTextSize(100);
        canvas.drawText("123456",300,300,mPaint);
    }
}
