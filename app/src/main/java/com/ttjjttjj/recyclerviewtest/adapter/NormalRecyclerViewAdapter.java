package com.ttjjttjj.recyclerviewtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ttjjttjj.recyclerviewtest.R;

/**
 * Created by Administrator on 2016/5/30 0030.
 */
public class NormalRecyclerViewAdapter extends
        RecyclerView.Adapter<NormalRecyclerViewAdapter.NormalViewholder> {

    private Context mContext;
    private String[] titles;
    private static final int TYPE_NORMAL =0;
    private static final int TYPE_HEADER =1;
    private static final int TYPE_FOOTER =2;
    private View mHeaderView;

    public NormalRecyclerViewAdapter(Context context){
        this.mContext = context;
        //假数据的数组
        titles= context.getResources().getStringArray(R.array.recyclerView_data);
    }

    public View getHeader() {
        return mHeaderView;
    }

    public void setHeader(View headerView){

        this.mHeaderView = headerView;
        //插入新的item
        notifyItemInserted(0);
    }

    /**
     * 在方法getItemViewType()中, 根据position来设置不同的view，实现添加header和footer的功能
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_HEADER;
        }

        if (position == getItemCount()-1){
            //最后一个,应该加载Footer
            return TYPE_FOOTER;
        }

        return TYPE_NORMAL;
    }

    @Override
    public NormalViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(mHeaderView != null && viewType == TYPE_HEADER){
            //需要传入parent，不然item不能居中
//            return new NormalViewholder(mHeaderView);
            return new NormalViewholder(LayoutInflater.from(mContext).inflate(R.layout.item_first_header, parent, false));
        }

        //需要传入parent，不然item不能居中
        View viewNormal = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_grid, parent, false);
        return new NormalViewholder(viewNormal);
    }

    @Override
    public void onBindViewHolder(NormalViewholder holder, int position) {

        if(getItemViewType(position) == TYPE_HEADER){
            return;
        }
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? titles.length:titles.length+1;
    }

    public static class NormalViewholder extends RecyclerView.ViewHolder{

        public NormalViewholder(View view){
            //需要设置super
            super(view);
//            ButterKnife.bind(this, view);
        }
    }

}
