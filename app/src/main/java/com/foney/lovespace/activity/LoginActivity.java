package com.foney.lovespace.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.foney.lovespace.R;
import com.foney.lovespace.util.LogUtil;

/**
 * Created by foney on 2017/8/12.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private EditText accountEditText;
    private EditText passwordEditText;
    private CheckBox remeberPasswordCheckBox;
    private CheckBox autoLoginCheckBox;
    private Button loginButton;
    private TextView registerTextView;
    private TextView forgotPasswordTextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();
        registerTextView.setOnClickListener(this);
        forgotPasswordTextView.setOnClickListener(this);
    }

    public void initView() {
        accountEditText = (EditText)findViewById(R.id.account_edit_text);
        passwordEditText = (EditText)findViewById(R.id.password_edit_text);
        remeberPasswordCheckBox = (CheckBox) findViewById(R.id.remeber_password_check_box);
        autoLoginCheckBox = (CheckBox) findViewById(R.id.auto_login_check_box);
        loginButton = (Button) findViewById(R.id.login_button);
        registerTextView = (TextView)findViewById(R.id.regeister_text_view);
        forgotPasswordTextView = (TextView)findViewById(R.id.forgot_password_text_view);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.regeister_text_view:
                RegisterActivity.startActivtyAction(LoginActivity.this);
                break;
            case R.id.forgot_password_text_view:
                ForgotPasswordActivity.startActivtyAction(LoginActivity.this);
                break;
            default:
                break;
        }
    }
}
