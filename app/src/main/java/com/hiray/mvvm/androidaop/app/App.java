package com.hiray.mvvm.androidaop.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.hiray.mvvm.androidaop.broadcast.NetWorkUtil;

/**
 * Created by hiray on 2018/5/16.
 *
 * @author hiray
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NetWorkUtil.register(this);
        //if activity lifecycle is your concern
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }
}
