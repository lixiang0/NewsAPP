package com.newsjd.view.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.utils.HttpUtils;

import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import pub.cpp.news.R;

public class ThirdFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "sjd thirdfrag";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.button2);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                Log.e(TAG, "onClick: button2");
                HttpUtils.login("tes", "test").flatMapSingle(new Function<String, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(String s) throws Exception {
                        Log.e(TAG, "call: " + s);
                        return null;
                    }
                });
                break;
            default:
                Log.e(TAG, "onClick: " + v.getId());
        }
    }
}
