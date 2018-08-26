package com.superwanttoborrow.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.superwanttoborrow.R;

import java.util.List;

public class RpRvAdapter extends RecyclerView.Adapter<RpRvAdapter.RpRvHolder>{


    private Context mContext;
    private List<String> mList;

    public RpRvAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    public void updata(List<String> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RpRvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repay_plan_rv, parent,false);
        RpRvHolder brRvHolder = new RpRvHolder(view);
        return brRvHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RpRvHolder holder, int position) {
        String s = mList.get(position);
        holder.periods.setText(s);
        holder.money.setText(s);
        holder.time.setText(s);
        holder.state.setText(s);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class RpRvHolder extends RecyclerView.ViewHolder {
        TextView periods;
        TextView money;
        TextView time;
        TextView state;
        public RpRvHolder(View itemView) {
            super(itemView);
            periods = itemView.findViewById(R.id.item_rp_rv_periods);
            money = itemView.findViewById(R.id.item_rp_rv_money);
            time = itemView.findViewById(R.id.item_rp_rv_time);
            state = itemView.findViewById(R.id.item_rp_rv_state);
        }
    }
}
