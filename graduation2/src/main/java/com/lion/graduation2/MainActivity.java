package com.lion.graduation2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.lion.graduation2.activity.AboutActivity;
import com.lion.graduation2.activity.EmergencyActivity;
import com.lion.graduation2.activity.GuideActivity;
import com.lion.graduation2.activity.LoginActivity;
import com.lion.graduation2.activity.SettingActivity;
import com.lion.graduation2.activity.SupportActivity;
import com.lion.graduation2.activity.UserActivity;
import com.lion.graduation2.activity.WebViewActivity;
import com.lion.graduation2.bean.json.PlaceBean;
import com.lion.graduation2.bean.json.TaskBean;
import com.lion.graduation2.bean.json.UserBean;
import com.lion.graduation2.bean.model.DrawerItemModel;
import com.lion.graduation2.ui.DividerItemDecoration;
import com.lion.graduation2.ui.adapter.DrawerRecyclerViewAdapter;
import com.lion.graduation2.ui.fragment.base.BaseTourFragment;
import com.lion.graduation2.ui.fragment.ContentFragment;
import com.lion.graduation2.util.BitmapUtils;
import com.lion.graduation2.util.Constant;
import com.lion.graduation2.util.FileUtils;
import com.lion.graduation2.util.HttpUtils;
import com.lion.graduation2.util.circularImage.CircularImage;

import net.tsz.afinal.FinalDb;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements BaseTourFragment.OnDispalyHomeListener, BaseTourFragment.OnTitleSet {

    private List<TaskBean> task;
    private List<PlaceBean> places;
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

    private List<TaskBean> tasks = new ArrayList<>();
    /* final db*/
    private FinalDb db;
    //用户数据
    private UserBean user = null;
    //测试数据
    private int[] icon = {R.drawable.tasks1, R.drawable.forum, R.drawable.warning3, R.drawable.technical, R.drawable.manual, R.drawable.setting, R.drawable.about1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取用户account
        db = FinalDb.create(this, Constant.DB);
        user = db.findAll(UserBean.class).get(0);
        //初始化
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initCircularImage();
    }

    private void init() {
        fragment = new ContentFragment();
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
                    fragment = new ContentFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
                } else if (position == 1) {
                    Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                    startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(MainActivity.this, EmergencyActivity.class);
                    startActivity(intent);
                } else if (position == 3) {
                    Intent intent = new Intent(MainActivity.this, SupportActivity.class);
                    startActivity(intent);
                } else if (position == 4) {
                    Intent intent = new Intent(MainActivity.this, GuideActivity.class);
                    startActivity(intent);
                } else if (position == 5) {
                    Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                    startActivity(intent);
                } else if (position == 6) {
                    Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                    startActivity(intent);
                }

                //getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
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

        mUserName = (TextView) mNavDrawerHeader.findViewById(R.id.navdrawer_user_name);
        mUserName.setText(user.getName());
    }

    /**
     * 初始话抽屉菜单显示的圆形用户头像
     */
    private void initCircularImage() {
        final String pic_path = Constant.Path.IMAGE + user.getAccount() + Constant.PIC_SUFFIX;
        //设置抽屉菜单显示圆形头像
        mCircularImage = (CircularImage) mNavDrawerHeader.findViewById(R.id.navdrawer_user_head);
        //如果头像未下载过，则显示问号并进行下载，否则直接显示用户头像
        if (user.getPic_path() == null) {
            downloadPic(pic_path);
        } else {
            try {
                mCircularImage.setImageDrawable(BitmapUtils.getImage(MainActivity.this, pic_path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mCircularImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });


    }

    private void downloadPic(final String pic_path) {
        //判断目录是否存在，不存在则创建一个
        if (!FileUtils.isDirExist(Constant.Path.IMAGE)) {
            FileUtils.makeDir(Constant.Path.IMAGE);
        }

        FinalHttp fh = new FinalHttp();
        fh.download(HttpUtils.HttpUrl.DOMAIN_URL + "/img/user/116040384.jpg", pic_path, false, new AjaxCallBack<File>() {
            @Override
            public void onSuccess(File file) {
                super.onSuccess(file);
                Log.e("afinal", "下载成功");
                try {
                    user.setPic_path(pic_path);
                    mCircularImage.setImageDrawable(BitmapUtils.getImage(MainActivity.this, pic_path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
            }

            @Override
            public void onFailure(Throwable t, String strMsg) {
                super.onFailure(t, strMsg);
                Log.e("afinal", "下载失败：" + strMsg);
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
        if (id == R.id.action_login_out) {
            login_out();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void login_out() {
        new AlertDialog.Builder(this).setTitle("注销").setNegativeButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.delete(user);
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }).setPositiveButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
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

    public List<TaskBean> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskBean> tasks) {
        this.tasks = tasks;
    }

}
