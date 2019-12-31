package com.bawei.dingjianfei20191231.base;

/**
 * 作者：丁建飞
 * 时间：2019/12/31  9:03
 * 类名：com.bawei.dingjianfei20191231.base
 */
public abstract class BasePresenter <V> {
    protected  V view;


    public void attach(V view) {
        this.view = view;
    }
    public void dttdch() {
        view=null;
    }

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();
}
