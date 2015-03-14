package com.lion.graduation;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lion.graduation.ui.circularImage.CircularImage;


public class MainActivity extends ActionBarActivity {

    //Toolbar，用来代替ActionBar
    private Toolbar toolbar = null;
    //抽屉导航菜单
    private DrawerLayout mDrawerLayout = null;
    //抽屉导航菜单的ListView
    private ListView mDrawList = null;
    private TextView mTextView = null;
    //DrawerLayout.DrawerListener的子类
    private ActionBarDrawerToggle mToggle = null;
    //抽屉菜单显示用户头像的控件
    private CircularImage mCircularImage = null;

    //测试数据
    private String[] test01 = {"数据1", "数据2", "数据3", "数据4", "数据5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击成功", Toast.LENGTH_LONG).show();
            }
        });
        mDrawList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, test01));
    }


    /**
     * init
     */
    private void init() {
        //style.xml需设置Theme为NoActionBar，否则setSupportActionBar会出错
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawList = (ListView) findViewById(R.id.left_drawer_list);
        mTextView = (TextView) findViewById(R.id.left_drawer_user_name);

        mCircularImage = (CircularImage) findViewById(R.id.left_drawer_user_head);
        mCircularImage.setImageResource(R.drawable.wolf);

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
