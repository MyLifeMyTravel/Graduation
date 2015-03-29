package com.lion.graduation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lion.graduation.json.LoginDto;
import com.lion.graduation.util.HttpUtils;
import com.lion.graduation.util.NetworkUtils;

import java.lang.reflect.Type;

/**
 * 用户登录界面，打开应用会跳转至此，若account.xml存在，则直接进入系统，否则，重新登录
 * Created by Lion on 2015/3/28.
 */
public class LoginActivity extends ActionBarActivity {

    private EditText userAccount, userPwd;
    private ImageButton login;
    private String account = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //判断用户是否登录过，若登录过，则直接跳过登陆界面
        SharedPreferences sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
        account = sharedPreferences.getString("account", null);
        if (null == account) {
            init();
        } else {
            start();
        }
    }

    private void init() {
        userAccount = (EditText) findViewById(R.id.user_account);
        userPwd = (EditText) findViewById(R.id.user_pwd);
        login = (ImageButton) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = userAccount.getText().toString();
                String pwd = userPwd.getText().toString();
                if (account.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "请输入用户名！", Toast.LENGTH_LONG).show();
                } else if (pwd.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "请输入密码！", Toast.LENGTH_LONG).show();
                } else {
                    /**
                     * 检查网络状态，如果无网络，则提示
                     */
                    if (NetworkUtils.isNetworkAvailable(LoginActivity.this)) {
                        login(account, pwd);
                    } else {
                        Toast.makeText(LoginActivity.this, "网络不可用！", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    /**
     * 用户登录
     *
     * @param account 用户帐号
     * @param pwd     用户密码
     */
    private void login(final String account, final String pwd) {
        //连接服务器，获取数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String result = HttpUtils.loginByHttpGet(account, pwd);
                Gson gson = new Gson();
                LoginDto loginDto = gson.fromJson(result, LoginDto.class);
                Log.e("lion", loginDto.toString());
                if (loginDto.getFlag().trim().equals("succeed")) {
                    saveAccount(loginDto);
                    updateToastUI("欢迎登录，" + loginDto.getUser().getName() + "!");
                    start();
                } else {
                    updateToastUI("用户名或密码错误，请重试！");
                }
            }
        }).start();
    }

    /**
     * 更新Toast
     *
     * @param msg 要显示的内容
     */
    private void updateToastUI(final String msg) {
        LoginActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void start() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        //关闭当前Activity
        this.finish();
    }

    /**
     * 保存账户信息
     *
     * @param account
     */
    private void saveAccount(LoginDto account) {
        SharedPreferences sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", account.getUser().getId());
        editor.putString("account", account.getUser().getAccount());
        editor.putString("name", account.getUser().getName());
        editor.putString("pic", account.getUser().getPic());
        editor.putString("info", account.getUser().getInfo());
        editor.commit();
    }
}
