package com.hw.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.hw.test.myview.path_animation.PathView;
import com.hw.test.myview.path_animation.PathViewGroup;
import com.hw.test.myview.path_animation.ViewParent;

public class PathDemoActivity extends AppCompatActivity {

    PathView view;

    PathViewGroup viewGroup ;

    ViewParent viewParent ;

    Toast toast;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        view = new PathView(this);
//        viewGroup = new PathViewGroup(this);
        viewParent = new ViewParent(this);
        setContentView(viewParent);
        for(int i = 0; i < 6; i++){
            viewParent.addItem(i,"item"+i);
        }

        toast = Toast.makeText(PathDemoActivity.this,"",Toast.LENGTH_SHORT);
        viewParent.setItemViewClick(new ViewParent.ItemViewClick() {
            @Override
            public void showToast(String word) {
//                toast.cancel();
//                toast.setText(word);
//                toast.show();
                Toast.makeText(PathDemoActivity.this,word,Toast.LENGTH_SHORT).show();
                Log.e("=====","========show toast");
            }
        });
    }

}
