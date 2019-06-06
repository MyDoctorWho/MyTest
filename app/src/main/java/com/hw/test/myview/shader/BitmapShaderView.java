package com.hw.test.myview.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.hw.test.R;

/**
 * Created by sunhewei on 2018/5/24.
 */

public class BitmapShaderView extends View{


    private Paint paint;


    public BitmapShaderView(Context context) {
        super(context);

        paint = new Paint();

        initShader();
    }

    public BitmapShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BitmapShaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setAlpha(150);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setTextSize(50);
        canvas.drawText("1,2,3,4,5,6",100,100,paint);
//        canvas.drawRect(0,0,1000,1000,paint);

    }

    private void initShader(){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 10;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.face,options);

        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        paint.setShader(bitmapShader);
    }
}
