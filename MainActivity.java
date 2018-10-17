package com.example.delamey.myapplication5;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.delamey.myapplication5.adapter.padapter;
import com.example.delamey.myapplication5.application.myapplication;
import com.example.delamey.myapplication5.bean.MakeMode;
import com.example.delamey.myapplication5.bean.MessageEvent;
import com.example.delamey.myapplication5.bean.Result;
import com.example.delamey.myapplication5.bean.User;
import com.example.delamey.myapplication5.bean.refresh;
import com.example.delamey.myapplication5.bean.young;
import com.example.delamey.myapplication5.constant.api;
import com.example.delamey.myapplication5.constant.constant;
import com.example.delamey.myapplication5.greendao.DaoSession;
import com.example.delamey.myapplication5.greendao.youngDao;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGAMeiTuanRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements  BGARefreshLayout.BGARefreshLayoutDelegate {
    public static final String myjson = "{\"name\":\"mrxi\",\"age\":\"24\",\"gender\":1,\"school\":\"bupt\"}";
    public static final String fanxing = " {\n" +
            "            \"retCode\": \"0000\"，\n" +
            "            \"resInfo\": \"成功\"，\n" +
            "            \"User\":{\n" +
            "                \"name\":\"张三\"，\n" +
            "                \"id\":23,\n" +
            "                \"userEmail\":\"xxx@xx.com\"\n" +
            "            }\n" +
            "        }";
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.event)
    Button event;
    @BindView(R.id.permissions)
    Button permissions;

    @BindView(R.id.refreshText)
    TextView refreshText;
    @BindView(R.id.refresh)
    BGARefreshLayout refresh;
    private DaoSession daoSession;
    public static final int cache = 1024 * 1024 * 10;
    @BindView(R.id.userrecycview)
    RecyclerView userrecycview;
    private List<User> userList = new ArrayList<User>();
    private padapter padapter;
    private Context context;
    RxPermissions rxPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rxPermissions = new RxPermissions(this);
        requestpr();
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        daoSession = myapplication.getDaosession();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        userrecycview.setLayoutManager(linearLayoutManager);
        padapter = new padapter(userList, this);
        userrecycview.setAdapter(padapter);
        initadata();
        selectdata();
        BGANormalRefreshViewHolder bgaNormalRefreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        refresh.setRefreshViewHolder(bgaNormalRefreshViewHolder);
        refresh.setDelegate(this);
        MakeMode.builder builder=new MakeMode.builder();
        MakeMode makeMode=builder.setAge(11).setName("delamey").build();


    }



    private void requestpr() {
        rxPermissions.request(Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION)

                .subscribe(granted -> {
                    if (granted) {
                        Toast.makeText(this, "权限申请成功", Toast.LENGTH_LONG);
                    } else {

                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //onSaveInstanceState();
    }

    private void selectdata() {
        List<young> youngList = daoSession.getYoungDao().queryBuilder().where(youngDao.Properties.Age.lt(26)).list();
        for (int i = 0; i < youngList.size(); i++) {
            int age = youngList.get(i).getAge();
            Log.e("age", String.valueOf(age));
        }
        List<young> youngList2 = daoSession.getYoungDao().queryBuilder().orderDesc(youngDao.Properties.Id).limit(3).list();
        for (int i = 0; i < youngList2.size(); i++) {
            Log.e("desc", String.valueOf(youngList2.get(i).getAge()));
        }
    }

    private void initadata() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(constant.myurl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttp())
                .build();
        api api = retrofit.create(api.class);
        api.getjson("user.json")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())

                .subscribe(new Observer<List<Result<User>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logger.d("111", d.toString());
                    }

                    @Override
                    public void onNext(List<Result<User>> results) {

                        for (int i = 0; i < results.size(); i++) {
                            Log.e("1", "1");
                            Result<User> userResult = results.get(i);
                            userList.add(userResult.getUser());
                            padapter.notifyDataSetChanged();

                        }
                        refresh.endRefreshing();
                        EventBus.getDefault().post(new refresh(results.size()));

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        young young = new young(null, "young1", 24);
        young young1 = new young(null, "young2", 25);
        daoSession.getYoungDao().insert(young);
        daoSession.getYoungDao().insert(young1);
        /*
        deleteBykey(long key)根据主键删除一条
        delete（User entity）根据实体类删除一条
        deleteAll():删除所有
         */
        young.setName("delamey");


    }

    private OkHttpClient okhttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1000, TimeUnit.SECONDS)
                .readTimeout(1000, TimeUnit.SECONDS)
                .writeTimeout(1000, TimeUnit.SECONDS)
                .addInterceptor(new Loggerinterceptor())
                .build();
        return okHttpClient;
    }

    @OnClick({R.id.add, R.id.event})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add:
                User user = null;

                user = new User(R.drawable.ic_launcher_background, "item4", 1);

                EventBus.getDefault().post(user);
                //startActivity(new Intent(this,WebviewActivity.class));
                break;
            case R.id.event:

                break;


        }

    }



    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        initadata();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }


    public class Loggerinterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            //Logger.d(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            CacheControl.Builder builder = new CacheControl.Builder()
                    .maxAge(5, TimeUnit.MINUTES);
            long t2 = System.nanoTime();
            Log.e("respone", response.toString());
            //Logger.d(String.format(String.valueOf(response.request().url()), (t2 - t1) / 1e6d, response.headers()));
            return response.newBuilder()
                    .header("Cache-Control", builder.build().toString())
                    .build();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    //FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
    //formBody.add("username","zhangsan");//传递键值对参数
    //MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
    //String jsonStr = "{\"username\":\"lisi\",\"nickname\":\"李四\"}";//json数据.
    //RequestBody body = RequestBody.create(JSON, josnStr);

    //    private void togson(String myjson) {
//        Gson gson = new Gson();
//        final firstGson firstGson = gson.fromJson(myjson, firstGson.class);
//        System.out.println(firstGson.toString());
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                mytext.setText(firstGson.toString());
//            }
//        });
//    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receive(MessageEvent messageEvent) {
        User user = new User("dddddd", messageEvent.getMessage(), "item2");
        userList.add(user);
        padapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void receive2(User user) {
        userList.add(user);
        padapter.notifyDataSetChanged();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receive3(refresh refresh) {


        refreshText.setText("更新了" + refresh.getNumber() + "条数据");
        refreshText.setVisibility(View.VISIBLE);
        ScaleAnimation scaleAnimation = (ScaleAnimation) AnimationUtils.loadAnimation(this, R.anim.textview);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshText.startAnimation(scaleAnimation);
                refreshText.setVisibility(View.GONE);
            }
        }, 1500);


    }


}
