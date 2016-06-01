package com.ttjjttjj.recyclerviewtest.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ttjjttjj.recyclerviewtest.R;
import com.ttjjttjj.recyclerviewtest.adapter.NormalRecyclerViewAdapter;

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
        return R.layout.fragment_image_main;
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

        //相当于GridView
//        mRecyclerView.setLayoutManager(
//                new GridLayoutManager(mActivity, 2));

        //相当于瀑布流
//        mRecyclerView.setLayoutManager(
//                new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));

        mAdapter = new NormalRecyclerViewAdapter(mActivity);
        mRecyclerView.setAdapter(mAdapter);

        //瀑布流的间隔
//        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
//        mRecyclerView.addItemDecoration(decoration);
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }











}
