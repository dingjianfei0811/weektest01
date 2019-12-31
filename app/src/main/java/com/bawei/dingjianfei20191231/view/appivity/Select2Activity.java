package com.bawei.dingjianfei20191231.view.appivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.dingjianfei20191231.R;
import com.bawei.dingjianfei20191231.base.BaseAppivity;
import com.bawei.dingjianfei20191231.base.BasePresenter;
import com.bawei.dingjianfei20191231.model.bean.QQ;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Select2Activity extends BaseAppivity {


    @BindView(R.id.bimg)
    ImageView bimg;
    @BindView(R.id.but1)
    Button but1;
    @BindView(R.id.but2)
    Button but2;

    @Override
    protected void initDate() {

    }

    @Override
    protected void initView() {
        Bitmap cc = CodeUtils.createImage("丁建飞", 200, 200, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        bimg.setImageBitmap(cc);
        bimg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                CodeUtils.analyzeByImageView(bimg, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(Select2Activity.this, "获取数据————"+result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {

                    }
                });
                return true;
            }
        });
    }

    @Override
    protected BasePresenter priPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_select2;
    }



    @OnClick({R.id.but1, R.id.but2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.but1:
                EventBus.getDefault().post("微信");
                break;
            case R.id.but2:
                EventBus.getDefault().post(new QQ("QQ"));
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void  weixin(String name){
        Toast.makeText(this, ""+name, Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void  qq (QQ name){
        Toast.makeText(this, ""+name.getQq(), Toast.LENGTH_SHORT).show();
    }

}
