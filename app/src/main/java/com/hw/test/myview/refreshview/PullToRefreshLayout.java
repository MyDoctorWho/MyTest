package com.hw.test.myview.refreshview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by sunhewei on 2017/1/17.
 */

public class PullToRefreshLayout extends RelativeLayout {

    private static String TAG = "PullToRefreshLayout";

    private float move_distance;
    private float downY,lastY;
    //刷新距离·
    private float refresh_distance = dp2px(60);

    private View mTarget;

    private ImageView mRefreshView;

    private boolean mRefreshing;
    private boolean mIsBeingDragged;

    private int mCurrentOffsetTop;

    private Drawable mRefreshDrawable;

    private boolean IsBeingDragged;
    private float last_y;
    private int mTouchSlop;

    public PullToRefreshLayout(Context context) {
        this(context,null);
    }

    public PullToRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        mRefreshView = new ImageView(context);
        mRefreshView.setVisibility(GONE);
        addView(mRefreshView,0);

        setWillNotDraw(false);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }


    private void ensureTarget() {
        if (mTarget != null)
            return;
        if (getChildCount() > 0) {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                if (child != mRefreshView)
                    mTarget = child;
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        ensureTarget();
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth()-getPaddingLeft()-getPaddingRight(), MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight()-getPaddingTop()-getPaddingBottom(), MeasureSpec.EXACTLY);
        mTarget.measure(widthMeasureSpec,heightMeasureSpec);
        mRefreshView.measure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        ensureTarget();
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        int left = getPaddingLeft();
        int right = getPaddingRight();
        int top = getPaddingTop();
        int bottom = getPaddingBottom();

        mTarget.layout(left,top+mTarget.getTop(),left+width-right,top+mTarget.getTop()+height-bottom);
        mRefreshView.layout(left,top,left+width-right,top+height-bottom);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(!isEnabled() || (canChildScrollUp() && mRefreshing)){
            return false;
        }

        final int action = ev.getActionMasked();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                if(!mRefreshing){
                    setTargetOffsetTop(0,true);
                }
                IsBeingDragged = false;
                last_y = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float ydiff = ev.getY()-last_y;
                if (mRefreshing) {
                    mIsBeingDragged = !(ydiff < 0 && mCurrentOffsetTop <= 0);
                } else if (ydiff > mTouchSlop && !mIsBeingDragged) {
                    mIsBeingDragged = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                mIsBeingDragged = false;
                break;

        }
        return mIsBeingDragged;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!mIsBeingDragged)
        return super.onTouchEvent(event);
        final int action = event.getActionMasked();

        switch (action){
            case MotionEvent.ACTION_MOVE:
                final float ydiff = event.getY()-last_y;

                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;

        }
        return false;
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getContext().getResources().getDisplayMetrics());
    }

    private boolean canChildScrollUp(){
        if(Build.VERSION.SDK_INT < 14){
            if(mTarget instanceof AbsListView){
                final AbsListView absListView = (AbsListView) mTarget;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return mTarget.getScrollY() > 0;
            }
        }else{
            return ViewCompat.canScrollVertically(mTarget,-1);
        }
    }

    private void setTargetOffsetTop(int offset, boolean requiresUpdate){
        mTarget.offsetTopAndBottom(offset);
        mCurrentOffsetTop = mTarget.getTop();
        if(requiresUpdate && Build.VERSION.SDK_INT <11){
            invalidate();
        }
    }
}
