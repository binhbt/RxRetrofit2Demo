package com.example.binhbt.rxjavaretrofitdemo.ui.fragment;

import android.app.Fragment;
import android.widget.Toast;

/**
 * Created by binhbt on 6/8/2016.
 */
public class BaseFragment extends Fragment {
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
