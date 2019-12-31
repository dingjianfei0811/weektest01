package com.bawei.dingjianfei20191231.model;

import com.bawei.dingjianfei20191231.icallBack.ICallBack;
import com.bawei.dingjianfei20191231.model.bean.Bean;
import com.bawei.dingjianfei20191231.netutil.Netutil;
import com.google.gson.Gson;

/**
 * 作者：丁建飞
 * 时间：2019/12/31  9:08
 * 类名：com.bawei.dingjianfei20191231.model
 */
public class HOmeModel implements ICallBack.IModel {
    @Override
    public void getHomeModel(Homemode homemode) {
      String http="http://blog.zhaoliang5156.cn/api/news/ranking.json";

        Netutil.getInstance().getJsonGet(http, new Netutil.MygetJson() {
            @Override
            public void onGetJson(String name) {
                Bean bean = new Gson().fromJson(name, Bean.class);
                homemode.onBean(bean);
            }

            @Override
            public void onError(Throwable throwable) {
                homemode.onError(throwable);
            }
        });
    }
}
