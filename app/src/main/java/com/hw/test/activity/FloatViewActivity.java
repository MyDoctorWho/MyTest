package com.hw.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.hw.test.R;
import com.hw.test.myview.floatview.FloatAdapter;
import com.hw.test.myview.floatview.FloatView;

import java.util.ArrayList;

public class FloatViewActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_float);

        final FloatView floatView = (FloatView) findViewById(R.id.floatview);
        final ListView listView = (ListView) findViewById(R.id.listview);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("1111");
        strings.add("2222");
        strings.add("3333");
        strings.add("4444");
        strings.add("5555");
        strings.add("6666");

        FloatAdapter floatAdapter = new FloatAdapter(this,strings);
        listView.setAdapter(floatAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e(TAG,"===========listview的item点击事件");
            }
        });
        findViewById(R.id.activity_main).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return listView.dispatchTouchEvent(motionEvent);
            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int widthPixels= dm.widthPixels;
        int heightPixels= dm.heightPixels;
        float density = dm.density;
        float denstidp = dm.densityDpi;
        int screenWidth = (int) (widthPixels /density);
        int screenHeight = (int)(heightPixels /density) ;
        TextView textView = (TextView) findViewById(R.id.tv_main);
        textView.setText(widthPixels+"===="+heightPixels+"==="+screenWidth+"==="+screenHeight+"=="+density+"==="+denstidp);
        TextView textView1 = (TextView) findViewById(R.id.tv_test);
    }
}
