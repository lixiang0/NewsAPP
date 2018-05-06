package com.mylibs.view.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mylibs.R;

import java.util.List;

/**
 * Created by sjd on 2017/7/17.
 */

public class RecycleListAdapter extends RecyclerView.Adapter<RecycleListAdapter.MyViewHolder> {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mDatas;

    public RecycleListAdapter(Context context, List<String> datas) {
        mContext = context;
        mDatas = datas;
        mInflater = LayoutInflater.from(context);
    }

    @Override //创建
    public RecycleListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycle_list_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(mDatas.get(position));
        setUpItemEvent(holder);
    }

    protected void setUpItemEvent(final MyViewHolder holder) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int layoutPosition = holder.getLayoutPosition();//这里要注意 ， item 根据holder来获取，不是通过传输的数据
                    mOnItemClickListener.onItemClick(view, layoutPosition);

                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(view, layoutPosition);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addData(int pos) {
        mDatas.add(pos, "Insert One");
        notifyItemInserted(pos); //不会刷新所有的view，不会重置他们的position
//        notifyDataSetChanged();   不要写成这个
    }

    public void deleteData(int pos) {
        mDatas.remove(pos);
        notifyItemRemoved(pos);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.single_textView);
        }
    }

    //添加onclick 接口
    //在 onBindViewHolder 进行回调
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
