package com.example.delamey.myapplication5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.delamey.myapplication5.adapter.AutoAdapter;
import com.example.delamey.myapplication5.bean.User;
import com.example.delamey.myapplication5.bean.videodata;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AutoPlayerActivity extends AppCompatActivity {

    @BindView(R.id.autoRecycler)
    RecyclerView autoRecycler;
    private AutoAdapter listNormalAdapter;
    private ArrayList<videodata> userList = new ArrayList<videodata>();
    StandardGSYVideoPlayer detailPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_player);
        ButterKnife.bind(this);
        initdata();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        autoRecycler.setLayoutManager(linearLayoutManager);
        listNormalAdapter=new AutoAdapter(userList,this);
        autoRecycler.setAdapter(listNormalAdapter);
//        ImageView imageView=new ImageView(this);
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setImageResource(R.mipmap.fist);
//        detailPlayer.setThumbImageView(imageView);
        autoRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int firstVisibleItem,lastVisibleItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstVisibleItem=linearLayoutManager.findFirstVisibleItemPosition();
                lastVisibleItem=linearLayoutManager.findLastVisibleItemPosition();
                //大于说明有播放
                if (GSYVideoManager.instance().getPlayPosition()>=0){
                    int positon=GSYVideoManager.instance().getPlayPosition();
                    if (positon<firstVisibleItem||positon>lastVisibleItem){
                        if (!GSYVideoManager.isFullState(AutoPlayerActivity.this)){
                            GSYVideoManager.releaseAllVideos();
                            listNormalAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });

    }

    private void initdata() {
        userList.add(new videodata("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4","视频"));
        userList.add(new videodata("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4","视频"));
        userList.add(new videodata("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4","视频"));
        userList.add(new videodata("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4","视频"));
        userList.add(new videodata("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4","视频"));

    }

    @Override
    public void onBackPressed() {
        if (GSYVideoManager.backFromWindowFull(this)){
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }
}
