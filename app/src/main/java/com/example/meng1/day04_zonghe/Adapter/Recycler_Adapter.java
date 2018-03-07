package com.example.meng1.day04_zonghe.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meng1.day04_zonghe.R;
import com.example.meng1.day04_zonghe.bean.Gson_Demo;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meng1
 * on 2018/3/6.
 * at 北京
 */

public class Recycler_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Gson_Demo.ResultBean.DataBean> list;
    Context context;
    private int type;

    public Recycler_Adapter(List<Gson_Demo.ResultBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        type = -1;
        if (list.get(position).getThumbnail_pic_s03()!=null){
            type =3;
        }else if (list.get(position).getThumbnail_pic_s02()!=null){
            type =2;
        }else {
            type =1;
        }
        return type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh=null;
        if (viewType==1){
            View view = LayoutInflater.from(context).inflate(R.layout.first_item, null);
            vh=new FirstViewHolder(view);
        }else if (viewType==2){
            View view = LayoutInflater.from(context).inflate(R.layout.other_item, null);
            vh=new OtherViewHolder(view);
        }else if (viewType==3){
            View view = LayoutInflater.from(context).inflate(R.layout.three_item, null);
            vh=new ThreeViewHolder(view);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (type==1){
                ((FirstViewHolder)holder).text.setText(list.get(position).getTitle());
                Picasso.with(context).load(list.get(position).getThumbnail_pic_s()).into(((FirstViewHolder)holder).img0);
            }else if (type==2){
                ((OtherViewHolder)holder).text.setText(list.get(position).getTitle());
                Picasso.with(context).load(list.get(position).getThumbnail_pic_s()).into(((OtherViewHolder)holder).img0);
                Picasso.with(context).load(list.get(position).getThumbnail_pic_s02()).into(((OtherViewHolder)holder).img1);
            }else if (type==3){
                ((ThreeViewHolder)holder).text.setText(list.get(position).getTitle());
                Picasso.with(context).load(list.get(position).getThumbnail_pic_s()).into(((ThreeViewHolder)holder).img0);
                Picasso.with(context).load(list.get(position).getThumbnail_pic_s02()).into(((ThreeViewHolder)holder).img1);
                Picasso.with(context).load(list.get(position).getThumbnail_pic_s03()).into(((ThreeViewHolder)holder).img2);
            }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class FirstViewHolder extends RecyclerView.ViewHolder{


        private final TextView text;
        private final ImageView img0;

        public FirstViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            img0 = itemView.findViewById(R.id.img0);
        }
    }
    class OtherViewHolder extends RecyclerView.ViewHolder{

        private final TextView text;
        private final ImageView img0;
        private final ImageView img1;

        public OtherViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            img0 = itemView.findViewById(R.id.img0);
            img1 = itemView.findViewById(R.id.img1);
        }
    }
    class ThreeViewHolder extends RecyclerView.ViewHolder{

        private final TextView text;
        private final ImageView img0;
        private final ImageView img1;
        private final ImageView img2;

        public ThreeViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            img0 = itemView.findViewById(R.id.img0);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
        }
    }
}
