package com.foney.lovespace.util;

import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by foney on 2017/8/12.
 * 请求处理核心类，get请求和post请求，以及文件上传请求
 */


//废弃封装类
public class HttpUtil {

    //private static final MediaType FILE_TYPE = MediaType.parse("application/octet-stream");

    /**
     * get请求
     * @param url
     * @param params
     * @return
     */
    public static void doGetRequest(String url, Map<String,String> params, okhttp3.Callback callback) {
        StringBuilder sb = new StringBuilder(url).append("?");
        if(params != null) {
            for(Map.Entry<String,String> entry : params.entrySet()) {
                sb.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
        }
        Log.d("url:",sb.substring(0,sb.length() - 1));
        LogUtil.d("url",sb.substring(0,sb.length() - 1));
        Request request = new Request.Builder()
                .get()
                .url(sb.substring(0,sb.length() - 1))
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(callback);
    }

    /**
     * post请求
     * @param url
     * @param params
     * @return
     */
    public static void doPostRequest(String url, Map<String,String> params,okhttp3.Callback callback) {
        FormBody.Builder builder = new FormBody.Builder();//创建form表单
        if(params != null) {
            for(Map.Entry<String,String> entry : params.entrySet()) {
                builder.add(entry.getKey(),entry.getValue());
            }
        }
        FormBody body = builder.build();
        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(callback);
    }

    public void testHttp() {
        HttpUtil.doGetRequest("",null, new okhttp3.Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //得到服务器返回数据
                String responseData =response.body().string();
            }
            @Override
            public void onFailure(Call call, IOException e) {
                //对异常处理
            }
        });
    }

}
