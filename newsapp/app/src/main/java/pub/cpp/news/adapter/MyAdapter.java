package pub.cpp.news.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import pub.cpp.news.activity.WebActivity;
import pub.cpp.news.pojo.NewsItem;
import pub.cpp.slamwiki.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<NewsItem> mDataset;
    Context mContext;

    // Provide a reference to the type of views that you are using
    // (custom viewholder)
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_time;
        public TextView tv_title;
        public Button tv_link;
        public TextView tv_description;
        public ImageView tv_img;

        public ViewHolder(View v) {
            super(v);
            tv_time = (TextView) v.findViewById(R.id.tv_time);
            tv_title = (TextView) v.findViewById(R.id.tv_title);
            tv_link = (Button) v.findViewById(R.id.tv_link);
            tv_description = (TextView) v.findViewById(R.id.tv_description);
            tv_img = (ImageView) v.findViewById(R.id.tv_img);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<NewsItem> myDataset, Context applicationContext) {
        mDataset = myDataset;
        mContext = applicationContext;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        // set the view's size, margins, paddings and layout parametersddd
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.tv_time.setText("更新于" + dateFormat(mDataset.get(position).getTime()));
        holder.tv_title.setText(mDataset.get(position).getTitle());
//        holder.tv_link.setTag("链接:"+mDataset.get(position).getLink());
        final Intent intent = new Intent(mContext, WebActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("link", mDataset.get(position).getLink());
        holder.tv_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(intent);
            }
        });
//        holder.tv_link.setMovementMethod(LinkMovementMethod.getInstance());
        holder.tv_description.setText(mDataset.get(position).getDescription());
//        holder.tv_img.setBackgroundResource(R.mipmap.null_pic);
//        holder.tv_img.setImageURI( Uri.parse("http://"+mDataset.get(position).getImg()));
        Glide.with(mContext)
                .load("http://" + mDataset.get(position).getImg()).placeholder(R.mipmap.null_pic).error(R.mipmap.null_pic)
                .into(holder.tv_img);
//        URL url = null;
//        try {
//            url = new URL("http://"+mDataset.get(position).getImg());
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

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public String dateFormat(String dateString) {
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
            System.out.println(date1.toString());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String outputText = outputFormat.format(date1);
        System.out.println(outputText);
        return outputText;
    }

}