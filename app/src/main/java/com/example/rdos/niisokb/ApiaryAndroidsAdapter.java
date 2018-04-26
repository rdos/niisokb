package com.example.rdos.niisokb;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ApiaryAndroidsAdapter extends RecyclerView.Adapter<ApiaryAndroidsAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitleTextView.setText(App.restMan.getAndroidsTitle(position));
    }

    @Override
    public int getItemCount() {
        return App.restMan.getAndroidsCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView mTitleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.text_title_main_item);
        }

        @Override
        public void onClick(View v) {
//            Toast.makeText(v.getContext(), "onClick", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
            v.getContext().startActivity(intent);
        }
    }
}