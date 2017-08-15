package com.foney.lovespace.homefragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.foney.lovespace.MainActivity;
import com.foney.lovespace.R;
import com.foney.lovespace.activity.PersonInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by foney on 2017/8/14.
 */

public class LovePersonFragment extends Fragment {

    @BindView(R.id.person_textview) TextView personTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.love_person,container,false);
        ButterKnife.bind(this,view);
        return view;
    }
    
    @OnClick(R.id.ceshi) void ceshi() {
        Intent intent = new Intent(getActivity(),PersonInfoActivity.class);
        startActivity(intent);
    }
}
