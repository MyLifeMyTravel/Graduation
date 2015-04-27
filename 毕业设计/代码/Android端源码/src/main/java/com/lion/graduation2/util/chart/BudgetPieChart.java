package com.lion.graduation2.util.chart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

/**
 * Created by Lion on 2015/4/21.
 */
public class BudgetPieChart {

    /* 默认颜色数组 */
    private int[] colors = {Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.CYAN, Color.RED, Color.WHITE, Color.GRAY, Color.BLACK};

    /**
     * 执行获取Intent，这里需要申明<activity android:name="org.achartengine.GraphicalActivity"></activity>
     *
     * @param context
     * @param title
     * @param items
     * @param values
     * @return
     */
    public View execute(Context context, String title, String[] items, double[] values) {
        DefaultRenderer renderer = buildCategoryRenderer(getColors(items.length));
        renderer.setBackgroundColor(Color.GRAY);
        renderer.setApplyBackgroundColor(true);
        renderer.setLabelsTextSize(20);
        renderer.setChartTitle(title);
        renderer.setChartTitleTextSize(30);
        renderer.setLegendTextSize(30);
        renderer.setLegendHeight(100);
        //renderer.setZoomButtonsVisible(true);
        //renderer.setZoomEnabled(true);
        //        Intent intent = ChartFactory.getPieChartIntent(context, buildCategoryDataset(title, items, values), renderer, "Budget");
        View view = ChartFactory.getPieChartView(context, buildCategoryDataset(title, items, values), renderer);
        return view;
    }

    protected DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(20);
        renderer.setLegendTextSize(20);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * 设置数据源
     *
     * @param title
     * @param item
     * @param values
     * @return
     */
    protected CategorySeries buildCategoryDataset(String title, String item[], double[] values) {
        CategorySeries series = new CategorySeries(title);
        for (int i = 0; i < item.length; i++) {
            series.add(item[i], values[i]);
        }
        return series;
    }

    private int[] getColors(int length) {
        int[] tmp_colors = new int[length];
        for (int i = 0; i < length; i++) {
            tmp_colors[i] = colors[i];
        }
        return tmp_colors;
    }
}
