package com.hiray.mvvm.androidaop.network;

import com.hiray.mvvm.androidaop.model.AndroidItemModel;
import com.hiray.mvvm.androidaop.model.ResponseWrapper;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by hiray on 2018/5/16.
 *
 * @author hiray
 */
public interface RestApi {

    @GET
    Observable<ResponseWrapper<List<AndroidItemModel>>> getAndroidProjects(@Url String url);
}
