package com.hw.test.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.hw.test.R;

import java.io.ByteArrayOutputStream;

/**
 * Created by sunhewei on 2017/11/2.
 */

public class CompressAndZoomPictureActivity extends Activity {

    private String path;

    private ImageView operate_img;

    private EditText img_quality;

    private EditText img_size;

    private Button set_quality_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        path = getIntent().getStringExtra("pic_path");
        setContentView(R.layout.compressandzoompic_main);

        initView();
    }

    private void initView(){
        operate_img = (ImageView) findViewById(R.id.operate_img);
        img_quality = (EditText) findViewById(R.id.img_quality);
        img_size = (EditText) findViewById(R.id.img_size);
        set_quality_size = (Button) findViewById(R.id.set_quality_size);

        if(path != null){
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            operate_img.setImageBitmap(bitmap);
        }

        set_quality_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quality = Integer.parseInt(img_quality.getText().toString().equals("")?"100":img_quality.getText().toString());
                int size = Integer.parseInt(img_size.getText().toString().equals("")?"1":img_size.getText().toString());
                setImage(quality,size);
            }
        });
    }


    private void setImage(int quality,int size){
        if(path != null){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;

            int outHeight = options.outHeight;
            int outWidth = options.outWidth;
            options.inJustDecodeBounds = false;
            options.inSampleSize = size;
            Bitmap bitmap = bitmap = BitmapFactory.decodeFile(path,options);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,quality,outputStream);
            Bitmap compressedBm = BitmapFactory.decodeByteArray(outputStream.toByteArray(), 0, outputStream.toByteArray().length);
            operate_img.setImageBitmap(compressedBm);
        }
    }
}
