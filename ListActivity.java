package com.example.delamey.myapplication5;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.delamey.myapplication5.adapter.CustomListViewAdapter;
import com.example.delamey.myapplication5.wxapi.CustomListView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    // 自定义Lv
    private CustomListView mCustomLv;
    // 自定义适配器
    private CustomListViewAdapter mAdapter;
    // 内容列表
    private List<String> contentList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initContentList();

        mCustomLv = (CustomListView) findViewById(R.id.custom_lv);
        mCustomLv.setOnDeleteListener(new CustomListView.OnDeleteListener() {

            @Override
            public void onDelete(int index) {
                contentList.remove(index);
                mAdapter.notifyDataSetChanged();
            }
        });

        mAdapter = new CustomListViewAdapter(this, 0, contentList);
        mCustomLv.setAdapter(mAdapter);
        new Thread(new Runnable() {
            @Override
            public void run() {


            }
        });
    }
    // 初始化内容列表
    private void initContentList() {
        for (int i = 0; i < 20; i++) {
            contentList.add("内容项" + i);
        }
    }

    @Override
    public void onBackPressed() {
        if (mCustomLv.isDeleteShown()) {
            mCustomLv.hideDelete();
            return;
        }
        super.onBackPressed();
    }
}
