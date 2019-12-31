package com.bawei.dingjianfei20191231.presenter;

import com.bawei.dingjianfei20191231.base.BasePresenter;
import com.bawei.dingjianfei20191231.icallBack.ICallBack;
import com.bawei.dingjianfei20191231.model.HOmeModel;
import com.bawei.dingjianfei20191231.model.bean.Bean;

/**
 * 作者：丁建飞
 * 时间：2019/12/31  9:21
 * 类名：com.bawei.dingjianfei20191231.presenter
 */
public class HomePresenter extends BasePresenter<ICallBack.IView> implements ICallBack.IPresenter {

    private HOmeModel hOmeModel;

    @Override
    protected void initModel() {
        hOmeModel = new HOmeModel();
    }

    @Override
    public void getHomeModel() {
        hOmeModel.getHomeModel(new ICallBack.IModel.Homemode() {
            @Override
            public void onBean(Bean bean) {
                view.onBean(bean);
            }

            @Override
            public void onError(Throwable throwable) {
                view.onError(throwable);
            }
        });
    }
}
