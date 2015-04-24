package com.lion.graduation2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lion.graduation2.MainActivity;
import com.lion.graduation2.R;
import com.lion.graduation2.bean.json.CdlxBean;
import com.lion.graduation2.bean.json.LoginBean;
import com.lion.graduation2.bean.json.SblxBean;
import com.lion.graduation2.bean.json.UserBean;
import com.lion.graduation2.bean.json.XsbzBean;
import com.lion.graduation2.bean.json.XsnrBean;
import com.lion.graduation2.util.Constant;
import com.lion.graduation2.util.HttpUtils;
import com.lion.graduation2.util.NetworkUtils;

import net.tsz.afinal.FinalDb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 用户登录界面，打开应用会跳转至此，若用户存在，则直接进入系统，否则，重新登录
 * Created by Lion on 2015/3/28.
 */
public class LoginActivity extends ActionBarActivity {

    private EditText userAccount, userPwd;
    private ImageButton login;
    private String account = null;
    private FinalDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //判断用户是否登录过，若登录过，则直接跳过登陆界面
        db = FinalDb.create(this, Constant.DB);
        if (db.findAll(UserBean.class).size() == 1) {
            start();
        } else {
            initData();
            init();
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
                //后期完成后需要把HttpGet请求改成HttpPost
                String url = String.format(HttpUtils.HttpUrl.LOGIN_URL, account, pwd);
                final String result = HttpUtils.httpGet(url);
                Gson gson = new Gson();
                try {
                    LoginBean login = gson.fromJson(result, LoginBean.class);
                    Log.e(Constant.TAG, login.toString());
                    if (login.getFlag().trim().equals("succeed")) {
                        saveAccount(login.getUser());
                        updateToastUI("欢迎登录，" + login.getUser().getName() + "!");
                        start();
                    } else {
                        updateToastUI("用户名或密码错误，请重试！");
                    }
                } catch (JsonSyntaxException jsonSyntax) {
                    jsonSyntax.printStackTrace();
                    updateToastUI("JSON数据解析错误！登录失败！");
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
     * 数据至数据库
     */
    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream is = getAssets().open("cdlx.md");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    String line = null;
                    int cdlx_id = 1, sblx_id = 1, xsnr_id = 1, xsbz_id = 1;
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("##")) {
                            CdlxBean cdlxBean = new CdlxBean();
                            cdlxBean.setId(cdlx_id++);
                            cdlxBean.setName(line.substring("##".length()));
                            db.save(cdlxBean);
                        } else if (line.startsWith("- ")) {
                            SblxBean sblxBean = new SblxBean();
                            sblxBean.setId(sblx_id++);
                            sblxBean.setCdlx_id(cdlx_id - 1);
                            sblxBean.setName(line.substring("- ".length()));
                            db.save(sblxBean);
                        } else if (line.startsWith("\t- ")) {
                            XsnrBean xsnrBean = new XsnrBean();
                            xsnrBean.setId(xsnr_id++);
                            xsnrBean.setSblx_id(sblx_id - 1);
                            xsnrBean.setNr(line.substring("\t- ".length()));
                            db.save(xsnrBean);
                        } else if (line.startsWith("\t\t- ")) {
                            XsbzBean xsbzBean = new XsbzBean();
                            xsbzBean.setId(xsbz_id++);
                            xsbzBean.setSblx_id(sblx_id - 1);
                            xsbzBean.setXsnr_id(xsnr_id - 1);
                            xsbzBean.setDescription(line.substring("\t\t- ".length()));
                            db.save(xsbzBean);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 保存账户信息
     *
     * @param account
     */
    private void saveAccount(UserBean account) {
        db.save(account);
    }
}
