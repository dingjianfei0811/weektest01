package com.bawei.dingjianfei20191231.icallBack;

import com.bawei.dingjianfei20191231.model.bean.Bean;

/**
 * 作者：丁建飞
 * 时间：2019/12/31  9:01
 * 类名：com.bawei.dingjianfei20191231.icallBack
 */
public interface ICallBack  {
     interface  IView{
         void  onBean(Bean bean);
         void  onError(Throwable throwable);
     }
     interface  IPresenter{
         void  getHomeModel();
     }

     interface  IModel{
         void  getHomeModel(Homemode homemode);

         interface  Homemode{
             void  onBean(Bean bean);
             void  onError(Throwable throwable);
         }
     }


}
