package com.bawei.dingjianfei20191231.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * 作者：丁建飞
 * 时间：2019/12/31  9:04
 * 类名：com.bawei.dingjianfei20191231.base
 */
public  abstract class BaseAppivity<P extends  BasePresenter> extends AppCompatActivity {
    public  P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        mPresenter=priPresenter();
        if (mPresenter != null) {
            mPresenter.attach(this);
        }
        ButterKnife.bind(this);
        initView();
        initDate();

    }

    protected abstract void initDate();

    protected abstract void initView();

    protected abstract P priPresenter();

    protected abstract int layoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.dttdch();

        }
    }
}
