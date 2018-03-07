package com.example.meng1.day04_zonghe;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.meng1.day04_zonghe.Adapter.Recycler_Adapter;
import com.example.meng1.day04_zonghe.bean.Gson_Demo;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecyclerActivity extends AppCompatActivity {

    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.header)
    RecyclerViewHeader header;
   /* @Bind(R.id.rec_view)
    RecyclerView recView;*/
    private RecyclerView rec_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        ButterKnife.bind(this);
        initView();

        initData();
    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url("http://192.168.43.195:8080/test/data.json").build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Gson_Demo gson_demo = gson.fromJson(string, Gson_Demo.class);
                        List<Gson_Demo.ResultBean.DataBean> list = gson_demo.getResult().getData();

                        List<Integer> arraylist = new ArrayList<>();
                        arraylist.add(R.mipmap.ic_launcher);
                        arraylist.add(R.mipmap.ic_launcher);
                        arraylist.add(R.mipmap.ic_launcher);
                        arraylist.add(R.mipmap.ic_launcher);
                        arraylist.add(R.mipmap.ic_launcher);
                        //设置Banner
                        banner.setImages(arraylist)
//设置没秒间隔时间
                                .setDelayTime(2000)
//设置Banner的样式   属性有四种
                                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
//设置解析图片
                                .setImageLoader(new BitmapPicasso())
//设置指示器在Banner中的位置
                                .setIndicatorGravity(BannerConfig.RIGHT)
//设置标题 （属性得设置成有标题的）

//启动Banner
                                .start();

                        Recycler_Adapter adapter = new Recycler_Adapter(list, RecyclerActivity.this);
                        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                        rec_view.setLayoutManager(staggeredGridLayoutManager);
                        rec_view.setAdapter(adapter);
                        header.attachTo(rec_view, true);
                    }
                });
            }
        });
    }

    class BitmapPicasso extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setImageResource((Integer) path);
        }
    }

    private void initView() {

        header = (RecyclerViewHeader) findViewById(R.id.header);

        rec_view = (RecyclerView) findViewById(R.id.rec_view);

    }
}
