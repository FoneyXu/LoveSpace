package com.foney.lovespace.util;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import com.google.gson.internal.$Gson$Types;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by foney on 2017/8/13.
 *
 */

public abstract class MyHttpCallback {



    public abstract void onBeforeRequest(Request request);
    public abstract void onFailure(Call call, IOException e);
    public abstract void onSuccess(Response response,Prompt prompt);
    public abstract void onError(Response response,String errorMsg);

}
