package com.hw.test.myview.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.hw.test.R;

/**
 * Created by sunhewei on 2018/5/25.
 */

public class ComposeShaderView extends View{

    private Paint mPaint;

    public ComposeShaderView(Context context) {
        this(context,null);
    }

    public ComposeShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public ComposeShaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(500,500,500,mPaint);
    }

    private void init(){
        mPaint = new Paint();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 10;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.face,options);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);

        SweepGradient sweepGradient = new SweepGradient(500,500, new int[]{Color.RED,Color.GREEN,Color.BLUE,Color.RED},null);
        RadialGradient radialGradient = new RadialGradient(500,500,500, Color.RED,Color.YELLOW, Shader.TileMode.MIRROR);

        ComposeShader composeShader = new ComposeShader(sweepGradient,bitmapShader, PorterDuff.Mode.OVERLAY);
        mPaint.setShader(composeShader);
    }
}
