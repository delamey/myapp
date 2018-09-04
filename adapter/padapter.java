package com.example.delamey.myapplication5.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.example.delamey.myapplication5.R;
import com.example.delamey.myapplication5.application.myapplication;
import com.example.delamey.myapplication5.bean.GlideApp;
import com.example.delamey.myapplication5.bean.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class padapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
private Context context;



    private List<User> userList;
    public static final int ITEM1 = 1;
    public static final int ITEM2 = 2;
    public static final int ITEM3 = 3;
    public static final int ITEM4 = 4;

    public static class fistitem extends RecyclerView.ViewHolder {
        View fistview;
        TextView item1text1;

        fistitem(@NonNull View itemView) {
            super(itemView);
            fistview = itemView;
            item1text1 = fistview.findViewById(R.id.item1text1);
        }
    }

    public static class seconditem extends RecyclerView.ViewHolder {

        TextView item2text1;

        TextView item2text2;
        View secondview;

        seconditem(@NonNull View itemView) {
            super(itemView);
            secondview = itemView;
            item2text1 = secondview.findViewById(R.id.item2text1);
            item2text2 = secondview.findViewById(R.id.item2text2);
        }
    }

    public static class thirditem extends RecyclerView.ViewHolder {
        View thridview;
        TextView item3text1;

        TextView item3text2;

        TextView item3text3;

        thirditem(@NonNull View itemView) {
            super(itemView);
            thridview = itemView;
            item3text1 = thridview.findViewById(R.id.item3text1);
            item3text2 = thridview.findViewById(R.id.item3text2);
            item3text3 = thridview.findViewById(R.id.item3text3);
        }
    }
    public static class fourthitem extends RecyclerView.ViewHolder{
        View fourthitem;
        ImageView myimageview;
        public fourthitem(@NonNull View itemView) {
            super(itemView);
                fourthitem=itemView;
            myimageview=fourthitem.findViewById(R.id.myimageview);

        }
    }

    public padapter(List<User> userList,Context context) {
        this.userList = userList;
        this.context =context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case ITEM1:
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item1, viewGroup, false);

                return new fistitem(view);
            case ITEM2:
                View view2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item2, viewGroup, false);
                return new seconditem(view2);

            case ITEM3:
                View view3 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item3, viewGroup, false);
                return new thirditem(view3);
            case ITEM4:
                View view4 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item4, viewGroup, false);
                return new fourthitem(view4);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof fistitem) {
            User user = userList.get(i);
            ((fistitem) viewHolder).item1text1.setText(user.getName());
        } else if (viewHolder instanceof seconditem) {
            User user = userList.get(i);
            seconditem seconditem = (padapter.seconditem) viewHolder;
            seconditem.item2text1.setText(user.getName());
            seconditem.item2text2.setText(user.getUserEmail());

        } else if (viewHolder instanceof thirditem) {
            User user = userList.get(i);
            thirditem thirditem = (padapter.thirditem) viewHolder;
            thirditem.item3text1.setText(user.getName());
            thirditem.item3text2.setText(user.getType());
            thirditem.item3text3.setText(user.getUserEmail());
        }else if (viewHolder instanceof  fourthitem){
            fourthitem fourthitem= (padapter.fourthitem) viewHolder;
//                Glide.with(context).load(userList.get(i).getUrl())
//                    .into(fourthitem.myimageview);
            GlideApp
                    .with(context)
                    .load(userList.get(i).getUrl())
                    .centerCrop()
                    .override(100,100)
                    .into(fourthitem.myimageview);


        }

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (userList.get(position).getType().equals("item1")) {
            return ITEM1;
        } else if (userList.get(position).getType().equals("item2")) {
            return ITEM2;
        } else if (userList.get(position).getType().equals("item3")) {
            return ITEM3;
        } else {
            return ITEM4;
        }
    }


}
