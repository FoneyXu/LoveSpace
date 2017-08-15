package com.foney.lovespace;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.foney.lovespace.activity.BaseActivity;
import com.foney.lovespace.util.FragmentUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {

    private BottomNavigationView bottomNavigationView;
    @BindView(R.id.head_title) TextView headTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setDefaultFragment();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.story:
                        FragmentUtil.getInstance().showFragment("story",MainActivity.this);
                        headTitle.setText("STORY");
                        break;
                    case  R.id.told:
                        FragmentUtil.getInstance().showFragment("told",MainActivity.this);
                        headTitle.setText("TOLD");
                        break;
                    case R.id.chat:
                        FragmentUtil.getInstance().showFragment("chat",MainActivity.this);
                        headTitle.setText("CHAT");
                        break;
                    case R.id.person:
                        FragmentUtil.getInstance().showFragment("person",MainActivity.this);
                        headTitle.setText("PERSON");
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    public void setDefaultFragment() {
        FragmentUtil.getInstance().showFragment("story",MainActivity.this);
    }




}
