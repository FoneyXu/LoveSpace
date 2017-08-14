package com.foney.lovespace.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.foney.lovespace.MainActivity;
import com.foney.lovespace.R;
import com.foney.lovespace.util.HttpManager;
import com.foney.lovespace.util.MD5Util;
import com.foney.lovespace.util.MyHttpCallback;
import com.foney.lovespace.util.Prompt;
import com.foney.lovespace.util.SysUtil;
import com.foney.lovespace.util.ZLoadingDialogUtil;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;
import com.zyao89.view.zloading.circle.DoubleCircleBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

import static android.R.attr.type;

/**
 * Created by foney on 2017/8/13.
 */

public class ForgotPasswordActivity extends BaseActivity {

    @BindView(R.id.phone_edit_text) EditText phoneEditText;
    @BindView(R.id.validate_code_edit_text) EditText validateCodeEditText;
    @BindView(R.id.new_password_edit_text) EditText newPasswordEditText;
    @BindView(R.id.confirm_password_edit_text) EditText confirmPasswordEditText;
    @BindView(R.id.get_validate_code_button) Button getValidateCodeButton;
    @BindView(R.id.complete_button) Button completeButton;

    private Timer timer = null;
    private TimerTask timerTask = null;
    private int i = 60;//获取验证码倒计时
    private final int MSG_TIMER = 0;

    private Map<String,String> params = new HashMap<String,String>();

    //开启活动方法
    public static void startActivtyAction(Context context) {
        Intent intent = new Intent(context,ForgotPasswordActivity.class);
        context.startActivity(intent);
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        ButterKnife.bind(this);
    }

    //获取验证码
    @OnClick(R.id.get_validate_code_button) void getValidateCode() {
        if(!checkInfo(0)) {
            return;
        }
        params.clear();
        params.put("phone",phoneEditText.getText().toString());
        params.put("type","1");//type=1  表示当前获取验证码是在忘记密码时候使用
        HttpManager.getInstance().doPost(SysUtil.getUrl("login/getValidateCode"),params,new MyHttpCallback() {
            @Override
            public void onBeforeRequest(Request request) {
                startTimes();
                ZLoadingDialogUtil.getInstace().start(ForgotPasswordActivity.this);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                ZLoadingDialogUtil.getInstace().stop();
                Toast.makeText(ForgotPasswordActivity.this,"请求异常",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(Response response, Prompt prompt) {
                ZLoadingDialogUtil.getInstace().stop();
                if(SysUtil.RESULT_TRUE.equals(prompt.getResult())) {
                    Toast.makeText(ForgotPasswordActivity.this,"验证码已发送至您的手机",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ForgotPasswordActivity.this,prompt.getMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Response response, String errorMsg) {
                ZLoadingDialogUtil.getInstace().stop();
                Toast.makeText(ForgotPasswordActivity.this,errorMsg,Toast.LENGTH_SHORT).show();
            }
        });

    }

    //点击完成，完成密码重置
    @OnClick(R.id.complete_button) void complete() {
        if(!checkInfo(1)) {
            return;
        }
        params.put("validateCode",validateCodeEditText.getText().toString());
        params.put("password", MD5Util.MD5Encode(newPasswordEditText.getText().toString()));
        HttpManager.getInstance().doPost(SysUtil.getUrl("/login/updatePassword"), params, new MyHttpCallback() {
            @Override
            public void onBeforeRequest(Request request) {
                startTimes();
                ZLoadingDialogUtil.getInstace().start(ForgotPasswordActivity.this);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                ZLoadingDialogUtil.getInstace().stop();
                Toast.makeText(ForgotPasswordActivity.this,"请求异常",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(Response response, Prompt prompt) {
                ZLoadingDialogUtil.getInstace().stop();
                if(SysUtil.RESULT_TRUE.equals(prompt.getResult())) {
                    Toast.makeText(ForgotPasswordActivity.this,"密码修改成功，返回登录",Toast.LENGTH_SHORT).show();
                    clearForgotInfo();
                }else {
                    Toast.makeText(ForgotPasswordActivity.this,prompt.getMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Response response, String errorMsg) {
                ZLoadingDialogUtil.getInstace().stop();
                Toast.makeText(ForgotPasswordActivity.this,errorMsg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean checkInfo(int type) {
        if(type == 0) {// 0 获取验证码时检验
            if(phoneEditText.getText().toString().length() != 11) {
                Toast.makeText(this,"电话号码格式不对",Toast.LENGTH_SHORT).show();
                return false;
            }
        }else if(type == 1) {// 1 点击“完成”时修改密码时检验
            if(phoneEditText.getText().toString().length() != 11) {
                Toast.makeText(this,"电话号码格式不对",Toast.LENGTH_SHORT).show();
                return false;
            }
            if(validateCodeEditText.getText().toString().length() != 6) {
                Toast.makeText(this,"验证码格式不对",Toast.LENGTH_SHORT).show();
                return false;
            }
            if(newPasswordEditText.getText().toString().length() == 0) {
                Toast.makeText(this,"新密码输入不能为空",Toast.LENGTH_SHORT).show();
                return false;
            }
            if(confirmPasswordEditText.getText().toString().length() == 0) {
                Toast.makeText(this,"确认密码输入不能为空",Toast.LENGTH_SHORT).show();
                return false;
            }
            if(!newPasswordEditText.getText().toString().equals(confirmPasswordEditText.getText().toString())) {
                Toast.makeText(this,"前后密码不一致",Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    public void clearForgotInfo() {
        phoneEditText.setText("");
        validateCodeEditText.setText("");
        newPasswordEditText.setText("");
        confirmPasswordEditText.setText("");
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


}
