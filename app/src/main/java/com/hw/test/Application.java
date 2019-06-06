package com.hw.test;

import android.graphics.Typeface;

import java.lang.reflect.Field;

/**
 * Created by sunhewei on 2018/6/4.
 */

public class Application extends android.app.Application{

    @Override
    public void onCreate() {
        super.onCreate();

        setTypeface();
    }


    public void setTypeface(){
        //华文彩云，加载外部字体assets/front/huawen_caiyun.ttf
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/huawenxinwei.ttf");
        try
        {
            //与values/styles.xml中的<item name="android:typeface">sans</item>对应
//            Field field = Typeface.class.getDeclaredField("SERIF");
//            field.setAccessible(true);
//            field.set(null, typeFace);

//            Field field_1 = Typeface.class.getDeclaredField("DEFAULT");
//            field_1.setAccessible(true);
//            field_1.set(null, typeFace);

            //与monospace对应
//            Field field_2 = Typeface.class.getDeclaredField("MONOSPACE");
//            field_2.setAccessible(true);
//            field_2.set(null, typeFace);

            Field field_3 = Typeface.class.getDeclaredField("SANS_SERIF");
            field_3.setAccessible(true);
            field_3.set(null, typeFace);
        }
        catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
}
