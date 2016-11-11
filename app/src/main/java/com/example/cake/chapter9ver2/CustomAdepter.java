package com.example.cake.chapter9ver2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by CAKE on 11/11/2016.
 */

public class CustomAdepter extends BaseAdapter {

    Context mContext;
    String[] Topic;
    String[] Date;
    int[] resId;

    public CustomAdepter(Context context, String [] Topic, int[] resID, String [] Date) {
        this.mContext = context;
        this.Topic = Topic;
        this.resId = resID;
        this.Date = Date;
    }
    @Override
    public int getCount() {
        return Topic.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater =(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder myHolder = null;

        if (convertView == null) {
            //คือช่วงแรกๆที่ convertView ยังไม่มีค่า

            //เตรียม Layout ที่ต้องการ ให้กับ convertView
            convertView = mInflater.inflate(R.layout.activity_menu_list, null);

            myHolder = new ViewHolder();
            myHolder.tvTopic = (TextView)convertView.findViewById(R.id.tvTopic);
            myHolder.imgDetail = (ImageView) convertView.findViewById(R.id.imgDetail);
            myHolder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            convertView.setTag(myHolder);
        }else {
            //คือช่วงที่ convertView ผ่านการ Scroll มาแล้ว
            myHolder = (ViewHolder) convertView.getTag();
        }

        // ต้อง set ค่าที่จะให้แสดงผลกับแต่ละ widget
        myHolder.tvTopic.setText(Topic[position]);
        myHolder.imgDetail.setImageResource(resId[position]);
        myHolder.tvDate.setText(Date[position]);
        return convertView;
    }

    public class ViewHolder{
        //ประกาษว่าภายใน view จะมี widget อะไร ชื่ออะไรบ้าง
        TextView tvTopic;
        ImageView imgDetail;
        TextView tvDate;
    }
}