package com.hw.test.myview.picture_deal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.hw.test.R;

/**
 * Created by sunhewei on 2018/6/4.
 */

public class SpecialEffectPicView extends View{

    private Paint mPaint;

    private Paint srcPaint,dstPaint;

    public SpecialEffectPicView(Context context) {
        this(context,null);
    }

    public SpecialEffectPicView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        initView();
    }

    public SpecialEffectPicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.face);

        RectF rect = new RectF(0,0,500,500);
        int saveFlags = Canvas.CLIP_TO_LAYER_SAVE_FLAG;
        canvas.saveLayer(0, 0, 500, 500, null, saveFlags);
        canvas.drawRoundRect(rect,50,50,mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        canvas.drawBitmap(bitmap, null,new RectF(100,100,500,500), mPaint);
        mPaint.setXfermode(null);
        canvas.restore();


        canvas.drawCircle(700,200,100,dstPaint);
        srcPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
        canvas.drawRect(500,0,700,200,srcPaint);


//        canvas.drawBitmapMesh(bitmap,1,1,new float[]{0,0,bitmap.getWidth(),0,0,bitmap.getHeight(),bitmap.getWidth(),bitmap.getHeight()},0,null,0,null);

    }


    private void initView(){
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setFilterBitmap(true);


        srcPaint = new Paint();
        srcPaint.setColor(Color.YELLOW);

        dstPaint = new Paint();
        dstPaint.setColor(Color.BLUE);

    }
}
