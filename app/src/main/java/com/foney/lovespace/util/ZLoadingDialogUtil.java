package com.foney.lovespace.util;

import android.content.Context;
import android.graphics.Color;

import com.foney.lovespace.activity.ForgotPasswordActivity;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

/**
 * Created by foney on 2017/8/14.
 */

public class ZLoadingDialogUtil {

    private final Z_TYPE type = Z_TYPE.DOUBLE_CIRCLE;
    private ZLoadingDialog zLoadingDialog;
    private static ZLoadingDialogUtil zLoadingDialogUtil;
    private ZLoadingDialogUtil() {

    }
    public static ZLoadingDialogUtil getInstace() {
        if(zLoadingDialogUtil == null) {
            synchronized (ZLoadingDialogUtil.class) {
                zLoadingDialogUtil = new ZLoadingDialogUtil();
            }
        }
        return zLoadingDialogUtil;
    }

    public void start(Context context) {
        if(zLoadingDialog == null) {
            zLoadingDialog = new ZLoadingDialog(context);
        }
        zLoadingDialog.setLoadingBuilder(type)
                .setLoadingColor(Color.YELLOW)
                .setCanceledOnTouchOutside(false)
                .setCancelable(false)
                .show();
    }

    public void stop() {
        if(zLoadingDialog != null) {
            zLoadingDialog.dismiss();
        }
    }

}
