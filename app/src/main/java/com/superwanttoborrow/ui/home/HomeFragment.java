package com.superwanttoborrow.ui.home;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.superwanttoborrow.R;
import com.superwanttoborrow.bean.ReturnBean;
import com.superwanttoborrow.bean.ReturnBean.DataBean.UseBean;
import com.superwanttoborrow.bean.ReturnDataListBean;
import com.superwanttoborrow.mvp.MVPBaseFragment;
import com.superwanttoborrow.ui.face.FaceActivity;
import com.superwanttoborrow.ui.login.LoginActivity;
import com.superwanttoborrow.ui.message.MessageActivity;
import com.superwanttoborrow.ui.progressquery.ProgressQueryActivity;
import com.superwanttoborrow.ui.web.WebActivity;
import com.superwanttoborrow.widget.EasyPickerView;
import com.superwanttoborrow.widget.bannerhelper.model.CycleModel;
import com.superwanttoborrow.widget.bannerhelper.widget.CycleView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author renji
 */

public class HomeFragment extends MVPBaseFragment<HomeContract.View, HomePresenter> implements HomeContract.View, View.OnClickListener {

    private ImageView activity_bar_img;
    private CycleView cycleView;
    private EasyPickerView picker_money;
    private EasyPickerView picker_time;
    private Button home_button_borrow;
    private Button home_button_select;
    private List<CycleModel> mData;
    private List<ReturnDataListBean.DataBean> list;
    private CycleModel mCycleModel;
    private ArrayList<String> mPeriodsList;
    private SharedPreferences sharedPreferences;
    private UseBean use = new UseBean();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        mPresenter.getBanner(getContext());
        mPresenter.getOther(getContext());
        return view;
    }

    private void initView(View view) {
        activity_bar_img = (ImageView) view.findViewById(R.id.activity_bar_img);
        activity_bar_img.setOnClickListener(this::onClick);
        cycleView = (CycleView) view.findViewById(R.id.cycleView);
        picker_money = (EasyPickerView) view.findViewById(R.id.picker_money);
        picker_time = (EasyPickerView) view.findViewById(R.id.picker_time);
        home_button_borrow = (Button) view.findViewById(R.id.home_button_borrow);
        home_button_select = (Button) view.findViewById(R.id.home_button_select);
        home_button_borrow.setOnClickListener(this);
        home_button_select.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_button_borrow:
                mPresenter.getRequestID(getContext());
                break;
            case R.id.home_button_select:
                mPresenter.getContract(getContext());
                break;
            case R.id.activity_bar_img:
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", 0);
                if (sharedPreferences.getBoolean("isLogin", false)) {
                    startActivity(new Intent(getActivity(), MessageActivity.class));
                } else {
                    Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
        }
    }

    @Override
    public void getBanner(List<ReturnDataListBean.DataBean> list) {
//        img_banner.setVisibility(View.GONE);
        cycleView.setVisibility(View.VISIBLE);
        mData = new ArrayList<>();
        this.list = list;
        ArrayList<String> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            mCycleModel = new CycleModel(list.get(i).getId() + "", list.get(i).getBannerinfoImageUrl());
            mData.add(mCycleModel);
        }
        // 设置为有轮播功能
        cycleView.setIsHasWheel(true);
        cycleView.setIndicators(R.drawable.dot_focus, R.drawable.dot_normal);
        // 设置数据源并设置监听
        cycleView.setData(mData, getContext(), position -> {
            //轮播图已设置
            if (list.get(position).getBannerinfoImageLinkurl() != null && !list.get(position).getBannerinfoImageLinkurl().equals("")) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", list.get(position).getBannerinfoImageLinkurl());
                intent.putExtra("name", list.get(position).getBannerinfoImageTitle());
                startActivity(intent);
            }
        });
    }

    @Override
    public void getOther(ArrayList<String> moneys, ArrayList<ReturnBean.DataBean.SupportPeriodBean> supportPeriodList) {
        sharedPreferences = getContext().getSharedPreferences("User", 0);
        picker_money.setDataList(moneys);
        mPeriodsList = new ArrayList<>();
        for (int i = 0; i < supportPeriodList.size(); i++) {
            mPeriodsList.add(supportPeriodList.get(i).getDictCode());
        }
        picker_time.setDataList(mPeriodsList);
        picker_money.moveBy(moneys.size() / 2);
        picker_time.moveBy(mPeriodsList.size() / 2);
        picker_money.setOnScrollChangedListener(new EasyPickerView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int curIndex) {
            }

            @Override
            public void onScrollFinished(int curIndex) {
                sharedPreferences.edit().putString("money", moneys.get(curIndex)).apply();
            }
        });

        picker_time.setOnScrollChangedListener(new EasyPickerView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int curIndex) {
            }

            @Override
            public void onScrollFinished(int curIndex) {
                sharedPreferences.edit().putString("periods", mPeriodsList.get(curIndex)).apply();
            }
        });

    }

    @Override
    public void getRequestID(ReturnBean.DataBean dataBean) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", 0);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        use = dataBean.getUse();
        edit.putString("requestId", dataBean.getRequestId());
        if (use != null) {
            edit.putString("addres_code", dataBean.getUse().getAddresCode());
            edit.putString("perAddr", dataBean.getUse().getBizAddr());
            edit.putString("applicantName", dataBean.getUse().getApplicantName());
            edit.putString("cardId", dataBean.getUse().getCardId());
            edit.putString("bankCardId", dataBean.getUse().getBankCardId());
            edit.putString("bankName", dataBean.getUse().getDepositBank());

            edit.putString("linkman1Name", dataBean.getUse().getLinkman1Name());
            edit.putString("linkman2Name", dataBean.getUse().getLinkman2Name());
            edit.putString("linkman1Cell", dataBean.getUse().getLinkman1Cell());
            edit.putString("linkman2Cell", dataBean.getUse().getLinkman2Cell());

            edit.putString("linkman1Relationship", dataBean.getUse().getLinkman1Relationship());
            edit.putString("linkman2Relationship", dataBean.getUse().getLinkman2Relationship());
            edit.putString("incomeRange", dataBean.getUse().getIncomeRange());
            edit.putString("qq", dataBean.getUse().getCustomId());
            edit.putString("weiXin", dataBean.getUse().getCustomName());


//            edit.putString("bizWorkfor", dataBean.getUse().getBizWorkfor());
//            edit.putString("mail", dataBean.getUse().getMail());
//            edit.putString("bizType", dataBean.getUse().getBizType());
//            edit.putString("income", dataBean.getUse().getMonthlyIncome());
        }
        edit.apply();
        startActivity(new Intent(getContext(), FaceActivity.class));
    }

    @Override
    public void getContract(ReturnBean.DataBean dataBean) {
        if (dataBean.getNode() < 1) {
            Toast.makeText(getContext(), "请先前往首页点击“马上借钱”进行审核认证", Toast.LENGTH_LONG).show();
        } else if (dataBean.getNode() == 7) {
            Toast.makeText(getContext(), "您有待还款账单，请还款之后再次申请", Toast.LENGTH_LONG).show();
        } else {
            sharedPreferences = getActivity().getSharedPreferences("User", 0);
            sharedPreferences.edit().putString("requestId", dataBean.getRequestId()).apply();
            Intent intent = new Intent(getActivity(), ProgressQueryActivity.class);
            intent.putExtra("dataBean", dataBean);
            startActivity(intent);
        }
    }
}
