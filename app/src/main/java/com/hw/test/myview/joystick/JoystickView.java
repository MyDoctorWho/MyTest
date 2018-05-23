package com.hw.test.myview.joystick;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import com.hw.test.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sunhewei on 2017/2/22.
 */

public class JoystickView extends View {

    private Paint mpaint,bgPaint;

    private int Width,Height;

    private float pointx ,pointy;

    private boolean istouch = false;

    private MapControlListener mapcontrolListener;
    private Timer publish_movemap;
    private int status = -1;

    public JoystickView(Context context) {
        this(context,null);
    }

    public JoystickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mpaint = new Paint();
        mpaint.setColor(Color.parseColor("#929AF4"));
        mpaint.setAntiAlias(true);

        bgPaint = new Paint();
        bgPaint.setColor(Color.parseColor("#929AF4"));
        bgPaint.setAntiAlias(true);
        bgPaint.setDither(true);
        bgPaint.setAlpha(150);

        publish_movemap = new Timer();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        publish_movemap.schedule(new TimerTask() {
            @Override
            public void run() {
                if(status == -1) return;
//                switch (status){
//                    case 0:
//                        mapcontrolListener.mapmove_up();
//                        break;
//                    case 1:
//                        mapcontrolListener.mapmove_left();
//                        break;
//                    case 2:
//                        mapcontrolListener.mapmove_down();
//                        break;
//                    case 3:
//                        mapcontrolListener.mapmove_right();
//                        break;
//                    default:
//                        break;
//                }
            }
        },10,10);
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        publish_movemap.cancel();
        publish_movemap.purge();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int Width = measureDimens(d2p(100),widthMeasureSpec);
        int Height = measureDimens(d2p(100),heightMeasureSpec);
        if(Width>Height) this.Width=this.Height=Height;
        this.Width=this.Height=Width;

        setMeasuredDimension(this.Width,this.Height);
    }

    private int measureDimens(int defaultsize, int measureSpec){
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
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
        drawBg(canvas);

        if(istouch) drawTouch(canvas);

        draw_arrow(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                istouch = true;
                pointx = event.getX();
                pointy = event.getY();
                caculate();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                pointx = event.getX();
                pointy = event.getY();
                caculate();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                istouch = false;
                status = -1;
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }

    private void caculate(){
        if(pointx - Width/2 < 0 && Math.abs(pointy - Height/2)< Math.abs(pointx - Width/2) ){
            status = 1;
        }else if(pointx - Width/2 > 0 && Math.abs(pointy - Height/2)< Math.abs(pointx - Width/2)){
            status = 3;
        }else if(pointy - Height/2<0){
            status = 0;
        }else{
            status = 2;
        }
    }

    private void drawBg(Canvas canvas){
        canvas.drawCircle(Width/2,Height/2,Width/2,bgPaint);
    }

    private void draw_arrow(Canvas canvas){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.rightarrowcon);
        for(int i = 0;i < 4;i++){
            canvas.save();
            canvas.rotate(90*i,Width/2,Height/2);
            canvas.drawBitmap(bitmap,Width/4-bitmap.getWidth()/2,Height/2-bitmap.getHeight()/2,bgPaint);
            canvas.restore();
        }
    }

    private void drawTouch(Canvas canvas){
        float radius = Width/4;
        if((pointx-Width/2)*(pointx-Width/2)+(pointy-Height/2)*(pointy-Height/2) > Width/4*Width/4){
            radius = Width/2 - (float) Math.sqrt((pointx-Width/2)*(pointx-Width/2)+(pointy-Height/2)*(pointy-Height/2));
        }else{
            radius = (float) Math.sqrt((pointx-Width/2)*(pointx-Width/2)+(pointy-Height/2)*(pointy-Height/2)) ;
        }
        canvas.drawCircle(pointx,pointy,radius,mpaint);
    }

    private int d2p(float dp){
        float scale = getResources().getDisplayMetrics().density;
        return  (int)(scale*dp+0.5);
    }

    public void setMapcontrolListener(MapControlListener mapcontrolListener) {
        this.mapcontrolListener = mapcontrolListener;
    }

    public interface MapControlListener{
        public void mapmove_up();

        public void mapmove_down();

        public void mapmove_left();

        public void mapmove_right();
    }
}
