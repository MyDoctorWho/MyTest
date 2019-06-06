package com.hw.test.myview.path_animation;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hw.test.R;

public class ItemView extends LinearLayout {

    private ImageView imageView;

    private TextView textView;

    public ItemView(Context context) {
        this(context,null);
    }

    public ItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = inflate(getContext(), R.layout.item_main, this);
        setOrientation(VERTICAL);

        imageView = (ImageView) view.findViewById(R.id.item_img);
        textView = (TextView) view.findViewById(R.id.item_tv);
    }

    public void initItem(String title){
        textView.setText(title);
    }
}
