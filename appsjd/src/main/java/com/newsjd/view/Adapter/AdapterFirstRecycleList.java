package com.newsjd.view.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.network.bean.NewsBean;

import pub.cpp.news.R;

import com.newsjd.config.LoadingFooter;
import com.newsjd.database.DataUtils;
import com.utils.Base64BitmapUtil;
import com.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by sjd on 2017/7/17.
 */

public class AdapterFirstRecycleList extends RecyclerView.Adapter {
    private Context mContext;
    private List<NewsBean> mDatas;
    public FooterHolder mFooterHolder;

    public AdapterFirstRecycleList(Context context, List<NewsBean> datas) {
        mContext = context;
        mDatas = datas;
    }

    @NonNull
    @Override //创建
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        if (viewType == 1) {
            view = mInflater.inflate(R.layout.fragment_first_list_footer, parent, false);
            mFooterHolder = new FooterHolder(view);
            return mFooterHolder;
        } else {
            view = mInflater.inflate(R.layout.view_item_new, parent, false);
            return new NormalHolder(view);
        }
    }

    @SuppressLint("SetTextI18n")

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalHolder) {
            NormalHolder normalHolder = (NormalHolder) holder;
            normalHolder.tv_time.setText(mContext.getString(R.string.update_time) + Utils.dateFormat(mDatas.get(position).getTime()));
            normalHolder.tv_title.setText(mDatas.get(position).getTitle());
//        holder.tv_link.setMovementMethod(LinkMovementMethod.getInstance());
            normalHolder.tv_description.setText(mDatas.get(position).getText());

            setUpItemEvent(normalHolder);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] bitmapBytes = null;

            if (!TextUtils.isEmpty(mDatas.get(position).getImg())) {
                Bitmap bitmap = Base64BitmapUtil.base64ToBitmap(mDatas.get(position).getImg());
                if (bitmap != null) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    bitmapBytes = baos.toByteArray();
                }
            }


            if (bitmapBytes != null) {
                RequestOptions requestOptions = new RequestOptions()
                        .placeholder(R.mipmap.null_pic)
                        .error(R.mipmap.null_pic);

                Glide.with(mContext)
                        .load(bitmapBytes)
                        .apply(requestOptions)
                        .into(normalHolder.tv_img);
            }
//            else {
//                normalHolder.tv_img.setVisibility(View.GONE);
//            }
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position == mDatas.size())
            //是 最后一个则显示加载
            return 1;
        else
            return 0;
    }

    @Override
    public int getItemCount() {
        return mDatas.size() + 1;
    }


    //添加onclick 接口
    //在 onBindViewHolder 进行回调
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position, NewsBean newsBean);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    public class NormalHolder extends RecyclerView.ViewHolder {
        TextView tv_time;
        TextView tv_title;
        Button tv_link;
        TextView tv_description;
        ImageView tv_img;
        CheckBox favoritesBtn;

        NormalHolder(View v) {
            super(v);
            tv_time = v.findViewById(R.id.tv_time);
            tv_title = v.findViewById(R.id.tv_title);
            tv_link = v.findViewById(R.id.tv_link);
            tv_description = v.findViewById(R.id.tv_description);
            tv_img = v.findViewById(R.id.tv_img);
            favoritesBtn = v.findViewById(R.id.favoritesBtn);
            favoritesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox checkBox = (CheckBox) v;
                    if (checkBox.isChecked()) {
                        Toast.makeText(v.getContext(), "收藏", Toast.LENGTH_SHORT).show();
//                        DataUtils.add()
                    } else {
                        Toast.makeText(v.getContext(), "取消收藏", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public class FooterHolder extends RecyclerView.ViewHolder {
        View mLoadingViewstubstub;
        View mEndViewstub;
        View mNetworkErrorViewstub;
        View mOnClickContinueErrorViewstub;

        FooterHolder(View itemView) {
            super(itemView);
            mLoadingViewstubstub = itemView.findViewById(R.id.loading_viewstub);
            mEndViewstub = itemView.findViewById(R.id.end_viewstub);
            mNetworkErrorViewstub = itemView.findViewById(R.id.network_error_viewstub);
            mOnClickContinueErrorViewstub = itemView.findViewById(R.id.loaded_viewstub);
        }

        //根据传过来的status控制哪个状态可见
        public void setData(LoadingFooter.FooterState status) {
            Log.d("TAG", "reduAdapter" + status + "");
            switch (status) {
                case Normal:
                    setAllGone();
                    mOnClickContinueErrorViewstub.setVisibility(View.VISIBLE);
                    break;
                case Loading:
                    setAllGone();
                    mLoadingViewstubstub.setVisibility(View.VISIBLE);
                    break;
                case TheEnd:
                    setAllGone();
                    mEndViewstub.setVisibility(View.VISIBLE);
                    break;
                case NetWorkError:
                    setAllGone();
                    mNetworkErrorViewstub.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }

        }

        //全部不可见
        void setAllGone() {
            if (mLoadingViewstubstub != null) {
                mLoadingViewstubstub.setVisibility(View.GONE);
            }
            if (mEndViewstub != null) {
                mEndViewstub.setVisibility(View.GONE);
            }
            if (mNetworkErrorViewstub != null) {
                mNetworkErrorViewstub.setVisibility(View.GONE);
            }
            if (mOnClickContinueErrorViewstub != null) {
                mOnClickContinueErrorViewstub.setVisibility(View.GONE);
            }

        }

    }

    private void setUpItemEvent(final NormalHolder holder) {
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
                    int layoutPosition = holder.getLayoutPosition();//这里要注意 ， item 根据holder来获取，不是通过传输的数据
                    mOnItemClickListener.onItemLongClick(view, layoutPosition, mDatas.get(layoutPosition));
                    return true;
                }
            });
        }
    }
}
