package com.lion.graduation2.activity.base;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.lion.graduation2.util.Constant;
import com.lion.graduation2.util.FileUtils;
import com.lion.graduation2.util.camera.Camera;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Lion on 2015/4/22.
 */
public class BaseCameraActivity extends ActionBarActivity {

    /* 请求码 */
    protected static final int REQUEST_IMAGE_CAPTURE = 100;
    protected static final int REQUEST_TAKE_PICTURE = 200;
    protected static final int REQUEST_NONE = 300;

    /* 原图存放路径 */
    protected static final String PATH_RAW_IMAGE_DIR = Constant.Path.RAW_IMAGE;
    /* 剪切图存放路径 */
    protected static final String PATH_CAPTURE_DIR = Constant.Path.CAPTURE_IMAGE;

    /**
     * 显示选择对话框
     */
    protected void showDialog(final String picName) {

        new AlertDialog.Builder(this).setTitle("上传照片...").setNegativeButton("相册", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                /**
                 * 下面这句话，与其它方式写是一样的效果，如果：
                 * intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                 * intent.setType(""image/*");设置数据类型
                 * 如果要限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                 */
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

            }
        }).setPositiveButton("拍照", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
                /**
                 * 下面这句还是老样子，调用快速拍照功能，至于为什么叫快速拍照，大家可以参考如下官方
                 * 文档，you_sdk_path/docs/guide/topics/media/camera.html
                 */
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //下面这句指定调用相机拍照后的照片存储的路径
                if (!FileUtils.isDirExist(Constant.Path.RAW_IMAGE)) {
                    FileUtils.makeDir(Constant.Path.RAW_IMAGE);
                }
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Constant.Path.RAW_IMAGE, picName)));
                startActivityForResult(intent, REQUEST_TAKE_PICTURE);
            }
        }).show();
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
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
    protected void setPicToView(Intent picdata, String imageName) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            Bitmap bitmap = extras.getParcelable("data");//裁剪后的图片

            Camera.storeImageToSDCARD(bitmap, imageName, Constant.Path.CAPTURE_IMAGE);
        }
    }

    /**
     * 上传剪切图
     */
    protected void uploadCapture(String imageName, String url) {
        FinalHttp fh = new FinalHttp();
        AjaxParams params = new AjaxParams();
        try {
            params.put("capture", new File(PATH_CAPTURE_DIR, imageName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        fh.post(url, params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                super.onSuccess(o);
                String strMsg = (String) o;
                if (strMsg.equals("succeed")) {
                    Toast.makeText(BaseCameraActivity.this, "上传成功！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BaseCameraActivity.this, "上传失败！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t, String strMsg) {
                super.onFailure(t, strMsg);
                Toast.makeText(BaseCameraActivity.this, "上传失败：" + strMsg, Toast.LENGTH_SHORT).show();
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

    /**
     * 上传原图
     */
    protected void uploadRawImage() {

    }

}
