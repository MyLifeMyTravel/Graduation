package com.lion.graduation;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lion.graduation.model.DrawerItemModel;
import com.lion.graduation.ui.DividerItemDecoration;
import com.lion.graduation.ui.adapter.DrawerRecyclerAdapter;
import com.lion.graduation.ui.circularImage.CircularImage;
import com.lion.graduation.ui.fragment.ContentFragement;
import com.lion.graduation.ui.fragment.TaskListFragement;
import com.lion.graduation.ui.fragment.UserInfoFragment;
import com.lion.graduation.util.BitmapUtils;
import com.lion.graduation.util.Constant;
import com.lion.graduation.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    //Toolbar，用来代替ActionBar
    private Toolbar toolbar = null;
    //抽屉菜单
    private DrawerLayout mDrawerLayout = null;
    //抽屉菜单，用于点击条目关闭抽屉菜单使用
    private LinearLayout mLeftDrawer = null;

    private LinearLayout mNavDrawerHeader = null;
    //抽屉菜单显示用户头像的控件
    private CircularImage mCircularImage = null;
    //抽屉菜单显示用户姓名的控件
    private TextView mUserName = null;

    //DrawerLayout.DrawerListener的子类
    private ActionBarDrawerToggle mToggle = null;
    //抽屉菜单条目列表
    private List<DrawerItemModel> items = null;
    //主界面显示的Fragement
    private Fragment fragment = null;

    private DrawerRecyclerAdapter mRecycleAdapter = null;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private String account = null;
    //测试数据
    private String[] text = null;
    private int[] icon = {R.drawable.document_36, R.drawable.wolf, R.drawable.wolf, R.drawable.wolf, R.drawable.wolf};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取Account
        SharedPreferences sharedPreferences = getSharedPreferences(Constant.Key.ACCOUNT, MODE_PRIVATE);
        account = sharedPreferences.getString(Constant.Key.ACCOUNT, null);
        init();
    }


    /**
     * init
     */
    private void init() {
        fragment = new ContentFragement();
        getSupportFragmentManager().beginTransaction().add(R.id.content_frame, fragment).commit();

        initData();
        initBar();

        initRecyclerAdapter();
        initRecyclerView();

        initNavDrawer();
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

    }

    private void initData() {
        text = getResources().getStringArray(R.array.item_text);

        initDrawerItem();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //获取任务个数
                String count = HttpUtils.httpGet(String.format(HttpUtils.HttpUrl.GET_COUNT_URL, account));
                items.get(0).setCount(Integer.parseInt(count));
                //通知RecyclerView数据已经更新
                mRecycleAdapter.notifyDataSetChanged();
            }
        }).start();
    }

    /**
     * 初始化抽屉菜单条目信息
     */
    private void initDrawerItem() {
        items = new ArrayList<>();
        DrawerItemModel item = null;
        for (int i = 0; i < text.length; i++) {
            item = new DrawerItemModel(icon[i], text[i]);
            items.add(item);
        }
        items.get(0).setTaskItem(true);
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

    private void initRecyclerAdapter() {
        mRecycleAdapter = new DrawerRecyclerAdapter(items);
        mRecycleAdapter.setOnItemClickListener(new DrawerRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if (position == 0) {//任务界面
                    fragment = new TaskListFragement();
                } else if (position == 1) {

                } else if (position == 2) {

                } else if (position == 3) {

                } else if (position == 4) {

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
                mDrawerLayout.closeDrawer(mLeftDrawer);
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_LONG).show();
            }
        });
        mRecycleAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.navdrawer_recycler);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL_LIST));
        // specify an adapter (see also next example)
        mRecyclerView.setAdapter(mRecycleAdapter);
    }

    private void initNavDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //mDrawList = (ListView) findViewById(R.id.left_drawer_list);
        mLeftDrawer = (LinearLayout) findViewById(R.id.navdrawer);

        initNavDrawerHeader();
    }

    private void initNavDrawerHeader() {
        mNavDrawerHeader = (LinearLayout) mLeftDrawer.findViewById(R.id.navdrawer_header);

        initCircularImage();

        mUserName = (TextView) mNavDrawerHeader.findViewById(R.id.navdrawer_user_name);
        SharedPreferences sharepreferences = getSharedPreferences(Constant.Key.ACCOUNT, MODE_PRIVATE);
        mUserName.setText(sharepreferences.getString(Constant.Key.NAME, null));
    }

    /**
     * 初始话抽屉菜单显示的圆形用户头像
     */
    private void initCircularImage() {
        //设置抽屉菜单显示圆形头像
        mCircularImage = (CircularImage) mNavDrawerHeader.findViewById(R.id.navdrawer_user_head);
        mCircularImage.setImageBitmap(BitmapUtils.readBitMap(this, R.drawable.wolf));
        mCircularImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new UserInfoFragment()).addToBackStack(null).commit();
                mDrawerLayout.closeDrawer(mLeftDrawer);
            }
        });
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //退出应用时结束进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
