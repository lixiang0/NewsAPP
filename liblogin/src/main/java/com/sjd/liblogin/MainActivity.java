package com.sjd.liblogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    String registfile = "registfile.txt";
    String pswfile = "pswfile.txt";
    EditText Euesrname;
    EditText Epassword;
    TextView forget;
    TextView login;
    TextView regist;
    ArrayList<String> ListUser = new ArrayList<String>();
    ArrayList<String> ListPassword = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //匹配id
        Euesrname = (EditText)findViewById(R.id.M_username);
        Epassword = (EditText)findViewById(R.id.M_password);
        forget = (TextView)findViewById(R.id.M_forget);
        login = (TextView)findViewById(R.id.M_login);
        regist = (TextView)findViewById(R.id.M_regist);

        //设置自带toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("登录");

        //点击登录按钮
        login.setOnClickListener(new TextView.OnClickListener(){
            @Override
            public void onClick(View view) {
                try{
                    FileInputStream inputUsername = openFileInput(registfile);
                    byte[] bytes = new byte[1024];
                    ByteArrayOutputStream userOutputStream = new ByteArrayOutputStream();
                    while(inputUsername.read(bytes)!=-1){
                        userOutputStream.write(bytes,0,bytes.length);
                    }
                    inputUsername.close();
                    userOutputStream.close();
                    String username = new String(userOutputStream.toByteArray());
                    System.out.println(username);
                    //读取密码
                    FileInputStream inputPassword = openFileInput(pswfile);
                    ByteArrayOutputStream pswOutputStream = new ByteArrayOutputStream();
                    while(inputPassword.read(bytes)!=-1){
                        pswOutputStream.write(bytes,0,bytes.length);
                    }
                    inputPassword.close();
                    pswOutputStream.close();
                    String password = new String(pswOutputStream.toByteArray());

                    //将账号密码写入List
                    String[] tokenName = username.split("#");
                    for(int i = 0;i<tokenName.length-1;i++){
                        ListUser.add(tokenName[i]);
                    }
                    String[] tokenPsw = password.split("#");
                    for(int i = 0;i<tokenPsw.length-1;i++){
                        ListPassword.add(tokenPsw[i]);
                    }
                    //写入完成

                    //账号密码匹配
                    String username1 = Euesrname.getText().toString();
                    String password1 = Epassword.getText().toString();
                    int i;

                    for(i=0;i<ListUser.size();i++){
                        //检查是否有输入的账号
                        if(ListUser.get(i).equals(username1)){
                            //匹配密码
                            if(ListPassword.get(i).equals(password1)){
                                Toast.makeText(MainActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                                MainActivity.this.finish();
                            }
                            else
                                Toast.makeText(MainActivity.this, "请检查密码是否正确", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(i==ListUser.size()-1){
                        Toast.makeText(MainActivity.this, "账号不存在", Toast.LENGTH_SHORT).show();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        //点击注册按钮，跳转注册界面
        regist.setOnClickListener(new TextView.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,LoginActivity.class);
                MainActivity.this.startActivityForResult(intent,1);
            }
        });

        //点击忘记密码按钮，暂不使用
        forget.setOnClickListener(new TextView.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "该功能暂未开放", Toast.LENGTH_SHORT).show();
            }
        });
    }

        //返回数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //将注册数据保存在集合里
        String username = data.getStringExtra("username");
        Euesrname.setText(username);
        //设置账号为注册的账号
    }


    //设置再按一次结束程序
    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

//标题栏返回箭头设置
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}



