package com.foney.lovespace.util;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.foney.lovespace.R;
import com.foney.lovespace.homefragment.LoveChatFragment;
import com.foney.lovespace.homefragment.LovePersonFragment;
import com.foney.lovespace.homefragment.LoveStoryFragment;
import com.foney.lovespace.homefragment.LoveToldFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by foney on 2017/8/14.
 */

public class FragmentUtil {

    private List<Fragment> fragments = new ArrayList<Fragment>();
    private FragmentManager fm;
    private FragmentTransaction transaction;
    private Fragment storyFragment;
    private Fragment toldFragment;
    private Fragment chatFragment;
    private Fragment personFragment;

    private FragmentUtil fragmentUtil;
    private FragmentUtil() {

    }
    public static FragmentUtil getInstance() {
        return new FragmentUtil();
    }

    public void initFragment() {
        storyFragment = new LoveStoryFragment();
        toldFragment = new LoveToldFragment();
        chatFragment = new LoveChatFragment();
        personFragment = new LovePersonFragment();
        fragments.add(storyFragment);
        fragments.add(toldFragment);
        fragments.add(chatFragment);
        fragments.add(personFragment);
    }

    public void showFragment(String fragment,AppCompatActivity activity) {
        if(fragments == null || fragments.size() == 0) {
            initFragment();
        }
        if(fm == null) {
            fm = activity.getFragmentManager();
        }
        if(transaction == null) {
            transaction = fm.beginTransaction();
        }
        switch (fragment) {
            case "story":
                transaction.replace(R.id.id_content,storyFragment);
                break;
            case "told":
                transaction.replace(R.id.id_content,toldFragment);
                break;
            case "chat":
                transaction.replace(R.id.id_content,chatFragment);
                break;
            case "person":
                transaction.replace(R.id.id_content,personFragment);
                break;
            default:
                break;
        }
        transaction.commit();
    }

}
