package com.example.binhbt.rxjavaretrofitdemo.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

/**
 * Created by binhbt on 6/8/2016.
 */
public class BaseActivity extends Activity{
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }
}
