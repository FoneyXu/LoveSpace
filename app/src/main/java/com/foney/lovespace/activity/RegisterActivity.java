package com.foney.lovespace.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.foney.lovespace.R;
import com.foney.lovespace.util.HttpManager;
import com.foney.lovespace.util.HttpUtil;
import com.foney.lovespace.util.LogUtil;
import com.foney.lovespace.util.MD5Util;
import com.foney.lovespace.util.MyHttpCallback;
import com.foney.lovespace.util.Prompt;
import com.foney.lovespace.util.SysUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

import static com.foney.lovespace.R.id.time;

/**
 * Created by foney on 2017/8/12.
 */

public class RegisterActivity extends BaseActivity implements OnClickListener {

    private EditText phoneEditText;
    private Button getValidateCodeButton;//获取验证码
    private EditText validateCodeEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button regeisterButton;//注册
    private TextView backLoginTextView;//返回登录
    private Map<String,String> params = new HashMap<String,String>();

    private Timer timer = null;
    private TimerTask timerTask = null;
    private int i = 60;//获取验证码倒计时

    private final int MSG_TIMER = 0;

    public static void startActivtyAction(Context context) {
        Intent intent = new Intent(context,RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        LogUtil.d("RegisterActivity","----------------------");
        initView();
        //获取验证码
        getValidateCodeButton.setOnClickListener(this);
        //注册
        regeisterButton.setOnClickListener(this);
        //返回登录
        backLoginTextView.setOnClickListener(this);
    }

    public void initView() {
        backLoginTextView = (TextView)findViewById(R.id.back_login_text_view);
        phoneEditText = (EditText)findViewById(R.id.phone_edit_text);
        getValidateCodeButton = (Button)findViewById(R.id.get_validate_code_button);
        validateCodeEditText = (EditText)findViewById(R.id.validate_code_edit_text);
        passwordEditText = (EditText)findViewById(R.id.password_edit_text);
        confirmPasswordEditText = (EditText)findViewById(R.id.confirm_password_edit_text);
        regeisterButton = (Button)findViewById(R.id.regeister_button);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //获取验证码
            case R.id.get_validate_code_button:
                if(!checkRegeisterInfo(0)) {
                    return;
                }
                params.put("phone",phoneEditText.getText().toString());
                HttpManager.getInstance().doPost(SysUtil.getUrl("/login/getValidateCode"), params, new MyHttpCallback() {
                    @Override
                    public void onBeforeRequest(Request request) {
                        startTimes();
                    }
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText(RegisterActivity.this,"请求异常",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onSuccess(Response response, Prompt prompt) {
                        if(SysUtil.RESULT_TRUE.equals(prompt.getResult())) {
                            Toast.makeText(RegisterActivity.this,"验证码已发送至您手机",Toast.LENGTH_SHORT).show();
                            validateCodeEditText.setText((String)prompt.getData());
                        }else {
                            Toast.makeText(RegisterActivity.this,prompt.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(Response response, String errorMsg) {
                        Toast.makeText(RegisterActivity.this,errorMsg,Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            //注册
            case R.id.regeister_button:
                if(!checkRegeisterInfo(1)) {
                    return;
                }
                params.put("validateCode",validateCodeEditText.getText().toString());
                params.put("password", MD5Util.MD5Encode(passwordEditText.getText().toString()));

                HttpManager.getInstance().doPost(SysUtil.getUrl("/login/registCustomer"), params, new MyHttpCallback() {
                    @Override
                    public void onBeforeRequest(Request request) {
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText(RegisterActivity.this,"请求异常",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(Response response, Prompt prompt) {
                        if(SysUtil.RESULT_TRUE.equals(prompt.getResult())) {
                            Toast.makeText(RegisterActivity.this,"用户注册成功，返回登录",Toast.LENGTH_SHORT).show();
                            clearRegeisterInfo();
                        }else {
                            Toast.makeText(RegisterActivity.this,prompt.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Response response, String errorMsg) {
                        Toast.makeText(RegisterActivity.this,errorMsg,Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            //返回登录页面
            case R.id.back_login_text_view:
                finish();
                break;
        }
    }

    public boolean checkRegeisterInfo(int type) {
        String phone = phoneEditText.getText().toString();
        if(type == 0) {//判断手机号是否已填写
            if(phone.length() != 11) {
                Toast.makeText(this,"手机号格式不正确",Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
        String validateCode = validateCodeEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();
        if(type == 1) {//判断注册信息是否完整
            if(phone.length() != 11) {
                Toast.makeText(this,"手机号格式不正确",Toast.LENGTH_SHORT).show();
                return false;
            }
            if(validateCode.length() != 6) {
                Toast.makeText(this,"验证码格式不正确",Toast.LENGTH_SHORT).show();
                return false;
            }
            if(password.length() == 0) {
                Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
                return false;
            }
            if(confirmPassword.length() == 0) {
                Toast.makeText(this,"确认密码不能为空",Toast.LENGTH_SHORT).show();
                return false;
            }
            if(!password.equals(confirmPassword)) {
                Toast.makeText(this,"前后密码不一致",Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
        return false;
    }

    private Handler mHandler = new Handler(){
        Prompt prompt = null;
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_TIMER ://这是定时器的消息
                    if(msg.arg1 == 0) {
                        i = 60;
                        getValidateCodeButton.setEnabled(true);
                        getValidateCodeButton.setText("点击重发");
                    }else {
                        getValidateCodeButton.setEnabled(false);
                        if(msg.arg1 < 10) {
                            getValidateCodeButton.setText("重发（0"+msg.arg1+"）");
                            startTimes();
                        }else {
                            getValidateCodeButton.setText("重发（"+msg.arg1+"）");
                            startTimes();
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };

    //开始倒计时60秒
    public void startTimes() {
        if(timer == null) {
            timer = new Timer();
        }
        timerTask = new TimerTask() {
            @Override
            public void run() {
                i--;
                Message message = Message.obtain();
                message.what = MSG_TIMER;
                message.arg1 = i;
                mHandler.sendMessage(message);
            }
        };
        timer.schedule(timerTask,1000);
    }

    //停止计时器
    public void stopTimes() {
        if(timer != null) {
            timer.cancel();
        }
    }

    public void clearRegeisterInfo() {
        phoneEditText.setText("");
        validateCodeEditText.setText("");
        passwordEditText.setText("");
        confirmPasswordEditText.setText("");
    }

}
