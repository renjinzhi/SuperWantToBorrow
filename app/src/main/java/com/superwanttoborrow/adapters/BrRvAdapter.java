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

import java.util.List;

public class BrRvAdapter extends RecyclerView.Adapter<BrRvAdapter.BrRvHolder>{


    private Context mContext;
    private List<String> mList;

    public BrRvAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    public void updata(List<String> list){
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
        String s = mList.get(position);
        holder.money.setText(s);
        holder.time.setText(s);
        holder.title.setText(s);
        holder.state.setText(s);
        holder.money.setOnClickListener((view -> {}));
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
