package com.newsjd.view.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.sjd.liblogin.MainActivity_login;
import com.utils.CheckUpdate;


import pub.cpp.news.R;

public class FourFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "sjd fourfrag";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_four, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button login = view.findViewById(R.id.four_login);
        Button pay = view.findViewById(R.id.four_pay);
        Button check = view.findViewById(R.id.four_check);
        Button aboutapp = view.findViewById(R.id.four_aboutapp);
        login.setOnClickListener(this);
        pay.setOnClickListener(this);
        check.setOnClickListener(this);
        aboutapp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.four_login:
                Log.e(TAG, "onClick: button2");
                Intent intent = new Intent(getContext(), MainActivity_login.class);
                startActivityForResult(intent, 101);
                break;
            case R.id.four_pay:
                Toast.makeText(v.getContext(), "正在开发，敬请期待", Toast.LENGTH_LONG).show();
                break;
            case R.id.four_check:
                CheckUpdate.checkVersion(this.getContext());
                break;
            case R.id.four_aboutapp:
                Toast.makeText(v.getContext(), "正在开发，敬请期待", Toast.LENGTH_LONG).show();
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

}
