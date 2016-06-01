package com.ttjjttjj.recyclerviewtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ttjjttjj.recyclerviewtest.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/30 0030.
 */
public class NormalRecyclerViewAdapter extends
        RecyclerView.Adapter<NormalRecyclerViewAdapter.NormalViewholder> {

    private Context mContext;
    private String[] titles;

    public NormalRecyclerViewAdapter(Context context){
        this.mContext = context;
        titles= context.getResources().getStringArray(R.array.recyclerView_data);
    }

    @Override
    public NormalViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        //需要传入parent，不然item不能居中
        return new NormalViewholder(
                LayoutInflater.from(mContext).inflate(R.layout.item_text, parent, false));
    }

    @Override
    public void onBindViewHolder(NormalViewholder holder, int position) {
        holder.mTextView.setText(titles[position]);
    }

    @Override
    public int getItemCount() {
        return titles == null ? 0 : titles.length;
    }

    public static class NormalViewholder extends RecyclerView.ViewHolder{

        @Bind(R.id.text_view)
        TextView mTextView;

        public NormalViewholder(View view){
            //需要设置super
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
