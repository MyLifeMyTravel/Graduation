package com.lion.graduation2.ui.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lion.graduation2.R;
import com.lion.graduation2.bean.json.XsbzBean;

import java.util.List;

/**
 * Created by Lion on 2015/4/24.
 */
public class XsbzRecyclerViewAdapter extends BaseRecyclerViewAdapter {

    private List<XsbzBean> xsbzBeans = null;

    public XsbzRecyclerViewAdapter(List<XsbzBean> xsbzBeans) {
        this.xsbzBeans = xsbzBeans;
    }

    public class XsbzViewHolder extends RecyclerView.ViewHolder {

        /* 巡视标准，备注 */
        private TextView xsbz, noteText;
        /* note Button */
        private Button noteBtn;

        public XsbzViewHolder(final View itemView) {
            super(itemView);
            this.xsbz = (TextView) itemView.findViewById(R.id.xsbz);
            this.noteText = (TextView) itemView.findViewById(R.id.noteText);
            this.noteBtn = (Button) itemView.findViewById(R.id.noteBtn);
            this.noteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final EditText inputServer = new EditText(itemView.getContext());
                    final AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setTitle("请输入备注").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer).setNegativeButton("取消", null);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            String note = inputServer.getText().toString();
                            noteText.setText(note);
                            xsbzBeans.get(getPosition()).setNote(note);
                        }
                    });
                    builder.show();
                }
            });

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.xsbz_item_layout, parent, false);
        return new XsbzViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        XsbzViewHolder holder = (XsbzViewHolder) viewHolder;
        holder.xsbz.setText(xsbzBeans.get(position).getDescription());
        if (xsbzBeans.get(position).getNote() == null) {
            holder.noteText.setText("无");
        } else {
            holder.noteText.setText(xsbzBeans.get(position).getNote());
        }
    }

    @Override
    public int getItemCount() {
        return xsbzBeans.size();
    }
}
