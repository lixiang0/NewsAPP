package com.sjd.liblogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by jzl on 2016/11/21.
 */

public class LoginActivity extends AppCompatActivity {
    String username,password;
    String registfile = "registfile.txt";
    String pswfile = "pswfile.txt";
    EditText number;
    EditText psw;
    TextView regist;
    TextView visitor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);
        getSupportActionBar().hide();

        number = (EditText)findViewById(R.id.Regist_username);
        psw = (EditText)findViewById(R.id.Regist_password);
        regist = (TextView)findViewById(R.id.Regist_regist);

        //点击注册按钮
        regist.setOnClickListener(new TextView.OnClickListener(){
            @Override
            public void onClick(View view) {
                    username = number.getText().toString();
                    password = psw.getText().toString();
                //判断输入是否为空
                if(username.isEmpty()||password.isEmpty()||username.contains("#")){
                    Toast.makeText(LoginActivity.this, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
                }
                else {
                    //若不为空，保存信息，传递信息，跳转页面
                    save();
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this,MainActivity.class);
                    //将输入传递给主界面
                    intent.putExtra("username",username);
                    intent.putExtra("password",password);
                    LoginActivity.this.setResult(1,intent);
                    //传递完毕
                    LoginActivity.this.finish();
                }
            }
        });

        //点游客登录按钮:
        visitor = (TextView)findViewById(R.id.Regist_visitor);
        visitor.setOnClickListener(new TextView.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "欢迎！", Toast.LENGTH_SHORT).show();

                LoginActivity.this.finish();
            }
        });
    }

//设置返回按钮
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        LoginActivity.this.finish();
        System.out.println("按下了back键   onBackPressed()");
    }

    //用户信息保存
    public void save(){
        try {
            String space = "#";
            FileOutputStream outputUsername = openFileOutput(registfile, Activity.MODE_APPEND);
            FileOutputStream outputPsw = openFileOutput(pswfile, Activity.MODE_APPEND);
            //保存数据并换行
            String name = username+space;
            String psw = password+space;
            outputUsername.write(name.getBytes());
            outputPsw.write(psw.getBytes());
            outputPsw.flush();
            outputUsername.flush();
            outputPsw.close();
            outputUsername.close();
            Toast.makeText(this, "OK!", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
