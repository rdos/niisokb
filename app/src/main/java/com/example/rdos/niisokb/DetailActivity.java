package com.example.rdos.niisokb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "ITEM_ID";
    private int mItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mItemId = getIntent().getIntExtra(EXTRA_POSITION, -1);
        Log.i("DetailActivity", "mItemId=" + String.valueOf(mItemId));

    }
}
