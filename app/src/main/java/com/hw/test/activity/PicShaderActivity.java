package com.hw.test.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.hw.test.myview.picture_deal.SpecialEffectPicView;
import com.hw.test.myview.shader.BitmapShaderView;
import com.hw.test.myview.shader.ComposeShaderView;
import com.hw.test.myview.shader.LinearGradientView;
import com.hw.test.myview.shader.RadialGradientView;
import com.hw.test.myview.shader.SweepGradientView;
import com.hw.test.myview.text_deal_view.DrawCenterText;

import java.lang.reflect.Field;

/**
 * Created by sunhewei on 2018/5/24.
 */

public class PicShaderActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new SpecialEffectPicView(this));


    }

}
