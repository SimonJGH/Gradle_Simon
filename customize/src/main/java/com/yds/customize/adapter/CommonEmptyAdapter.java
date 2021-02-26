package com.yds.customize.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.yds.customize.R;
import com.yds.customize.util.network.NetworkUtil;

import java.util.List;

@SuppressWarnings("all")
public  class CommonEmptyAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {
    protected Context context;
    protected int layoutId;
    private int emptyResourceId = R.mipmap.no_data;
    protected List<T> mDatas;
    private View emptyView;
    protected ItemDataListener itemDataListener;
    protected ItemDatasListener itemDatasListener;
    //点击事件
    private OnItemClickListener mOnItemClickListener;
    private OnItemEmptyClickListener mOnItemEmptyClickListener;
    public static final int TYPE_EMPTY = 0;
    public static final int TYPE_NORMAL = 2;

    public CommonEmptyAdapter(Context context, int layoutId, List<T> datas) {
        this.context = context;
        this.layoutId = layoutId;
        this.mDatas = datas;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemEmptyClickListener(OnItemEmptyClickListener onItemEmptyClickListener) {
        this.mOnItemEmptyClickListener = onItemEmptyClickListener;
    }

    public void setDatas(List<T> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    public void setEmptyView(int emptyResourceId) {
        this.emptyResourceId=emptyResourceId;
        notifyDataSetChanged();
    }

    //添加空布局
    private void addEmptyView() {
        if (mDatas != null && mDatas.isEmpty()) {
            ImageView imageView = new ImageView(context);
            imageView.setPadding(50, 50, 50, 50);
            if (NetworkUtil.isNetworkAvailable()) {
                imageView.setImageResource(emptyResourceId);
            } else {
                imageView.setImageResource(R.mipmap.no_net);
            }

            // imageView.setBackgroundColor(R.color.white);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemEmptyClickListener != null) {
                        mOnItemEmptyClickListener.setOnEmptyClickListener();
                    }
                }
            });
            this.emptyView = imageView;
        } else {
            this.emptyView = null;
        }
    }

    @Override
    public int getItemCount() {
        addEmptyView();

        if (emptyView != null) {
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
        if (emptyView != null) {
            if (position == 0) {
                return TYPE_EMPTY;
            }
        }
        return TYPE_NORMAL;
    }

    //获取真实的position（与datalist对应，因为添加了空布局，会使得position和data对应不上）
    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return emptyView == null ? position : position - 1;
    }

    public void setItemDataListener(ItemDataListener listener) {
        itemDataListener = listener;
    }

    public void setItemDatasListener(ItemDatasListener listener) {
        itemDatasListener = listener;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (emptyView != null && viewType == TYPE_EMPTY) {
            DisplayMetrics dm = new DisplayMetrics();
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(dm);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dm.widthPixels, ViewGroup.LayoutParams.MATCH_PARENT);
            emptyView.setLayoutParams(layoutParams);
            return new CommonViewHolder(context, emptyView);
        }

        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        CommonViewHolder holder = new CommonViewHolder(context, view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final CommonViewHolder holder, int position) {
        ClickEvent(holder, getRealPosition(holder));
        if (getItemViewType(position) == TYPE_EMPTY) {
            //如果是空布局，不做数据填充
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
        if (mOnItemClickListener != null && position != -1) {
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
        if (mOnItemEmptyClickListener != null && position != -1) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemEmptyClickListener.setOnItemClickListener(holder.itemView, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemEmptyClickListener.setOnItemLongClickListener(holder.itemView, position);
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

    /**
     * 点击 条目/空数据 事件
     */
    public interface OnItemEmptyClickListener {

        void setOnEmptyClickListener();

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
