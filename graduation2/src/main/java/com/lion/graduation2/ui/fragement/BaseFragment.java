package com.lion.graduation2.ui.fragement;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by Lion on 2015/4/16.
 */
public class BaseFragment extends Fragment {

    private OnDispalyHomeListener dispalyHomeListener;
    private OnTitleSet titleListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.dispalyHomeListener = (OnDispalyHomeListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnDispalyHomeListener");
        }

        try {
            this.titleListener = (OnTitleSet) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnTitleSet");
        }
    }

    /**
     * 接口，
     */
    public interface OnDispalyHomeListener {
        public void showDisplayHome(boolean showDrawerToggle);
    }

    /**
     * 接口，用于设置标题
     */
    public interface OnTitleSet {
        public void setTitle(String title);

        //此方法需要自己提前保存之前的title。
        public void restoreTitle();
    }

    public OnDispalyHomeListener getDispalyHomeListener() {
        return dispalyHomeListener;
    }

    public void setDispalyHomeListener(OnDispalyHomeListener dispalyHomeListener) {
        this.dispalyHomeListener = dispalyHomeListener;
    }

    public OnTitleSet getTitleListener() {
        return titleListener;
    }

    public void setTitleListener(OnTitleSet titleListener) {
        this.titleListener = titleListener;
    }
}
