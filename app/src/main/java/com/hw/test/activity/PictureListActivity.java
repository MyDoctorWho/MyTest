package com.hw.test.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hw.test.R;
import com.hw.test.myview.picture_deal.PictureAdapter;
import com.hw.test.myview.picture_deal.PictureEntity;
import java.util.ArrayList;

/**
 * Created by sunhewei on 2017/10/30.
 */

public class PictureListActivity extends Activity {

    private ListView picture_listview;

    private PictureAdapter pictureAdapter;
    private ArrayList<PictureEntity> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picturelist_main);

        initView();
        getpicList();
    }

    private void initView(){
        picture_listview = (ListView) findViewById(R.id.picture_listview);

        arrayList = new ArrayList<>();
        pictureAdapter = new PictureAdapter(this,arrayList);
        picture_listview.setAdapter(pictureAdapter);

        picture_listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState){
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        pictureAdapter.setScroll(false);

                        pictureAdapter.notifyDataSetChanged();
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        pictureAdapter.setScroll(true);
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        pictureAdapter.setScroll(true);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        picture_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PictureListActivity.this,OperatePicActivity.class);
                intent.putExtra("pic_path",arrayList.get(position).getPath());
                startActivity(intent);
            }
        });
    }

    private void getpicList(){
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
        arrayList.clear();
        while (cursor.moveToNext()){
            PictureEntity pictureEntity = new PictureEntity();
            pictureEntity.setPath(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
            arrayList.add(pictureEntity);
        }
    }
}
