package com.lion.graduation2.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.lion.graduation2.R;
import com.lion.graduation2.listener.OnTitleSetListener;
import com.lion.graduation2.ui.fragment.GuideListFragment;
import com.lion.graduation2.util.HttpUtils;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

/**
 * Created by Lion on 2015/4/20.
 */
public class GuideActivity extends ActionBarActivity implements OnTitleSetListener{

    private Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initToolbar();

        getSupportFragmentManager().beginTransaction().add(R.id.fragment, new GuideListFragment()).commit();
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
    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
