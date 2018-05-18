package com.hiray.mvvm.androidaop.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by hiray on 2018/5/16.
 *
 * @author hiray
 */
public class NetWorkUtil extends BroadcastReceiver {

    private static final String TAG = "NetUtils";

    private static BroadcastReceiver receiver = new NetWorkUtil();
    private static boolean isConnected = false;
    private static Context context;

    public static void register(Context ctx) {
        initNetWorkState(ctx);
        context = ctx;
    }

    /**
     * 初始化当前网络状态
     *
     * @param context
     */
    private static void initNetWorkState(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
        Log.i(TAG, "initNetWorkState: "+isConnected);
    }

    /**
     * 取消注册
     */
    public void unregister() {
        if (context != null) {
            context.unregisterReceiver(receiver);
        }
    }


    /**
     * 接收网络状态改变广播
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = mobile.isConnected() || wifi.isConnected();

    }


    public static boolean isConnected() {
        return isConnected;
    }
}
