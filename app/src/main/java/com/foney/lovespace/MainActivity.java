package com.foney.lovespace;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;

import com.foney.lovespace.activity.BaseActivity;
import com.foney.lovespace.util.FragmentUtil;


public class MainActivity extends BaseActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDefaultFragment();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.story:
                        FragmentUtil.getInstance().showFragment("story",MainActivity.this);
                        break;
                    case  R.id.told:
                        FragmentUtil.getInstance().showFragment("told",MainActivity.this);
                        break;
                    case R.id.chat:
                        FragmentUtil.getInstance().showFragment("chat",MainActivity.this);
                        break;
                    case R.id.person:
                        FragmentUtil.getInstance().showFragment("person",MainActivity.this);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    public void setDefaultFragment() {
        FragmentUtil.getInstance().showFragment("story",MainActivity.this);
    }




}
