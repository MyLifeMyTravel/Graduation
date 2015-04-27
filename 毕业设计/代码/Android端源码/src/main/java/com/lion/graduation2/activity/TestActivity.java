package com.lion.graduation2.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.lion.graduation2.R;
import com.lion.graduation2.bean.json.XsnrBean;
import com.lion.graduation2.util.Constant;
import com.lion.graduation2.util.HttpUtils;

import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* Created by Lion on 2015/4/19.
*/
public class TestActivity extends ActionBarActivity {

    private FinalDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FinalDb.create(TestActivity.this,Constant.DB);
        Log.d(Constant.TAG,db.findById(7, XsnrBean.class).toString());
    }
}
