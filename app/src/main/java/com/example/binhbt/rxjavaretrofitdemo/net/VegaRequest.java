package com.example.binhbt.rxjavaretrofitdemo.net;


import retrofit2.Callback;

/**
 * Created by binhbt on 6/8/2016.
 */
public abstract class VegaRequest <T> implements Callback<T> {
    public VegaRequest(){
        onStart();
    }
    public abstract void onStart();
}
