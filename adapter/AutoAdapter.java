package com.example.delamey.myapplication5.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.delamey.myapplication5.R;

import com.example.delamey.myapplication5.bean.videodata;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.shuyu.gsyvideoplayer.GSYVideoBaseManager.TAG;

public class AutoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
private Context context;
private ArrayList<videodata> arrayList;
private ImageView imageView;
private StandardGSYVideoPlayer gsyVideoPlayer;
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class autoplayditem extends RecyclerView.ViewHolder {

        @BindView(R.id.detail_player)
        StandardGSYVideoPlayer detailPlayer;

        autoplayditem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            imageView=new ImageView(context);


        }
    }

    public AutoAdapter(ArrayList <videodata> List,Context context) {
        this.arrayList=List;
        this.context=context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.autoplay, parent, false);

        return new autoplayditem(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof autoplayditem){
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                if (position%2==0){
                    imageView.setImageResource(R.mipmap.fist);
                }
                else{
                    imageView.setImageResource(R.mipmap.second);
                }
                if (imageView.getParent()!=null){
                    ViewGroup viewGroup= (ViewGroup) imageView.getParent();
                    viewGroup.removeView(imageView);
                }
                ((autoplayditem) holder).detailPlayer.setThumbImageView(imageView);
                ((autoplayditem) holder).detailPlayer.setUpLazy(arrayList.get(position).getUrl(),true,null,null,arrayList.get(position).getUrl());
                ((autoplayditem) holder).detailPlayer.getTitleTextView().setVisibility(View.GONE);
                ((autoplayditem) holder).detailPlayer.getBackButton().setVisibility(View.GONE);
                ((autoplayditem) holder).detailPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((autoplayditem) holder).detailPlayer.startWindowFullscreen(context,false,true);
                    }
                });
                ((autoplayditem) holder).detailPlayer.setPlayTag(TAG);
                ((autoplayditem) holder).detailPlayer.setPlayPosition(position);
                ((autoplayditem) holder).detailPlayer.setAutoFullWithSize(true);
                ((autoplayditem) holder).detailPlayer.setReleaseWhenLossAudio(false);
                ((autoplayditem) holder).detailPlayer.setShowFullAnimation(true);
                ((autoplayditem) holder).detailPlayer.setIsTouchWiget(false);
                ((autoplayditem) holder).detailPlayer.setThumbImageView(imageView);
//                ((autoplayditem) holder).detailPlayer.setVideoAllCallBack(new GSYSampleCallBack(){
//                    @Override
//                    public void onPrepared(String url, Object... objects) {
//                        super.onPrepared(url, objects);
//                        if (!gsyVideoPlayer.isIfCurrentIsFullscreen()) {
//                            GSYVideoManager.instance().setNeedMute(true);
//                        }
//                    }
//
//                    @Override
//                    public void onQuitFullscreen(String url, Object... objects) {
//                        GSYVideoManager.instance().setNeedMute(true);
//
//                    }
//
//                    @Override
//                    public void onEnterFullscreen(String url, Object... objects) {
//                        super.onEnterFullscreen(url, objects);
//                        GSYVideoManager.instance().setNeedMute(false);
//
//                    }
//                });


            }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
