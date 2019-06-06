package com.hw.test.myview.path_animation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hw.test.R;

public class PathViewGroup extends FrameLayout {

    private final int DEFAULT_ITEM_HEIGHT = 300;
    private final int DEFAULT_ITEM_DIVIDER = 100;

    public PathViewGroup(@NonNull Context context) {
        this(context,null);
    }

    public PathViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        addFlow();

        startAnimation();
    }

    public PathViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        for(int i = 0; i< getChildCount();i++){
            ImageView imageView = (ImageView) getChildAt(i);

            Property property = (Property) imageView.getTag();

            imageView.layout(property.left,property.top,property.right,property.bottom);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    public void addFlow(){
        for(int i = 0; i< 3;i++){
            ImageView imageView = new ImageView(getContext());
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.star_pink));
            Property property = new Property();
            if(i%2 == 0) {
                property.setIs_right(false);
            }else{
                property.setIs_right(true);
            }
            imageView.setTag(property);
            this.addView(imageView);
        }
    }

    private void startAnimation(){
        ValueAnimator mValueAnimator;
        mValueAnimator = ValueAnimator.ofFloat(0, 1,0);
        mValueAnimator.setDuration(8000);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //获取从0-1的变化值
                float drawValue = (float) animation.getAnimatedValue();
                //不断刷新绘图，实现路径绘制
                for(int i = 0;i<getChildCount();i++){
                    ImageView imageView =(ImageView) getChildAt(i);
                    Property property = (Property) imageView.getTag();
                    if(getWidth() >0){
                        if(property.is_right){
                            property.left = (int) (getWidth()-getWidth()/3*drawValue-100);
                            property.top = DEFAULT_ITEM_HEIGHT*i*2+100;
                            property.right = (int) (getWidth()-getWidth()/3*drawValue);
                            property.bottom = DEFAULT_ITEM_HEIGHT*i*2+200;
                        }else{
                            property.left = (int)(getWidth()/3*drawValue);
                            property.top = DEFAULT_ITEM_HEIGHT*i*2+100;
                            property.right = (int)(getWidth()/3*drawValue)+100;
                            property.bottom = DEFAULT_ITEM_HEIGHT*i*2+200;

                        }
                    }

                    imageView.setTag(property);
                }
                requestLayout();
            }
        });
        mValueAnimator.start();
    }

    class Property{
        boolean is_right;
        int left,top,right,bottom;

        public void setIs_right(boolean is_right) {
            this.is_right = is_right;
        }
    }
}


