package com.hiray.mvvm.androidaop.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hiray on 2018/5/16.
 *
 * @author hiray
 */
public class RestApiHelper {

    private static  RestApi restApi = null;

    public RestApiHelper() {

    }

    public static RestApi restApi() {
        if (restApi == null) {
            OkHttpClient okHttpClient = new OkHttpClient();

            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://www.gank.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            restApi = retrofit.create(RestApi.class);
        }
        return restApi;
    }
}
