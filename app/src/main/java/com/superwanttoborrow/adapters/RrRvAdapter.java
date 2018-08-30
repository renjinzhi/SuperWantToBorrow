package com.superwanttoborrow.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.superwanttoborrow.R;
import com.superwanttoborrow.bean.ReturnDataListBean;
import com.superwanttoborrow.utils.MyTextUtils;

import java.util.List;

public class RrRvAdapter extends RecyclerView.Adapter<RrRvAdapter.RrRvHolder>{


    private Context mContext;
    private List<ReturnDataListBean.DataBean> mList;

    public RrRvAdapter(Context context, List<ReturnDataListBean.DataBean> list) {
        mContext = context;
        mList = list;
    }

    public void updata(List<ReturnDataListBean.DataBean> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RrRvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repay_record_rv, parent,false);
        RrRvHolder rrRvHolder = new RrRvHolder(view);
        return rrRvHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RrRvHolder holder, int position) {
        holder.num.setText(position+1+"");
        holder.mode.setText(MyTextUtils.getChannel(mList.get(position).getChannel()));
        holder.time.setText(MyTextUtils.getStrData(mList.get(position).getPresentTime() + ""));
        holder.money.setText(mList.get(position).getTotalMoney() + "");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class RrRvHolder extends RecyclerView.ViewHolder {
        TextView num;
        TextView mode;
        TextView time;
        TextView money;
        public RrRvHolder(View itemView) {
            super(itemView);
            num = itemView.findViewById(R.id.item_rr_rv_num);
            mode = itemView.findViewById(R.id.item_rr_rv_mode);
            time = itemView.findViewById(R.id.item_rr_rv_time);
            money = itemView.findViewById(R.id.item_rr_rv_money);
        }
    }
}
