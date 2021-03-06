package com.superwanttoborrow.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.superwanttoborrow.R;
import com.superwanttoborrow.bean.ReturnDataListBean;
import com.superwanttoborrow.utils.MyTextUtils;

import java.util.List;

public class BankRvAdapter extends RecyclerView.Adapter<BankRvAdapter.BankRvHolder>{

    private Context mContext;
    private List<ReturnDataListBean.DataBean> mList;

    public BankRvAdapter(Context context, List<ReturnDataListBean.DataBean> list) {
        mContext = context;
        mList = list;
    }

    public void updata(List<ReturnDataListBean.DataBean> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BankRvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bank_rv, parent,false);
        BankRvHolder bankRvHolder = new BankRvHolder(view);
        return bankRvHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BankRvHolder holder, int position) {
        String s = mList.get(position).getBankName();
        holder.bank_name.setText(s);
        holder.bank_img.setImageResource(MyTextUtils.getBankCard(s));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class BankRvHolder extends RecyclerView.ViewHolder {
        TextView bank_name;
        ImageView bank_img;
        public BankRvHolder(View itemView) {
            super(itemView);
            bank_name = itemView.findViewById(R.id.item_bank_rv_tv);
            bank_img = itemView.findViewById(R.id.item_bank_rv_img);
        }
    }
}
