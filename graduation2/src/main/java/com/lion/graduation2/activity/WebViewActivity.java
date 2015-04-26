package com.lion.graduation2.activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.lion.graduation2.R;

/**
 * Created by Lion on 2015/4/19.
 */
public class WebViewActivity extends ActionBarActivity implements View.OnClickListener {

    /* ToolBar */
    private Toolbar toolbar;
    /* WebView视图 */
    private WebView webView;
    /* 底部浏览器后退、刷新、前进按钮 */
    private Button backBtn, refreshBtn, forwardBtn;
    /* 北极星电力论坛手机版主页 */
    private static final String URL_FORUM = "http://bbs.bjx.com.cn/misc.php?mod=mobile";
    /* ToolBar Title */
    private static final String TITLE = "北极星电力论坛";
    /* 当后退到顶部时的提示 */
    private static final String BACK = "已经是最前一页了,如需退出请按左上角的返回按钮";
    /* 当前进到顶部时的提示 */
    private static final String FORWARD = "已经是最后一页了,不能再前进了";
    /* 加载时的提示 */
    private static final String LOADING = "页面加载中，请稍后";
    /* 加载时的dialog */
    private ProgressDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle(TITLE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        webView = (WebView) findViewById(R.id.webview);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (dialog == null || !dialog.isShowing())
                    dialog = ProgressDialog.show(WebViewActivity.this, null, LOADING);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dialog.dismiss();
            }
        });
        webView.setWebChromeClient(new WebChromeClient());
        //WebView设置
        //WebSettings settings = webView.getSettings();
        //支持javascript
        //settings.setJavaScriptEnabled(true);
        // 设置可以支持缩放
        //settings.setSupportZoom(true);
        // 设置出现缩放工具
        //settings.setBuiltInZoomControls(true);
        //扩大比例的缩放
        //settings.setUseWideViewPort(true);
        //自适应屏幕
        //settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //settings.setLoadWithOverviewMode(true);
        //加载服务器上的页面
        webView.loadUrl(URL_FORUM);

        backBtn = (Button) this.findViewById(R.id.back);
        backBtn.setOnClickListener(this);

        refreshBtn = (Button) this.findViewById(R.id.refresh);
        refreshBtn.setOnClickListener(this);

        forwardBtn = (Button) this.findViewById(R.id.forward);
        forwardBtn.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {
            return true;
        } else if (id == android.R.id.home) {//点击Home图标关闭当前Activity
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            goBack();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back://后退
                goBack();
                break;
            case R.id.refresh://刷新
                webView.reload();
                break;
            case R.id.forward://前进
                goForward();
                break;
            default:
                break;
        }
    }

    private void goBack() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            Toast.makeText(WebViewActivity.this, BACK, Toast.LENGTH_SHORT).show();
        }
    }

    private void goForward() {
        if (webView.canGoForward()) {
            webView.goForward();
        } else {
            Toast.makeText(WebViewActivity.this, FORWARD, Toast.LENGTH_SHORT).show();
        }
    }
}