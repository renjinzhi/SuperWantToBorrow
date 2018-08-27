package com.superwanttoborrow.ui.progressquery;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ProgressQueryActivity extends MVPBaseActivity<ProgressQueryContract.View, ProgressQueryPresenter> implements ProgressQueryContract.View {

    private AlertDialog dialog;
    private TextView dialog_pg_tv_money;
    private TextView dialog_pg_tv_deadline;
    private Button dialog_pg_button;
    private ImageView dialog_pg_img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_query);
        initDialog();
    }

    private void initDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        dialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_progress_query, null);
        dialog.setView(view, 0, 0, 0, 0);// 设置边距为0,保证在2.x的版本上运行没问题
        dialog.setCanceledOnTouchOutside(false);
        dialog_pg_tv_money = (TextView) view.findViewById(R.id.dialog_pg_tv_money);
        dialog_pg_tv_deadline = (TextView) view.findViewById(R.id.dialog_pg_tv_deadline);
        dialog_pg_button = (Button) view.findViewById(R.id.dialog_pg_button);
        dialog_pg_img = (ImageView) view.findViewById(R.id.dialog_pg_img);
        dialog_pg_button.setOnClickListener((view1 -> dialog.dismiss()));
        dialog_pg_img.setOnClickListener((view1 -> dialog.dismiss()));
        dialog.show();
    }


}
