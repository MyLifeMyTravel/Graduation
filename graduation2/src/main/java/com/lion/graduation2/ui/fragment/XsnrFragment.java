package com.lion.graduation2.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.lion.graduation2.R;
import com.lion.graduation2.bean.json.SblxBean;
import com.lion.graduation2.bean.json.XsnrBean;
import com.lion.graduation2.ui.fragment.base.BaseTourFragment;
import com.lion.graduation2.util.Constant;

import net.tsz.afinal.FinalDb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lion on 2015/4/24.
 */
public class XsnrFragment extends BaseTourFragment {

    private boolean isFinish = true;
    private ListView xsnr_list;
    private Button finishBtn = null;
    private SblxBean sblx;

    private FinalDb db;
    private List<XsnrBean> all, xsnrBeans;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sblx = places.get(pPosition).getSblxs().get(dPosition);

        db = FinalDb.create(getActivity(), Constant.DB);
        //获取所有的巡视内容
        all = db.findAll(XsnrBean.class);
        //获取当前设备的巡视内容
        xsnrBeans = getXsnr(sblx.getId());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xsnr, container, false);

        finishBtn = (Button) view.findViewById(R.id.finish);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity()).setTitle("确认已经完成巡检嘛？").setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        places.get(pPosition).getSblxs().get(dPosition).setStatus(true);
                        getActivity().getSupportFragmentManager().popBackStack();
                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

            }
        });
        xsnr_list = (ListView) view.findViewById(R.id.xsnr_list);
        xsnr_list.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getXsnrStr(xsnrBeans)));
        xsnr_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = new XsbzFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("xsnr_id", xsnrBeans.get(position).getId());
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.content_frame, fragment).commit();
            }
        });
        return view;
    }

    private List<XsnrBean> getXsnr(int sblx_id) {
        List<XsnrBean> list = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getSblx_id() == sblx_id) {
                list.add(all.get(i));
            }
        }
        return list;
    }

    private List<String> getXsnrStr(List<XsnrBean> xsnrBeans) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < xsnrBeans.size(); i++) {
            list.add(xsnrBeans.get(i).getNr());
        }
        return list;
    }
}
