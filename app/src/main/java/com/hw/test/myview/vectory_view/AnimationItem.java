package com.hw.test.myview.vectory_view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by sunhewei on 2018/5/17.
 */

public class AnimationItem {

    private Bitmap bitmap;

    private Matrix matrix ;

    private float mScale = 1;

    private Paint paint ;

    private int center_x,center_y;

    private float mSpeedX,mSpeedY,mangle,mCurrentAngle;

    private int floatsize = 60;

    private float mCurrentX, mCurrentY,mCurrentX1, mCurrentY1;

    private int alpha;

    public AnimationItem(Bitmap bitmap, int center_x, int center_y){
        this.bitmap = bitmap;
        matrix = new Matrix();
        paint = new Paint();
        alpha = 255;
        this.center_x = center_x;
        this.center_y = center_y;
        init_speed();
    }

    private void init_speed(){
        Random r = new Random();
        float speed = (float) ((r.nextFloat()*0.2)+0.2);

        mCurrentAngle = mangle = r.nextInt(360) ;
        double angleInRads = Math.toRadians(mangle);
         mSpeedX= (float) (speed * Math.cos(angleInRads));
        mSpeedY = (float) (speed * Math.sin(angleInRads));
        mCurrentX = center_x;
        mCurrentY = center_y;

    }

    public void update(float current){
        mangle = mCurrentAngle+floatsize*current;
        mCurrentX = center_x/2+mSpeedX*current*1000;
        mCurrentY = center_y+mSpeedY*current*1000;
        mCurrentX1 = center_x+center_x/2+mSpeedX*current*1000;
        mCurrentY1 = center_y+mSpeedY*current*1000;
    }

    public void draw(Canvas canvas){
        matrix.reset();
        matrix.postRotate(mangle,bitmap.getWidth()/2,bitmap.getHeight()/2);
        matrix.postScale(mScale, mScale,bitmap.getWidth()/2,bitmap.getHeight()/2);
        matrix.postTranslate(mCurrentX, mCurrentY);

        canvas.drawBitmap(bitmap, matrix, paint);
        matrix.reset();
        matrix.postRotate(mangle,bitmap.getWidth()/2,bitmap.getHeight()/2);
        matrix.postScale(mScale, mScale,bitmap.getWidth()/2,bitmap.getHeight()/2);
        matrix.postTranslate(mCurrentX1, mCurrentY1);
        canvas.drawBitmap(bitmap, matrix, paint);
    }
}
