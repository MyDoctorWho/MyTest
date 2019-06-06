package com.hw.test.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hw.test.R;

/**
 * Created by sunhewei on 2018/5/24.
 */

public class OperatePicActivity extends Activity implements View.OnClickListener{

    private Button bt_compressimg;

    private Button bt_operate_shader;

    private String path ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        path = getIntent().getStringExtra("pic_path");
        setContentView(R.layout.operatepic_main);

        initView();
    }

    private void initView(){
        bt_compressimg = (Button) findViewById(R.id.bt_compressimg);
        bt_operate_shader = (Button) findViewById(R.id.bt_operate_shader);

        bt_compressimg.setOnClickListener(this);
        bt_operate_shader.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_compressimg:
                Intent intent = new Intent(OperatePicActivity.this,CompressAndZoomPictureActivity.class);
                intent.putExtra("pic_path",path);
                startActivity(intent);
                break;
            case R.id.bt_operate_shader:
                Intent intent1 = new Intent(OperatePicActivity.this,PicShaderActivity.class);
                intent1.putExtra("pic_path",path);
                startActivity(intent1);
                break;
        }
    }
}
