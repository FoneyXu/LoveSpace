package com.foney.lovespace.homefragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.foney.lovespace.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by foney on 2017/8/14.
 */

public class LovePersonFragment extends Fragment {

    @BindView(R.id.person_textview) TextView personTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.love_person,container,false);
    }
    
    @OnClick(R.id.ceshi) void ceshi() {
        personTextView.setText("个人中心");
    }
}
