package com.superwanttoborrow.ui.repayment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.superwanttoborrow.R;
import com.superwanttoborrow.mvp.MVPBaseFragment;

/**
 * @author renji
 */

public class RepaymentFragment extends MVPBaseFragment<RepaymentContract.View, RepaymentPresenter> implements RepaymentContract.View {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repayment,container,false);
        return view;
    }
}
