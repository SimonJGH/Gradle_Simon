package com.yds.customize.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

@SuppressWarnings("all")
public class CommonsAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {
    protected Context context;
    protected int layoutId;
    protected List<T> mDatas;
    private View headerView;
    protected ItemDataListener itemDataListener;
    protected ItemDatasListener itemDatasListener;
    //点击事件
    private OnItemClickListener mOnItemClickListener;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 2;

    public CommonsAdapter(Context context, int layoutId, List<T> datas) {
        this.context = context;
        this.layoutId = layoutId;
        this.mDatas = datas;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setDatas(List<T> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    //添加头部布局
    public void addHeaderView(View addHeaderView) {
        if (addHeaderView == null || headerView != null) {
            return;
        }
        this.headerView = addHeaderView;
        notifyItemInserted(0);
    }

    @Override
    public int getItemCount() {
        if (headerView != null) {
            return mDatas.size() + 1;
        } else {
            return mDatas.size();
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (headerView != null) {
            if (position == 0) {
                return TYPE_HEADER;
            }
        }
        return TYPE_NORMAL;
    }

    //获取真实的position（与datalist对应，因为添加了头部，会使得position和data对应不上）
    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return headerView == null ? position : position - 1;
    }

    public void setItemDataListener(ItemDataListener listener) {
        itemDataListener = listener;
    }

    public void setItemDatasListener(ItemDatasListener listener) {
        itemDatasListener = listener;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headerView != null && viewType == TYPE_HEADER) {
            //如果是正常头
            DisplayMetrics dm = new DisplayMetrics();
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(dm);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dm.widthPixels, ViewGroup.LayoutParams.WRAP_CONTENT);
            headerView.setLayoutParams(layoutParams);
            return new CommonViewHolder(context, headerView);
        }

        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        CommonViewHolder holder = new CommonViewHolder(context, view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final CommonViewHolder holder, int position) {
        ClickEvent(holder, getRealPosition(holder));
        if (getItemViewType(position) == TYPE_HEADER) {
            //如果是头部，不做数据填充
            return;
        } else {
            if (itemDataListener != null) {
                itemDataListener.setItemData(holder, mDatas.get(getRealPosition(holder)));
            }

            if (itemDatasListener != null) {
                itemDatasListener.setItemDatas(holder, mDatas.get(getRealPosition(holder)), getRealPosition(holder));
            }

        }
    }

    protected void ClickEvent(final RecyclerView.ViewHolder holder, final int position) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.setOnItemClickListener(holder.itemView, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.setOnItemLongClickListener(holder.itemView, position);
                    return true;
                }
            });
        }
    }

    /**
     * 点击事件
     */
    public interface OnItemClickListener {

        void setOnItemClickListener(View view, int position);

        void setOnItemLongClickListener(View view, int position);
    }

    public interface ItemDataListener<T> {
        void setItemData(CommonViewHolder holder, T t);
    }

    public interface ItemDatasListener<T> {
        void setItemDatas(CommonViewHolder holder, T t, int position);
    }
}
