package com.lion.graduation2.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
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
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 用户登录界面，打开应用会跳转至此，若用户存在，则直接进入系统，否则，重新登录
 * Created by Lion on 2015/3/28.
 */
public class LoginActivity extends ActionBarActivity {

    //用户名、密码输入框
    private EditText userAccount, userPwd;
    //登录按钮
    private ImageButton login;
    //用户帐号
    private String account = null;
    //用户姓名
    private String name = null;
    //FinalDb对象，管理数据库
    private FinalDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        db = FinalDb.create(this, Constant.DB);

        //判断是否初次使用软件，如果是，则初始化导入相应数据到数据库
        SharedPreferences sharedPreferences = getSharedPreferences("init", Context.MODE_PRIVATE);
        boolean isFirst = sharedPreferences.getBoolean("isFirst", true);
        if (isFirst) {
            isFirst = false;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirst", isFirst);
            editor.commit();
            initData();
        }

        //判断用户是否登录过，若登录过，则直接跳过登陆界面
        if (db.findAll(UserBean.class).size() != 0) {
            start();
        } else {
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
                Log.d(Constant.TAG, "登录JSON数据：" + result);
                Gson gson = new Gson();
                try {
                    final LoginBean login = gson.fromJson(result, LoginBean.class);
                    Log.e(Constant.TAG, login.toString());
                    if (login.getFlag().trim().equals("succeed")) {
                        name = login.getUser().getName();
                        if (login.getUser().getDeviceId() != null && !login.getUser().getDeviceId().equals("")) {
                            if (login.getUser().getDeviceId().equals(getDeviceID())) {
                                saveAccount(login.getUser());
                                start();
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(LoginActivity.this, "尊敬的" + name + ",您的帐号已绑定了设备：" + login.getUser().getDeviceId() + "。请联系管理员解绑后重新登录", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    saveAccount(login.getUser());
                                    bindPhone();
                                }
                            });
                        }
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
     * 初次使用绑定设备ID，如手机丢失，则要去管理员那边解绑
     */
    private void bindPhone() {
        new AlertDialog.Builder(LoginActivity.this).setTitle("正在绑定设备号，是否继续？").setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(LoginActivity.this, "取消", Toast.LENGTH_SHORT).show();
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(LoginActivity.this, "确定", Toast.LENGTH_SHORT).show();
                String deviceID = getDeviceID();
                FinalHttp fh = new FinalHttp();
                AjaxParams params = new AjaxParams();
                params.put(Constant.Key.ACCOUNT, userAccount.getText().toString().trim());
                params.put("device_id", deviceID);
                fh.post(HttpUtils.HttpUrl.POST_UPDATE_DEVICEID_URL, params, new AjaxCallBack<Object>() {
                    @Override
                    public void onSuccess(Object o) {
                        super.onSuccess(o);
                        String strMsg = (String) o;
                        Log.d(Constant.TAG, "绑定设备ID成功：" + strMsg);
                        updateToastUI("欢迎登录，" + name + "!");
                        start();
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                        Log.d(Constant.TAG, "开始上传device_id");
                    }

                    @Override
                    public void onLoading(long count, long current) {
                        super.onLoading(count, current);
                        Log.d(Constant.TAG, "正在上传device_id");
                    }

                    @Override
                    public void onFailure(Throwable t, String strMsg) {
                        super.onFailure(t, strMsg);
                        Log.d(Constant.TAG, "上传失败："+strMsg);
                    }
                });
            }
        }).show();
    }

    private String getDeviceID() {
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        Log.d(Constant.TAG, "设备ID:" + manager.getDeviceId());
        return manager.getDeviceId();
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
     * 导入数据至数据库
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
