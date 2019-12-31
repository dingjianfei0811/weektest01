package com.bawei.dingjianfei20191231.netutil;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.dingjianfei20191231.R;
import com.bawei.dingjianfei20191231.model.bean.Wwang;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 作者：丁建飞
 * 时间：2019/12/31  9:08
 * 类名：com.bawei.dingjianfei20191231.netutil
 */
public class Netutil  {
    //全局单例模式
    private  static  Netutil netutil;
    private final Handler handler;
    private final OkHttpClient okHttpClient;

    private Netutil() {
        handler = new Handler();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(httpLoggingInterceptor)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    public static Netutil getInstance() {
        if (netutil==null){
            synchronized (Netutil.class){
                if (netutil==null){
                    netutil=new Netutil();
                }
            }
        }
        return netutil;
    }
    public  interface  MygetJson{
        void  onGetJson(String name);
        void  onError(Throwable throwable);
    }

//有无网判断
    public  boolean wang(Context context){
        ConnectivityManager systemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = systemService.getActiveNetworkInfo();
        if (activeNetworkInfo != null&&activeNetworkInfo.isAvailable()) {
            return true;
        }else {
            Toast.makeText(context, "无网", Toast.LENGTH_SHORT).show();
            Log.i("i","无网");
            return false;
        }

    }



    //get请求
    public  void  getJsonGet(String http,MygetJson mygetJson){
        Request build = new Request.Builder()
                .get()
                .url(http)
                .build();
        Call call = okHttpClient.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mygetJson.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    if (response!=null&&response.isSuccessful()){
                        String string = response.body().string();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                           mygetJson.onGetJson(string);
                            }
                        });

                    }else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                           mygetJson.onError(new Exception("未接收到数据"));
                            }
                        });
                    }
            }
        });


    }



    //post请求
    public  void  getJsonPost(String http, Map<String , String> map, MygetJson mygetJson){
        FormBody.Builder builder = new FormBody.Builder();
        for (String key: map.keySet()) {
            builder.add(key,map.get(key));
        }
        FormBody formBody = builder.build();

        Request build = new Request.Builder()
                .post(formBody)
                .url(http)
                .build();
        Call call = okHttpClient.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mygetJson.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response!=null&&response.isSuccessful()){
                    String string = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mygetJson.onGetJson(string);
                        }
                    });

                }else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mygetJson.onError(new Exception("未接收到数据"));
                        }
                    });
                }
            }
        });


    }
    //图片请求
    public  void  getGlide(String httpImg, ImageView imageView){
        Glide.with(imageView)
                .load(httpImg)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher_round)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imageView);

    }


}
