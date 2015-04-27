package com.lion.graduation2.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lion.graduation2.R;
import com.lion.graduation2.activity.base.BaseCameraActivity;
import com.lion.graduation2.util.Constant;
import com.lion.graduation2.util.HttpUtils;
import com.lion.graduation2.util.TimeUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Lion on 2015/4/20.
 */
public class EmergencyActivity extends BaseCameraActivity {

    ProgressDialog dialog = null;
    private Toolbar toolbar;
    private Button camera, phone;
    private String image_name = null;
    // 标题
    private static final String TITLE = "突发情况";
    // 电话
    public static final String PHONE = "10086";
    private static final String URL = HttpUtils.HttpUrl.DOMAIN_URL + "post/emergencyPhoto.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        initToolbar();
        camera = (Button) this.findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image_name = "JPG_" + TimeUtils.getCurrentDate() + ".jpg";
                showDialog(image_name);
            }
        });

        phone = (Button) this.findViewById(R.id.phone);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //封装一个拨打电话的intent，并且将电话号码包装成一个Uri对象传入
                dail();
            }
        });
    }

    private void initToolbar() {
        toolbar = (Toolbar) this.findViewById(R.id.tool_bar);

        setSupportActionBar(toolbar);
        //左上角返回图标
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置导航图标
        //toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        //设置菜单监听事件
        //toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        getSupportActionBar().setTitle(TITLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                // 如果是直接从相册获取
                case REQUEST_IMAGE_CAPTURE:
                    startPhotoZoom(data.getData());
                    break;
                // 如果是调用相机拍照时
                case REQUEST_TAKE_PICTURE:
                    File temp = new File(Constant.Path.RAW_IMAGE, image_name);
                    startPhotoZoom(Uri.fromFile(temp));
                    break;
                // 取得裁剪后的图片
                case REQUEST_NONE:
                    /**
                     * 非空判断大家一定要验证，如果不验证的话，
                     * 在剪裁之后如果发现不满意，要重新裁剪，丢弃
                     * 当前功能时，会报NullException，小马只
                     * 在这个地方加下，大家可以根据不同情况在合适的
                     * 地方做判断处理类似情况
                     *
                     */
                    setPicToView(data, image_name);

                    //上传用户头像
                    FinalHttp fh = new FinalHttp();
                    AjaxParams params = new AjaxParams();
                    try {
                        params.put("capture", new File(PATH_CAPTURE_DIR, image_name));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    fh.post(URL, params, new AjaxCallBack<Object>() {
                        @Override
                        public void onSuccess(Object o) {
                            super.onSuccess(o);
                            dialog.dismiss();
                            String strMsg = (String) o;
                            Log.d("afinal", "上传图片成功：" + strMsg);
                            if (strMsg.equals("succeed")) {
                                Toast.makeText(EmergencyActivity.this, "上传成功！", Toast.LENGTH_SHORT).show();
                                new AlertDialog.Builder(EmergencyActivity.this).setTitle("图片上传成功，是否通知中心？").setNegativeButton("否", null).setPositiveButton("是", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dail();
                                    }
                                }).show();
                            } else {
                                Toast.makeText(EmergencyActivity.this, "上传失败！", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Throwable t, String strMsg) {
                            super.onFailure(t, strMsg);
                            dialog.dismiss();
                            Log.d("afinal", "上传图片失败：" + strMsg);
                            Toast.makeText(EmergencyActivity.this, "上传失败：" + strMsg, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onLoading(long count, long current) {
                            super.onLoading(count, current);
                        }

                        @Override
                        public void onStart() {
                            super.onStart();
                            dialog = ProgressDialog.show(EmergencyActivity.this, null, "正在上传，请稍后...");
                        }
                    });
                    break;
                default:
                    break;
            }
        }
    }

    private void dail() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + PHONE));
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
