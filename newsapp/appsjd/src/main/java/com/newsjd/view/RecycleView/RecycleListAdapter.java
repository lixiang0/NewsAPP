package com.newsjd.view.RecycleView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.newsjd.R;
import com.newsjd.data.NewsData;
import com.newsjd.view.webview.WebActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by sjd on 2017/7/17.
 */

public class RecycleListAdapter extends RecyclerView.Adapter<RecycleListAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<NewsData> mDatas;

    public RecycleListAdapter(Context context, List<NewsData> datas) {
        mContext = context;
        mDatas = datas;
        mInflater = LayoutInflater.from(context);
    }

    @Override //创建
    public RecycleListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_item_new, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.tv_time.setText("更新于" + dateFormat(mDatas.get(position).getTime()));
        holder.tv_title.setText(mDatas.get(position).getTitle());
//        holder.tv_link.setTag("链接:"+mDatas.get(position).getLink());
        final Intent intent = new Intent(mContext, WebActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("link", mDatas.get(position).getLink());
        holder.tv_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(intent);
            }
        });
//        holder.tv_link.setMovementMethod(LinkMovementMethod.getInstance());
        holder.tv_description.setText(mDatas.get(position).getText());
//        holder.tv_img.setBackgroundResource(R.mipmap.null_pic);
//        holder.tv_img.setImageURI( Uri.parse("http://"+mDatas.get(position).getImg()));
        Glide.with(mContext)
                .load("http://" + mDatas.get(position).getImg()).placeholder(R.mipmap.null_pic).error(R.mipmap.null_pic)
                .into(holder.tv_img);
//        URL url = null;
//        try {
//            url = new URL("http://"+mDatas.get(position).getImg());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        Bitmap bmp = null;
//        try {
//            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        holder.tv_img.setImageBitmap(bmp);

    }

    protected void setUpItemEvent(final ViewHolder holder) {
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


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_time;
        public TextView tv_title;
        public Button tv_link;
        public TextView tv_description;
        public ImageView tv_img;

        public ViewHolder(View v) {
            super(v);
            tv_time = v.findViewById(R.id.tv_time);
            tv_title = v.findViewById(R.id.tv_title);
            tv_link = v.findViewById(R.id.tv_link);
            tv_description = v.findViewById(R.id.tv_description);
            tv_img = v.findViewById(R.id.tv_img);
        }
    }


    public static String dateFormat(String dateString) {
        String inputText = dateString;
        SimpleDateFormat inputFormat = new SimpleDateFormat
                ("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.ENGLISH);
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        SimpleDateFormat outputFormat =
                new SimpleDateFormat("MM月dd日HH时mm分");
        // Adjust locale and zone appropriately
        Date date1 = null;
        try {
            date1 = inputFormat.parse(inputText);
//            System.out.println(date1.toString());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String outputText = outputFormat.format(date1);
//        System.out.println(outputText);
        return outputText;
    }

}
