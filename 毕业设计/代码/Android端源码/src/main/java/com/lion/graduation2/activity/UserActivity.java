package com.lion.graduation2.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lion.graduation2.R;
import com.lion.graduation2.activity.base.BaseCameraActivity;
import com.lion.graduation2.bean.json.UserBean;
import com.lion.graduation2.ui.view.SimpleTextItem;
import com.lion.graduation2.util.BitmapUtils;
import com.lion.graduation2.util.Constant;
import com.lion.graduation2.util.FileUtils;
import com.lion.graduation2.util.HttpUtils;
import com.lion.graduation2.util.circularImage.CircularImage;

import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Lion on 2015/4/18.
 */
public class UserActivity extends BaseCameraActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private CircularImage photo;
    private TextView user_name;
    private SimpleTextItem user_describe, user_phone, user_email, user_dept, user_position, user_joinTime;
    private String[] titles = {"个人简介", "联系电话", "电子邮箱", "所属部门", "所属职位", "入职时间"};
    /* 用户信息，可调用Application.getUser()方法获取 */
    private UserBean user;
    private String pic_path;
    private String pic_name;

    private FinalDb db;
    /* 请求码 */
    private static final int REQUEST_EDIT_USER_DESCRIBLE = 0;
    private static final int REQUEST_EDIT_USER_PHONE = 1;
    private static final int REQUEST_EDIT_USER_EMAIL = 2;

    /* URL */
    private static final String URL_EDIT_INFO = HttpUtils.HttpUrl.DOMAIN_URL + "post/editInfo.php";
    private static final String URL_CHANGE_PHOTE = HttpUtils.HttpUrl.DOMAIN_URL + "post/editPhoto.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getAccount();
        initWidget();
        initData();
        setCircularImage();

        setToolbar();
    }

    private void initWidget() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);

        photo = (CircularImage) this.findViewById(R.id.user_photo);
        user_name = (TextView) this.findViewById(R.id.user_name);
        user_describe = (SimpleTextItem) this.findViewById(R.id.user_describe);
        user_phone = (SimpleTextItem) this.findViewById(R.id.user_phone);
        user_email = (SimpleTextItem) this.findViewById(R.id.user_email);
        user_dept = (SimpleTextItem) this.findViewById(R.id.user_department);
        user_position = (SimpleTextItem) this.findViewById(R.id.user_position);
        user_joinTime = (SimpleTextItem) this.findViewById(R.id.user_joinTime);

        photo.setOnClickListener(this);
        user_describe.setOnClickListener(this);
        user_phone.setOnClickListener(this);
        user_email.setOnClickListener(this);
    }

    private void initData() {
        user_name.setText(user.getName());
        user_describe.setContent(titles[0], user.getDescription(), R.drawable.icon_forword);
        user_phone.setContent(titles[1], user.getPhone(), R.drawable.icon_forword);
        user_email.setContent(titles[2], user.getEmail(), R.drawable.icon_forword);
        user_dept.setContent(titles[3], user.getDept());
        user_position.setContent(titles[4], user.getPosition());
        user_joinTime.setContent(titles[5], user.getJoinTime());
    }

    private void setCircularImage() {
        try {
            photo.setImageDrawable(BitmapUtils.getImage(UserActivity.this, pic_path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setToolbar() {
        toolbar.setTitle("个人信息");

        setSupportActionBar(toolbar);
        //左上角返回图标
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置导航图标
        //toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        //设置菜单监听事件
        //toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {//点击Home图标关闭当前Activity
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.user_photo:
                String pic_name = user.getAccount() + Constant.PIC_SUFFIX;
                showDialog(pic_name);
                break;
            case R.id.user_describe:
                intent = new Intent(UserActivity.this, EditActivity.class);
                intent.putExtra("title", titles[0]);
                intent.putExtra("text", user.getDescription());
                startActivityForResult(intent, REQUEST_EDIT_USER_DESCRIBLE);
                break;
            case R.id.user_phone:
                intent = new Intent(UserActivity.this, EditActivity.class);
                intent.putExtra("title", titles[1]);
                intent.putExtra("text", user.getPhone());
                startActivityForResult(intent, REQUEST_EDIT_USER_PHONE);
                break;
            case R.id.user_email:
                intent = new Intent(UserActivity.this, EditActivity.class);
                intent.putExtra("title", titles[2]);
                intent.putExtra("text", user.getEmail());
                startActivityForResult(intent, REQUEST_EDIT_USER_EMAIL);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_EDIT_USER_DESCRIBLE) {
                String result = data.getExtras().getString("result");
                user.setDescription(result);
                AjaxParams params = new AjaxParams();
                params.put(Constant.Key.ACCOUNT, user.getAccount());
                params.put(Constant.Key.DESCRIPTION, result);
                updateInfo(params, URL_EDIT_INFO);
                user_describe.updateContent(result);
            } else if (requestCode == REQUEST_EDIT_USER_PHONE) {
                String result = data.getExtras().getString("result");
                user.setPhone(result);
                AjaxParams params = new AjaxParams();
                params.put(Constant.Key.ACCOUNT, user.getAccount());
                params.put(Constant.Key.PHONE, result);
                updateInfo(params, URL_EDIT_INFO);
                user_phone.updateContent(result);
            } else if (requestCode == REQUEST_EDIT_USER_EMAIL) {
                String result = data.getExtras().getString("result");
                user.setEmail(result);
                AjaxParams params = new AjaxParams();
                params.put(Constant.Key.ACCOUNT, user.getAccount());
                params.put(Constant.Key.EMAIL, result);
                updateInfo(params, URL_EDIT_INFO);
                user_email.updateContent(result);
            } else if (requestCode == REQUEST_IMAGE_CAPTURE) {
                startPhotoZoom(data.getData());
            } else if (requestCode == REQUEST_TAKE_PICTURE) {
                File temp = new File(Constant.Path.RAW_IMAGE, pic_name);
                startPhotoZoom(Uri.fromFile(temp));
            } else if (requestCode == REQUEST_NONE) {
                /**
                 * 非空判断大家一定要验证，如果不验证的话，
                 * 在剪裁之后如果发现不满意，要重新裁剪，丢弃
                 * 当前功能时，会报NullException，小马只
                 * 在这个地方加下，大家可以根据不同情况在合适的
                 * 地方做判断处理类似情况
                 *
                 */
                if (data != null) {
                    setPicToView(data, pic_name);

                    //上传用户头像
                    FinalHttp fh = new FinalHttp();
                    AjaxParams params = new AjaxParams();
                    try {
                        params.put("capture", new File(PATH_CAPTURE_DIR, pic_name));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    fh.post(URL_CHANGE_PHOTE, params, new AjaxCallBack<Object>() {
                        @Override
                        public void onSuccess(Object o) {
                            super.onSuccess(o);
                            String strMsg = (String) o;
                            if (strMsg.equals("succeed")) {
                                try {
                                    //拷贝截图到用户头像
                                    FileUtils.copyFile(Constant.Path.CAPTURE_IMAGE + pic_name, pic_path);
                                    photo.setImageDrawable(BitmapUtils.getImage(UserActivity.this, pic_path));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Toast.makeText(UserActivity.this, "上传成功！", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(UserActivity.this, "上传失败！", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Throwable t, String strMsg) {
                            super.onFailure(t, strMsg);
                            Toast.makeText(UserActivity.this, "上传失败：" + strMsg, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onLoading(long count, long current) {
                            super.onLoading(count, current);
                        }

                        @Override
                        public void onStart() {
                            super.onStart();
                        }
                    });
                }
            }
        }
    }

    private void updateInfo(AjaxParams params, String url) {
        FinalHttp fh = new FinalHttp();
        fh.post(url, params, new AjaxCallBack<Object>() {

            @Override
            public void onSuccess(Object o) {
                super.onSuccess(o);
                String strMsg = (String) o;
                Log.e("afinal", strMsg);
                if (strMsg.equals("succeed")) {
                    db.update(user);
                    Toast.makeText(UserActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UserActivity.this, strMsg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t, String strMsg) {
                super.onFailure(t, strMsg);
                Toast.makeText(UserActivity.this, "修改失败，请重试！", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getAccount() {
        db = FinalDb.create(this, Constant.DB);
        user = db.findAll(UserBean.class).get(0);
        pic_name = user.getAccount() + Constant.PIC_SUFFIX;
        pic_path = Constant.Path.IMAGE + pic_name;
    }

}
