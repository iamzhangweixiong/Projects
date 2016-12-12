package com.zhangwx.myapplication;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import Z_RecycleView.HeaderAndFooterWrapper;
import Z_RecycleView.LoadMoreWrapper;
import Z_RecycleView.RecycleAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshItem;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private LoadMoreWrapper loadMoreWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = $(R.id.recycleView);
        mSwipeRefreshItem = $(R.id.swipeRefreshItem);
        mSwipeRefreshItem.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshItem.setRefreshing(true);
                reFresh();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new RecycleAdapter(this));
    }

    private void reFresh() {

    }

    private <T extends View> T $(int id) {
        return (T) this.findViewById(id);
    }
}
