package com.hw.test.myview.floatview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hw.test.R;

import java.util.ArrayList;

/**
 * Created by sunhewei on 2017/2/21.
 */

public class FloatAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> list;
    private LayoutInflater layoutInflater;

    public FloatAdapter(Context context, ArrayList<String> list){
        this.context = context;
        if(list == null){
            this.list = new ArrayList<>();
        }else {
            this.list = list;
        }
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.float_item,null);
            viewHolder.t1 =(TextView) view.findViewById(R.id.mark_position);
            viewHolder.t2 = (TextView) view.findViewById(R.id.mark_content);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.t2.setText(list.get(i));
        return view;
    }

    class ViewHolder{
        TextView t1;
        TextView t2;
    }
}
