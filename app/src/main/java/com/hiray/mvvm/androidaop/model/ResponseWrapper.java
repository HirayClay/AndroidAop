package com.hiray.mvvm.androidaop.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hiray on 2018/5/16.
 *
 * @author hiray
 */
public class ResponseWrapper<T> {

    @SerializedName("results")
    public T data;

    @SerializedName("error")
    boolean err;

    public boolean isOk() {
        return !err;
    }
}
