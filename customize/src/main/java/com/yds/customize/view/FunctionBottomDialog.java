package com.yds.customize.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yds.customize.R;
import com.yds.customize.adapter.CommonViewHolder;
import com.yds.customize.adapter.CommonsAdapter;
import com.yds.customize.entity.BottomDialogBean;

import java.util.List;

/**
 * @author YDS
 * @date 2020/12/4
 * @discribe 底部对话框
 */
@SuppressWarnings("all")
public class FunctionBottomDialog extends Dialog {

    private List<BottomDialogBean> mList;
    private RecyclerView recyclerview_bottom;
    private OnItemClickLintener itemClickListener;

    public FunctionBottomDialog(@NonNull Context context, @NonNull List<BottomDialogBean> list) {
        this(context, R.style.normal_dialog, list);
    }

    public FunctionBottomDialog(@NonNull Context context, int themeResId, @NonNull List<BottomDialogBean> list) {
        super(context, themeResId);
        this.mList = list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_custom_dialog_function_bottom, null);

        recyclerview_bottom = inflate.findViewById(R.id.recyclerview_bottom);
        initRecyclerview();

        Window window = getWindow();
        window.getDecorView().setPadding(0, 20, 0, 0); //消除边距
        window.setWindowAnimations(R.style.AnimBottom);
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setContentView(inflate);
    }

    private void initRecyclerview() {
        GridLayoutManager glm = new GridLayoutManager(getContext(),4);
        recyclerview_bottom.setLayoutManager(glm);
        // 如果Item够简单，高度是确定的，打开FixSize将提高性能
        recyclerview_bottom.setHasFixedSize(true);
        // 设置Item默认动画，加也行，不加也行
        recyclerview_bottom.setItemAnimator(new DefaultItemAnimator());
        recyclerview_bottom.setNestedScrollingEnabled(false);

        CommonsAdapter mCommonEmptyAdapter = new CommonsAdapter(getContext(), R.layout.layout_custom_dialog_bottom_item, mList);
        mCommonEmptyAdapter.setItemDataListener(new CommonsAdapter.ItemDataListener<BottomDialogBean>() {
            @Override
            public void setItemData(CommonViewHolder holder, BottomDialogBean dataBean) {
                ImageView iv_dialog_bottom_icon = holder.getView(R.id.iv_dialog_bottom_icon);
                TextView tv_dialog_bottom_msg = holder.getView(R.id.tv_dialog_bottom_msg);

                iv_dialog_bottom_icon.setImageResource(dataBean.getIconId());
                tv_dialog_bottom_msg.setText(dataBean.getIconMsg());
            }
        });

        mCommonEmptyAdapter.setOnItemClickListener(new CommonsAdapter.OnItemClickListener() {

            @Override
            public void setOnItemClickListener(View view, int position) {
                itemClickListener.onItemClick(position);
                dismiss();
            }

            @Override
            public void setOnItemLongClickListener(View view, int position) {
            }
        });

        mCommonEmptyAdapter.setHasStableIds(true);
        recyclerview_bottom.setAdapter(mCommonEmptyAdapter);
    }

    //设置选项点击回调
    public void setOnItemClickListener(OnItemClickLintener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    //选项点击回调
    public interface OnItemClickLintener {

        //item点击
        void onItemClick(int position);
    }

}
