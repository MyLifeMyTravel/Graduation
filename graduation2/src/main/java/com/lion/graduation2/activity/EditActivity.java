package com.lion.graduation2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.lion.graduation2.R;

/**
 * Created by Lion on 2015/4/19.
 */
public class EditActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private EditText editText;
    private String text, title;
    private String edit = "修改";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        initWidget();
        text = getIntent().getStringExtra("text");
        title = getIntent().getStringExtra("title");

        getSupportActionBar().setTitle(title + edit);
        editText.setText(text);
    }

    private void initWidget() {
        initToolbar();
        initEditText();
    }

    private void initToolbar() {
        toolbar = (Toolbar) this.findViewById(R.id.tool_bar);

        setSupportActionBar(toolbar);

        //左上角返回图标
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置菜单监听事件
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    private void initEditText() {
        editText = (EditText) this.findViewById(R.id.edit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {
            return true;
        } else if (id == android.R.id.home) {//点击Home图标关闭当前Activity
            setResult(RESULT_CANCELED);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_finish:
                    Intent data = new Intent(EditActivity.this, UserActivity.class);
                    data.putExtra("result", editText.getText().toString());
                    setResult(RESULT_OK, data);
                    finish();
                    break;
                default:
                    break;
            }
            return false;
        }
    };
}
