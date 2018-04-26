package com.example.rdos.niisokb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements RestMan.Callback {

    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.posts_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        ApiaryAndroidsAdapter adapter = new ApiaryAndroidsAdapter(App.restMan.getAndroids());
        mRecyclerView.setAdapter(adapter);

        App.restMan.setCallBack(this);
        App.restMan.sendAndroids();
        Button buttonUpdate = findViewById(R.id.button_update);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MainActivity", "buttonUpdate.onClick");
                App.restMan.sendAndroids();
            }
        });
    }

    @Override
    public void onResponse() {
        Log.i("MainActivity", "onResponse");
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }
}
