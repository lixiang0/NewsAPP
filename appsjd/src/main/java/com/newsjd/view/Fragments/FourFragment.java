package com.newsjd.view.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.newsjd.base.MainApplication;
import com.newsjd.config.Contants;
import com.newsjd.view.Activity.MainActivity;
import com.newsjd.view.Activity.MainViewPager;
import com.newsjd.view.Adapter.AdapterMainVP;
import com.sjd.liblogin.MainActivity_login;
import com.utils.CheckUpdate;


import java.util.ArrayList;

import pub.cpp.news.BuildConfig;
import pub.cpp.news.R;

public class FourFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "sjd fourfrag";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_four, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 获取需要被添加控件的布局
        LinearLayout linearLayout = view.findViewById(R.id.four_lin);

        LayoutInflater inflater = LayoutInflater.from(view.getContext());

        ArrayList<ButtonParams> arrayList = new ArrayList<>();
        arrayList.add(new ButtonParams(R.id.btName_1, R.string.unlogin, R.drawable.ic_person_outline_black_24dp));
        arrayList.add(new ButtonParams(R.id.btName_2, R.string.save, R.drawable.ic_save_black_24dp));
//        arrayList.add(new ButtonParams(R.id.btName_3, R.string.reward, R.drawable.ic_attach_money_black_24dp));  //支付功能不对个人开放，暂时没接入
        arrayList.add(new ButtonParams(R.id.btName_4, R.string.checkversion, R.drawable.ic_vertical_align_bottom_black_24dp));
        arrayList.add(new ButtonParams(R.id.btName_5, R.string.aboutapp, R.drawable.ic_phone_android_black_24dp));


        for (int i = 0; i < arrayList.size(); i++) {
            ButtonParams buttonParams = arrayList.get(i);
            // 获取需要添加的布局
            @SuppressLint("InflateParams") View itemView = inflater.inflate(R.layout.view_four_item, null);//.findViewById(R.id.LinearLayout_item);
            Button itemButton = itemView.findViewById(R.id.view_four_item_button);
            itemButton.setText(getResources().getText(buttonParams.textId));
            //重定义button的id，方便onClick识别
            itemButton.setId(buttonParams.id);
            //设置button 左右图标
            Drawable drawableLeft = getResources().getDrawable(buttonParams.leftImgId);
            // 这一步必须要做,否则不会显示.
            drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());

            Drawable drawableRight = getResources().getDrawable(buttonParams.rightImgId);
            drawableRight.setBounds(0, 0, drawableRight.getMinimumWidth(), drawableRight.getMinimumHeight());

            itemButton.setCompoundDrawables(drawableLeft, null, drawableRight, null);
            itemButton.setOnClickListener(this);
            // 将布局加入到当前布局中
            linearLayout.addView(itemView);

            //加入分割线
            if (i != arrayList.size() - 1) {
                Log.e(TAG, "onViewCreated: add imageview");
                //分割线
                ImageView imageView = new ImageView(view.getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 20);
                layoutParams.setMargins(4, 4, 4, 4);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    imageView.setImageResource(R.color.dark_gray_1);//view.getContext().getColor(
                }
                imageView.setLayoutParams(layoutParams);
                linearLayout.addView(imageView);
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btName_1:
                Log.e(TAG, "onClick: button2");
                Intent intent = new Intent(getContext(), MainActivity_login.class);
                startActivityForResult(intent, 101);
                break;
            case R.id.btName_2:
                jumpToLocalPri();
                break;
            case R.id.btName_3:
                Toast.makeText(v.getContext(), "正在开发，敬请期待", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btName_4:
                CheckUpdate.checkVersion(true);
                break;
            case R.id.btName_5:
                Toast.makeText(v.getContext(), BuildConfig.VERSION_NAME + "(" + BuildConfig.VERSION_CODE + ")", Toast.LENGTH_LONG).show();
                break;
            default:
                Log.e(TAG, "onClick: " + v.getId());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 101:
                break;
            default:
        }
    }

    class ButtonParams {
        public int id;
        int textId;
        int leftImgId;
        int rightImgId = R.drawable.ic_chevron_right_black_24dp;

        ButtonParams(int id, int textId, int leftImgId) {
            this.id = id;
            this.textId = textId;
            this.leftImgId = leftImgId;
        }
    }

    void jumpToLocalPri() {
        toFragment(0);
    }

    /* 跳转到Fragment 与MAinActivity中的方法对接
     * @param i
     */
    private void toFragment(final int i) {
        final MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setFragmentSkipInterface(new MainActivity.FragmentSkipInterface() {
            @Override
            public void gotoFragment(MainViewPager viewPager, AdapterMainVP adapterMainVP) {
                viewPager.setCurrentItem(i);
                FirstFragment firstFragment = (FirstFragment) adapterMainVP.getItem(0);
                firstFragment.getViewPager().setCurrentItem(Contants.AllItem.length - 1);
            }
        });
        mainActivity.skipToFragment();
    }
}
