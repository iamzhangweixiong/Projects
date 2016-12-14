package com.zhangwx.myapplication;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import Z_RecycleView.adapter.HeaderAndFooterWrapper;
import Z_RecycleView.adapter.ListDecoration;
import Z_RecycleView.adapter.LoadMoreWrapper;
import Z_RecycleView.adapter.RecycleAdapter;
import Z_RecycleView.data.DataService;

public class MainActivity extends AppCompatActivity {

    private DataService mDataService;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshItem;

    private RecycleAdapter mRecycleAdapter;
    private LoadMoreWrapper loadMoreWrapper;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDataService = new DataService();
        mRecyclerView = $(R.id.recycleView);
        mRecycleAdapter = new RecycleAdapter();
        mRecyclerView.setAdapter(mRecycleAdapter);
        mRecyclerView.addItemDecoration(new ListDecoration(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mSwipeRefreshItem = $(R.id.swipeRefreshItem);
        mSwipeRefreshItem.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshItem.setRefreshing(true);
                mRecycleAdapter.addFeedData(mDataService.getData());
                mRecycleAdapter.notifyDataSetChanged();
                mSwipeRefreshItem.setRefreshing(false);
            }
        });
    }


    private <T extends View> T $(int id) {
        return (T) this.findViewById(id);
    }
}
