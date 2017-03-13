package com.ttjjttjj.recyclerviewtest.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.ttjjttjj.recyclerviewtest.DividerItemDecoration;
import com.ttjjttjj.recyclerviewtest.R;
import com.ttjjttjj.recyclerviewtest.adapter.NormalRecyclerViewAdapter;
import com.ttjjttjj.recyclerviewtest.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/31 0031.
 *
 *
 */
public class FirstFragment extends BaseFragment {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private NormalRecyclerViewAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initData() {

        //相当于listview
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(mActivity));

        mAdapter = new NormalRecyclerViewAdapter(mActivity);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        /**
         * 实现下划线
         *
         * 新建类DividerItemDecoration.class
         *
         * AppTheme加入:  <item name="android:listDivider">@drawable/item_bottom_line</item>
         *
         * DividerItemDecoration.VERTICAL_LIST : 这个方向要注意，如果是下划线，列表的是垂直的，不要弄错，否则出不来
         */

        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL_LIST));


        /**
         * 加入header
         */
        setAdaperHeader();
    }

    private void setAdaperHeader(){
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_first_header,null);
        mAdapter.setHeader(view);
    }
}
