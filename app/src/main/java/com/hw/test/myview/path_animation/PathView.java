package com.hw.test.myview.path_animation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import java.util.ArrayList;

public class PathView extends View {

    private final int DEFAULT_ITEM_HEIGHT = 300;
    private final int DEFAULT_ITEM_DIVIDER = 100;

    private Paint mPaint;

    private Paint hasPaint;

    private Path path;

    private ArrayList<Point> points = new ArrayList<>();

    private int current_index = 0;

    int prex = 0,prey = 0;

    private boolean default_right = true;

    private float drawValue = 0;

    ValueAnimator mValueAnimator ;

    private Path drawPath;
    private Path animationPath;
    private Path hadPath;

    PathMeasure pathMeasure;
    PathMeasure animaPathMeasure;

    float length;
    float animaLength;

    boolean initPath = false;
    boolean isRefreshd = false;

    public PathView(Context context) {
        this(context,null);

    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();

//        initPath();
//        startAnimate();
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initPaint(){
        mPaint = new Paint();
        mPaint.setColor(Color.GRAY);           // 画笔颜色 - 黑色
        mPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAlpha(100);
        mPaint.setStrokeWidth(100);

        hasPaint = new Paint();
        hasPaint.setColor(Color.BLACK);           // 画笔颜色 - 黑色
        hasPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
        hasPaint.setAlpha(100);
        hasPaint.setStrokeWidth(100);

    }

    public void initPath(){
        path = new Path();
//        points = new ArrayList<Point>();
        points.clear();
        points.add(new Point(getWidth()/2,100));
        points.add(new Point(getWidth()/2+200,400));
        points.add(new Point(getWidth()/2,700));
        points.add(new Point(getWidth()/2-200,1000));
        points.add(new Point(getWidth()/2,1300));
        points.add(new Point(getWidth()/2+200,1600));

        drawPath = new Path();
        animationPath = new Path();
        hadPath = new Path();

        for(int i = 0 ; i < points.size() ; i++){
            Point point = points.get(i);
            if(path.isEmpty()){
                path.moveTo(point.x,point.y);
                prex = point.x;
                prey = point.y;
            }else{
                if(default_right) {
                    path.quadTo(point.x,prey,point.x,point.y);
                }else{
                    path.quadTo(prex,point.y,point.x,point.y);
                }
                default_right = !default_right;
                prex = point.x;
                prey = point.y;
            }
        }

        pathMeasure = new PathMeasure();
        pathMeasure.setPath(path,false);
        length = pathMeasure.getLength();

        initPath = true;
    }

    private void refreshPath(){
        drawPath.reset();
        if(current_index > 0) {
            pathMeasure.getSegment(0, length*(current_index-1) /(points.size()-1), hadPath, true);
            pathMeasure.getSegment(length*(current_index-1)/(points.size()-1),length*current_index /(points.size()-1), drawPath,true);
        }else{
        }

        animaPathMeasure = new PathMeasure();
        animaPathMeasure.setPath(drawPath,false);
        animaLength = animaPathMeasure.getLength();

        isRefreshd= true;
        requestLayout();
    }

    public void startAnimate(){

        mValueAnimator = ValueAnimator.ofFloat(0, 1);
        mValueAnimator.setDuration(2000);
        mValueAnimator.setRepeatCount(0);
        mValueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //获取从0-1的变化值
                drawValue = (float) animation.getAnimatedValue();
                refreshAnimationPath();
                //不断刷新绘图，实现路径绘制
                postInvalidate();
            }
        });
        mValueAnimator.start();
    }

    private void refreshAnimationPath(){
        if(initPath){
            animationPath.reset();
            animaPathMeasure.getSegment(0,animaLength*drawValue,animationPath,true);
        }
    }

    public int getCurrent_index() {
        return current_index;
    }

    public void setCurrent_index(int index){
        this.current_index = index;
        isRefreshd = false;
        startAnimate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int height = 0;
        if(points.size() > 1){
            height = DEFAULT_ITEM_HEIGHT*points.size()+DEFAULT_ITEM_DIVIDER*2;
        }else{
            height = DEFAULT_ITEM_HEIGHT;
        }

        setMeasuredDimension(widthSize,height);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if(!initPath){
            initPath();
        }
        if(!isRefreshd){
            refreshPath();
        }
        canvas.drawPath(path,mPaint);
        canvas.drawPath(hadPath,hasPaint);

        canvas.drawPath(animationPath,hasPaint);
    }
}
