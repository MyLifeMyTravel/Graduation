package com.lion.graduation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lion.graduation.model.DrawerItem;
import com.lion.graduation.ui.adapter.DrawItemAdapter;
import com.lion.graduation.ui.circularImage.CircularImage;
import com.lion.graduation.ui.fragment.ContentFrame;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    //Toolbar，用来代替ActionBar
    private Toolbar toolbar = null;
    //抽屉菜单
    private DrawerLayout mDrawerLayout = null;
    //抽屉菜单，用于点击条目关闭抽屉菜单使用
    private LinearLayout mLeftDrawer = null;
    //抽屉菜单的ListView
    private ListView mDrawList = null;
    //DrawerLayout.DrawerListener的子类
    private ActionBarDrawerToggle mToggle = null;
    //抽屉菜单显示用户头像的控件
    private CircularImage mCircularImage = null;
    //抽屉菜单条目列表
    private List<DrawerItem> items = null;
    //主界面显示的Fragement
    private Fragment fragment = null;

    //测试数据
    private String[] text = null;
    private int[] icon = {R.drawable.wolf, R.drawable.wolf, R.drawable.wolf, R.drawable.wolf, R.drawable.wolf};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }


    /**
     * init
     */
    private void init() {
        fragment = new ContentFrame();
        getSupportFragmentManager().beginTransaction().add(R.id.content_frame, fragment).commit();

        initData();
        initBar();
        initCircularImage();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawList = (ListView) findViewById(R.id.left_drawer_list);
        mLeftDrawer = (LinearLayout) findViewById(R.id.left_drawer);

        //
        mToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, toolbar, R.drawable.wolf, R.drawable.wolf) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawerLayout.setDrawerListener(mToggle);

        mDrawList.setAdapter(new DrawItemAdapter(items));
        //抽屉菜单ListView条目点击事件
        mDrawList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    fragment = new ContentFrame();
                    //关闭左侧的抽屉菜单
                } else if (position == 1) {

                } else if (position == 2) {

                } else if (position == 3) {

                } else if (position == 4) {

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
                mDrawerLayout.closeDrawer(mLeftDrawer);
            }
        });
    }

    private void initData() {
        text = getResources().getStringArray(R.array.item_text);

        initDrawerItem();
    }

    /**
     * 初始化抽屉菜单条目信息
     */
    private void initDrawerItem() {
        items = new ArrayList<>();
        DrawerItem item = null;
        for (int i = 0; i < text.length; i++) {
            item = new DrawerItem(icon[i], text[i]);
            items.add(item);
        }
    }

    /**
     * 初始化Toolbar
     */
    private void initBar() {
        //style.xml需设置Theme为NoActionBar，否则setSupportActionBar会出错
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        //给左上角图标的左边加上一个返回的图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * 初始话抽屉菜单显示的圆形用户头像
     */
    private void initCircularImage() {
        //设置抽屉菜单显示圆形头像
        mCircularImage = (CircularImage) findViewById(R.id.left_drawer_user_head);
        mCircularImage.setImageResource(R.drawable.wolf);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
