package com.lion.graduation2.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lion.graduation2.R;
import com.lion.graduation2.bean.json.UserBean;
import com.lion.graduation2.ui.view.SimpleTextItem;
import com.lion.graduation2.util.BitmapUtils;
import com.lion.graduation2.util.Constant;
import com.lion.graduation2.util.HttpUtils;
import com.lion.graduation2.util.camera.Camera;
import com.lion.graduation2.util.circularImage.CircularImage;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Lion on 2015/4/18.
 */
public class UserActivity extends ActionBarActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private CircularImage photo;
    private TextView user_name;
    private SimpleTextItem user_describe, user_phone, user_email, user_dept, user_position, user_joinTime;
    private String[] titles = {"个人简介", "联系电话", "电子邮箱", "所属部门", "所属职位", "入职时间"};
    /* 用户信息，可调用Application.getUser()方法获取 */
    private UserBean user;
    /* 用户头像路径 */
    private String pic_path;
    /* 头像名称 */
    private static final String IMAGE_FILE_NAME = "116040384.jpg";
    /* Afinal Http.post方法参数*/
    private AjaxParams params = new AjaxParams();

    /* 请求码 */
    private static final int REQUEST_IMAGE_CAPTURE = 3;
    private static final int REQUEST_TAKE_PICTURE = 4;
    private static final int REQUEST_NONE = 5;

    /* URL */
    private static final String URL_EDIT_INFO = HttpUtils.HttpUrl.DOMAIN_URL + "post/editInfo.php";
    private static final String URL_CHANGE_PHOTE = HttpUtils.HttpUrl.DOMAIN_URL + "post/changePhoto.php";


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
        user_describe.setContent(titles[0], user.getDescribe(), R.drawable.icon_forword);
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
                showDialog();
                break;
            case R.id.user_describe:
                intent = new Intent(UserActivity.this, EditActivity.class);
                intent.putExtra("title", titles[0]);
                intent.putExtra("text", user.getDescribe());
                startActivityForResult(intent, 0);
                break;
            case R.id.user_phone:
                intent = new Intent(UserActivity.this, EditActivity.class);
                intent.putExtra("text", user.getPhone());
                intent.putExtra("title", titles[1]);
                startActivityForResult(intent, 1);
                break;
            case R.id.user_email:
                intent = new Intent(UserActivity.this, EditActivity.class);
                intent.putExtra("title", titles[2]);
                intent.putExtra("text", user.getEmail());
                startActivityForResult(intent, 2);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 0) {
                String result = data.getExtras().getString("result");
                params.put(Constant.Key.ACCOUNT, user.getAccount());
                params.put(Constant.Key.DESCRIBE, result);
                updateInfo(params, URL_EDIT_INFO);
                user_describe.updateContent(result);
            } else if (requestCode == 1) {
                String result = data.getExtras().getString("result");
                params.put(Constant.Key.ACCOUNT, user.getAccount());
                params.put(Constant.Key.PHONE, result);
                updateInfo(params, URL_EDIT_INFO);
                user_phone.updateContent(result);
            } else if (requestCode == 2) {
                String result = data.getExtras().getString("result");
                params.put(Constant.Key.ACCOUNT, user.getAccount());
                params.put(Constant.Key.EMAIL, result);
                updateInfo(params, URL_EDIT_INFO);
                user_email.updateContent(result);
            } else if (requestCode == REQUEST_IMAGE_CAPTURE) {
                startPhotoZoom(data.getData());
            } else if (requestCode == REQUEST_TAKE_PICTURE) {
                File temp = new File(Constant.Path.IMAGE
                        , IMAGE_FILE_NAME);
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
                    setPicToView(data);
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
                Toast.makeText(UserActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t, String strMsg) {
                super.onFailure(t, strMsg);
                Toast.makeText(UserActivity.this, "修改失败，请重试！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 显示选择对话框
     */
    private void showDialog() {

        new AlertDialog.Builder(this)
                .setTitle("设置头像...")
                .setNegativeButton("相册", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(Intent.ACTION_PICK, null);
                        /**
                         * 下面这句话，与其它方式写是一样的效果，如果：
                         * intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                         * intent.setType(""image/*");设置数据类型
                         * 如果要限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                         */
                        intent.setDataAndType(
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                "image/*");
                        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

                    }
                })
                .setPositiveButton("拍照", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        /**
                         * 下面这句还是老样子，调用快速拍照功能，至于为什么叫快速拍照，大家可以参考如下官方
                         * 文档，you_sdk_path/docs/guide/topics/media/camera.html
                         */
                        Intent intent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        //下面这句指定调用相机拍照后的照片存储的路径
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                                .fromFile(new File(Constant.Path.IMAGE,
                                        IMAGE_FILE_NAME)));
                        startActivityForResult(intent, REQUEST_TAKE_PICTURE);
                    }
                }).show();
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        /*
         * 至于下面这个Intent的ACTION是怎么知道的，大家可以看下自己路径下的如下网页
		 * yourself_sdk_path/docs/reference/android/content/Intent.html
		 * 直接在里面Ctrl+F搜：CROP ，之前小马没仔细看过，其实安卓系统早已经有自带图片裁剪功能,
		 * 是直接调本地库的，小马不懂C C++  这个不做详细了解去了，有轮子就用轮子，不再研究轮子是怎么
		 * 制做的了...吼吼
		 */
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, REQUEST_NONE);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            Bitmap bitmap = extras.getParcelable("data");//裁剪后的图片
            Drawable drawable = new BitmapDrawable(bitmap);

            photo.setImageDrawable(drawable);
            FinalHttp fh = new FinalHttp();
            AjaxParams params = new AjaxParams();
            try {
                params.put("image", new File(Constant.Path.IMAGE + user.getAccount() + ".jpg"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            fh.post(URL_CHANGE_PHOTE, params, new AjaxCallBack<Object>() {
                @Override
                public void onStart() {
                    super.onStart();
                }

                @Override
                public void onLoading(long count, long current) {
                    super.onLoading(count, current);
                    Log.e("afianl", "onLoading");
                }

                @Override
                public void onFailure(Throwable t, String strMsg) {
                    super.onFailure(t, strMsg);
                    Log.e("afianl", strMsg);
                }

                @Override
                public void onSuccess(Object o) {
                    super.onSuccess(o);
                    Log.e("afianl", (String) o);
                }
            });
        }
    }


    private void getAccount() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constant.Key.ACCOUNT, MODE_PRIVATE);
        user = new UserBean();
        pic_path = sharedPreferences.getString(Constant.Key.PIC_PATH, null);
        user.setAccount(sharedPreferences.getString(Constant.Key.ACCOUNT, null));
        user.setName(sharedPreferences.getString(Constant.Key.NAME, null));
        user.setPic(sharedPreferences.getString(Constant.Key.PIC_URL, null));
        user.setDescribe(sharedPreferences.getString(Constant.Key.DESCRIBE, null));
        user.setPhone(sharedPreferences.getString(Constant.Key.PHONE, null));
        user.setEmail(sharedPreferences.getString(Constant.Key.EMAIL, null));
        user.setDept(sharedPreferences.getString(Constant.Key.DEPT, null));
        user.setPosition(sharedPreferences.getString(Constant.Key.POSITION, null));
        user.setJoinTime(sharedPreferences.getString(Constant.Key.JOINTIME, null));
    }

}
