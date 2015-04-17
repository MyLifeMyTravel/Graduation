package com.lion.graduation2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lion.graduation2.bean.json.Place;
import com.lion.graduation2.bean.json.Task;
import com.lion.graduation2.bean.model.DrawerItemModel;
import com.lion.graduation2.ui.DividerItemDecoration;
import com.lion.graduation2.ui.adapter.DrawerRecyclerViewAdapter;
import com.lion.graduation2.ui.fragement.BaseFragment;
import com.lion.graduation2.ui.fragement.ContentFragement;
import com.lion.graduation2.ui.fragement.UserFragment;
import com.lion.graduation2.util.BitmapUtils;
import com.lion.graduation2.util.Constant;
import com.lion.graduation2.util.circularImage.CircularImage;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements BaseFragment.OnDispalyHomeListener, BaseFragment.OnTitleSet {

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

    //抽屉菜单RecyclerView适配器
    private DrawerRecyclerViewAdapter mRecycleAdapter = null;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private String account = null;

    private List<Task> tasks = new ArrayList<>();
    private List<Place> places = new ArrayList<>();
    //测试数据
    private int[] icon = {R.drawable.tasks1, R.drawable.warning3, R.drawable.technical, R.drawable.setting, R.drawable.about1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取用户account
        SharedPreferences sharedPreferences = getSharedPreferences(Constant.Key.ACCOUNT, MODE_PRIVATE);
        account = sharedPreferences.getString(Constant.Key.ACCOUNT, null);
        //初始化
        init();
    }

    private void init() {
        fragment = new ContentFragement();
        getSupportFragmentManager().beginTransaction().add(R.id.content_frame, fragment).commit();

        //初始化抽屉菜单信息
        initDrawerItem();

        //初始化ToolBar
        initBar();

        //初始化RecyclerAdapter
        initRecyclerAdapter();

        //初始化RecyclerView
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

    /**
     * 初始化抽屉菜单条目信息
     */
    private void initDrawerItem() {
        String[] text = getResources().getStringArray(R.array.item_text);
        items = new ArrayList<>();
        DrawerItemModel item = null;
        for (int i = 0; i < text.length; i++) {
            item = new DrawerItemModel(icon[i], text[i]);
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

    private void initRecyclerAdapter() {
        mRecycleAdapter = new DrawerRecyclerViewAdapter(items);
        mRecycleAdapter.setOnItemClickListener(new DrawerRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if (position == 0) {//任务界面
                    //fragment = new TaskListFragement();
                } else if (position == 1) {

                } else if (position == 2) {

                } else if (position == 3) {

                } else if (position == 4) {

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
                mDrawerLayout.closeDrawer(mLeftDrawer);
                Log.d(Constant.TAG, "NavDrawer item click.postion:" + position + ".content:" + items.get(position).getText());
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
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new UserFragment()).addToBackStack(null).commit();
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
    public void showDisplayHome(boolean showDrawerToggle) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(showDrawerToggle);
    }

    @Override
    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void restoreTitle() {
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }
}
