package com.superwanttoborrow.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.superwanttoborrow.R;
import com.superwanttoborrow.bean.ReturnBean;
import com.superwanttoborrow.utils.MyTextUtils;

import java.util.List;

public class BrRvAdapter extends RecyclerView.Adapter<BrRvAdapter.BrRvHolder>{


    private Context mContext;
    private List<ReturnBean.DataBean.HistoryRecordsBean> mList;

    public BrRvAdapter(Context context, List<ReturnBean.DataBean.HistoryRecordsBean> list) {
        mContext = context;
        mList = list;
    }

    public void updata(List<ReturnBean.DataBean.HistoryRecordsBean> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BrRvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_borrow_record_rv, parent,false);
        BrRvHolder brRvHolder = new BrRvHolder(view);
        return brRvHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BrRvHolder holder, int position) {
        holder.money.setText(mList.get(position).getLoanAmount()+"元");
        holder.time.setText((MyTextUtils.getStrTime(mList.get(position).getCreateTime()+"")));
        holder.title.setText("借款记录");
        holder.state.setText(MyTextUtils.getStatus(mList.get(position).getLoanStatus()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class BrRvHolder extends ViewHolder{
        TextView money;
        TextView time;
        TextView title;
        TextView state;
        public BrRvHolder(View itemView) {
            super(itemView);
            money = itemView.findViewById(R.id.item_br_rv_money);
            time = itemView.findViewById(R.id.item_br_rv_time);
            title = itemView.findViewById(R.id.item_br_rv_title);
            state = itemView.findViewById(R.id.item_br_rv_state);
        }
    }
}
