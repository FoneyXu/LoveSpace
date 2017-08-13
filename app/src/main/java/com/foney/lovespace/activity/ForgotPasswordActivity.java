package com.foney.lovespace.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.foney.lovespace.R;

/**
 * Created by foney on 2017/8/13.
 */

public class ForgotPasswordActivity extends BaseActivity implements View.OnClickListener {

    public static void startActivtyAction(Context context) {
        Intent intent = new Intent(context,ForgotPasswordActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
    }

    @Override
    public void onClick(View view) {

    }
}
