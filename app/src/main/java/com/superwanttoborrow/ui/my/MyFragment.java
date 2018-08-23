package com.superwanttoborrow.ui.my;


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

public class MyFragment extends MVPBaseFragment<MyContract.View, MyPresenter> implements MyContract.View {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my,container,false);
        return view;
    }
}
