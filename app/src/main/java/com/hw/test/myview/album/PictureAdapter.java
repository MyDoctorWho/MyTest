package com.hw.test.myview.album;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.hw.test.R;

import java.util.ArrayList;

/**
 * Created by sunhewei on 2017/10/30.
 */

public class PictureAdapter extends BaseAdapter {

    private ArrayList<PictureEntity> list ;
    private Context context;
    private LayoutInflater inflater;

    private boolean scroll = false;

    public void setScroll(boolean scroll) {
        this.scroll = scroll;
    }

    public PictureAdapter(Context context, ArrayList<PictureEntity> list){

        if(list == null){
            this.list = new ArrayList<PictureEntity>();
        }else {
            this.list = list;
        }
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.picture_list_item,null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.pic_item_imv);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(!scroll){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            Bitmap bitmap = BitmapFactory.decodeFile(list.get(position).getPath(),options);

            int outHeight = options.outHeight;
            int outWidth = options.outWidth;
            options.inJustDecodeBounds = false;
            options.inSampleSize = 10;
            bitmap = BitmapFactory.decodeFile(list.get(position).getPath(),options);
            viewHolder.imageView.setImageBitmap(bitmap);
        }else{
            viewHolder.imageView.setImageResource(R.drawable.default_bg);
        }

        return convertView;
    }


    class ViewHolder{
        ImageView imageView;
    }
}
