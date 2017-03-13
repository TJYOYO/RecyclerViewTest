package com.ttjjttjj.recyclerviewtest.fragment;

import android.os.Bundle;
import android.view.View;

import com.ttjjttjj.recyclerviewtest.R;
import com.ttjjttjj.recyclerviewtest.adapter.NormalRecyclerViewAdapter;
import com.ttjjttjj.recyclerviewtest.base.BaseFragment;

import butterknife.ButterKnife;

/**
 */
public class ThirdFragment extends BaseFragment {

    private NormalRecyclerViewAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.content_main;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initData() {
    }
}