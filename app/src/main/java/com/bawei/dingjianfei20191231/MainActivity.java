package com.bawei.dingjianfei20191231;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.dingjianfei20191231.base.BaseAppivity;
import com.bawei.dingjianfei20191231.icallBack.ICallBack;
import com.bawei.dingjianfei20191231.model.bean.Bean;
import com.bawei.dingjianfei20191231.model.bean.Wwang;
import com.bawei.dingjianfei20191231.netutil.Netutil;
import com.bawei.dingjianfei20191231.presenter.HomePresenter;
import com.bawei.dingjianfei20191231.view.RViewAdapTer;
import com.bawei.dingjianfei20191231.view.appivity.Select2Activity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseAppivity<HomePresenter> implements ICallBack.IView {


    @BindView(R.id.tiao)
    TextView tiao;
    @BindView(R.id.view)
    RecyclerView view;

    @Override
    protected void initDate() {
        mPresenter.getHomeModel();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected HomePresenter priPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBean(Bean bean) {

if ( Netutil.getInstance().wang(this)){
    List<Bean.RankingBean> ranking = bean.getRanking();
    view.setLayoutManager(new LinearLayoutManager(this));
    RViewAdapTer rViewAdapTer = new RViewAdapTer(ranking);
    view.setAdapter(rViewAdapTer);

    rViewAdapTer.setOnclickItem(new RViewAdapTer.OnclickItem() {
        @Override
        public void onclick(int p) {
            Toast.makeText(MainActivity.this, "吐司"+ranking.get(p).getName(), Toast.LENGTH_SHORT).show();
        }
    });
}else {
    EventBus.getDefault().post(new Wwang("没有网络"));
}


    }

    @Override
    public void onError(Throwable throwable) {
        Log.i("i","未成功获取数据");
    }



    @OnClick(R.id.tiao)
    public void onViewClicked() {
        Intent intent = new Intent(MainActivity.this, Select2Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void  q(Wwang name){
        Toast.makeText(this, ""+name.getWang(), Toast.LENGTH_SHORT).show();
        Log.i("i","没有网络");
    }
}
