package com.hw.test.myview.path_animation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class ViewParent extends RelativeLayout {

    PathView pathView;
    PathViewGroup pathViewGroup;

    public ItemViewClick itemViewClick;

    public ViewParent(Context context) {
        this(context,null);
    }

    public ViewParent(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadPath();
    }

    public ViewParent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void loadPath(){

        pathViewGroup = new PathViewGroup(getContext());
        addView(pathViewGroup,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        pathView = new PathView(getContext());
        RelativeLayout.LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(pathView,layoutParams);

    }

    public void addItem(final int index, String title){
        final ItemView itemView = new ItemView(getContext());
        itemView.initItem(title);
        itemView.setTag(index);
        RelativeLayout.LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = 300*index+50;
        layoutParams.leftMargin = 100;
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        addView(itemView,layoutParams);
        itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if((pathView.getCurrent_index() +1) ==  index){
                    pathView.setCurrent_index(index);
                }else if(pathView.getCurrent_index() >= index ){
                    if(itemViewClick != null){
                        itemViewClick.showToast("已完成该单元");
                    }
                }else if(pathView.getCurrent_index() < index-1){
                    if(itemViewClick != null){
                        itemViewClick.showToast("该单元还未开放");
                    }
                }
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        for(int i = 0; i < getChildCount(); i++){
            if(getChildAt(i) instanceof ItemView){
                ItemView itemView =(ItemView) getChildAt(i);
                int index = (int)itemView.getTag();
                int left = 0,top = 0,right = 0,bottom = 0;
                switch (index%4){
                    case 0:
                        left = getWidth()/2-itemView.getWidth()/2;
                        right = getWidth()/2+itemView.getWidth()/2;
                        break;
                    case 1:
                        left = getWidth()/2-itemView.getWidth()/2+200;
                        right = getWidth()/2+itemView.getWidth()/2+200;
                        break;
                    case 2:
                        left = getWidth()/2-itemView.getWidth()/2;
                        right = getWidth()/2+itemView.getWidth()/2;
                        break;
                    case 3:
                        left = getWidth()/2-itemView.getWidth()/2-200;
                        right = getWidth()/2+itemView.getWidth()/2-200;
                        break;
                }
                top = index*300+50;
                bottom = index*300+50+itemView.getHeight();
                itemView.layout(left,top,right,bottom);
            }

        }
    }

    public interface ItemViewClick{
        public void showToast(String word);
    }

    public void setItemViewClick(ItemViewClick itemViewClick) {
        this.itemViewClick = itemViewClick;
    }
}
