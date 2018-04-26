package com.example.rdos.niisokb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ApiaryAndroidsAdapter extends RecyclerView.Adapter<ApiaryAndroidsAdapter.ViewHolder> {

    private List<ApiaryAndroidsModel> mApiarys;

    public ApiaryAndroidsAdapter(List<ApiaryAndroidsModel> apiarys) {
        mApiarys = apiarys;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.apiary_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ApiaryAndroidsModel apiary = mApiarys.get(position);
        holder.title.setText(apiary.getTitle());
    }

    @Override
    public int getItemCount() {
        if (mApiarys == null)
            return 0;
        return mApiarys.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final Context mContext;
        final TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.text_apiary_item_title);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "onClick", Toast.LENGTH_SHORT).show();
        }
    }
}
