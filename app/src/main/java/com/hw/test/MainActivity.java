package com.hw.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hw.test.activity.CenterWinAndFlowerActivity;
import com.hw.test.activity.FloatViewActivity;

/**
 * Created by sunhewei on 2018/5/23.
 */

public class MainActivity extends Activity implements View.OnClickListener{

    private Button bt_vectory_show;

    private Button bt_float;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        bt_vectory_show = (Button) findViewById(R.id.bt_vectory_show);
        bt_float = (Button) findViewById(R.id.bt_float);

        bt_vectory_show.setOnClickListener(this);
        bt_float.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_vectory_show:
                startActivity(new Intent(MainActivity.this, CenterWinAndFlowerActivity.class));
                break;
            case R.id.bt_float:
                startActivity(new Intent(MainActivity.this, FloatViewActivity.class));
                break;
            default:
                break;
        }
    }
}
