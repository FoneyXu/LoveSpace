package com.foney.lovespace.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.foney.lovespace.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by foney on 2017/8/15.
 */

public class PersonInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_info);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.person_info_back_button) void back() {
        finish();
    }


}
