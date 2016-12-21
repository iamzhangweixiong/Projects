package com.zhangwx.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import Z_RecycleView.adapter.ListDecoration;
import Z_RecycleView.adapter.RecycleAdapter;
import Z_RecycleView.adapter.SimpleHeaderWrapper;
import Z_RecycleView.adapter.SimpleLoadMoreWrapper;
import Z_RecycleView.data.DataService;

/**
 * Created by zhangwx on 2016/12/21.
 */
public class RecycleActivity extends Activity {

    private Handler mhandler;
    private DataService mDataService;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshItem;

    private RecycleAdapter mRecycleAdapter;
    private SimpleHeaderWrapper mSimpleHeaderWrapper;
    private SimpleLoadMoreWrapper mSimpleLoadMoreWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);


        mDataService = new DataService();
        mhandler = new Handler(Looper.getMainLooper());
        mRecyclerView = $(R.id.recycleView);
        mRecycleAdapter = new RecycleAdapter();
        mSimpleHeaderWrapper = new SimpleHeaderWrapper(mRecycleAdapter);
        mSimpleLoadMoreWrapper = new SimpleLoadMoreWrapper(mSimpleHeaderWrapper);
        mRecyclerView.setAdapter(mSimpleLoadMoreWrapper);
        mSimpleLoadMoreWrapper.setOnLoadMoreListener(new SimpleLoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mRecycleAdapter.addFeedData(mDataService.getData());
                notifyDataChange();
            }
        });
        mRecyclerView.addItemDecoration(new ListDecoration(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mSwipeRefreshItem = $(R.id.swipeRefreshItem);
        mSwipeRefreshItem.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshItem.setRefreshing(true);
                mRecycleAdapter.addFeedDataToTop(mDataService.getData());
                notifyDataChange();
                mSwipeRefreshItem.setRefreshing(false);
            }
        });
    }

    private void notifyDataChange() {
        mhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSimpleLoadMoreWrapper.notifyDataSetChanged();
            }
        }, 100);
    }

    private <T extends View> T $(int id) {
        return (T) this.findViewById(id);
    }
}
