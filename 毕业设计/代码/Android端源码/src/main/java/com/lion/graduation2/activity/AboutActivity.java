package com.lion.graduation2.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.lion.graduation2.R;

/**
 * Created by Lion on 2015/4/25.
 */
public class AboutActivity extends ActionBarActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);

        initToolbar();
    }

    private void initToolbar() {
        toolbar = (Toolbar) this.findViewById(R.id.tool_bar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("关于");
        //左上角返回图标
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {//点击Home图标关闭当前Activity
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
