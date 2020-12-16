package com.yds.customize.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

@SuppressWarnings("all")
public class CommonViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    //view的集合 
    private View mConvertView;
    //item的布局 
    private Context mContext;

    //上下文 
    public CommonViewHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<View>();
    }

    //获取item的布局 
    public View getItemView() {
        return mConvertView;
    }

    //初始化控件，通过传进去id来初始化，使用泛型实现传递任何类型 
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    //快捷设置TextView的文本 
    public CommonViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    //快捷设置ImageView的图片
    public CommonViewHolder setSrc(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    //设置控件的点击事件 
    public CommonViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    //设置item的点击事件
    public CommonViewHolder setItemOnClickListener(View.OnClickListener listener) {
        mConvertView.setOnClickListener(listener);
        return this;
    }
}