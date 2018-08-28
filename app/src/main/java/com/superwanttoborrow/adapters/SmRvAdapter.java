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

public class SmRvAdapter extends RecyclerView.Adapter<SmRvAdapter.SmRvHolder> {


    private Context mContext;
    private List<String> mList;

    public SmRvAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    public void updata(List<String> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SmRvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_system_message_rv, parent, false);
        SmRvHolder smRvHolder = new SmRvHolder(view);
        return smRvHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SmRvHolder holder, int position) {
        String s = mList.get(position);
        holder.title_time.setText(s);
        holder.mess_time.setText(s);
        holder.title_mess.setText(s);
        holder.mess.setText(s);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class SmRvHolder extends RecyclerView.ViewHolder {
        TextView title_time;
        TextView mess_time;
        TextView title_mess;
        TextView mess;

        public SmRvHolder(View itemView) {
            super(itemView);
            title_time = itemView.findViewById(R.id.sm_rv_title_time);
            mess_time = itemView.findViewById(R.id.sm_rv_mess_time);
            title_mess = itemView.findViewById(R.id.sm_rv_title_mess);
            mess = itemView.findViewById(R.id.sm_tv_mess);
        }
    }
}
