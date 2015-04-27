package com.lion.graduation2.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lion.graduation2.R;

/**
 * Created by Lion on 2015/4/19.
 */
public class SimpleTextItem extends LinearLayout {

    private Context context;
    private LayoutInflater inflater;
    private TextView title, content;
    private ImageView icon;
    private View divider;

    public SimpleTextItem(Context context) {
        super(context);
    }

    public SimpleTextItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        addView(inflate(context, R.layout.simple_text_item, null));

        title = (TextView) findViewById(R.id.title);
        content = (TextView) findViewById(R.id.content);
        icon = (ImageView) findViewById(R.id.icon);
        divider = (View) findViewById(R.id.divider);
    }

    public SimpleTextItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setContent(String title, String content) {
        this.title.setText(title);
        this.content.setText(content);
    }

    public void setContent(String title, String content, int icon) {
        this.title.setText(title);
        this.content.setText(content);
        this.icon.setImageResource(icon);
    }

    public void addDivider() {
        divider.setVisibility(VISIBLE);
    }

    public void updateContent(String content) {
        this.content.setText(content);
    }
}
