package com.foney.lovespace.listener;

/**
 * Created by foney on 2017/8/12.
 */

public interface NetworkRequestListener {

    /**
     * 网络请求前回调,加载框展示
     */
    void onRequestBefore();

    /**
     * 网络请求成功
     */
    void onRequestSuccess(Object object);

    /**
     * 网络请求失败，提示请求失败信息
     * @param msg
     */
    void onRequestFail(String msg);

    /**
     * 网络请求完成，关闭加载框
     */
    void onRequestComplete();

}
