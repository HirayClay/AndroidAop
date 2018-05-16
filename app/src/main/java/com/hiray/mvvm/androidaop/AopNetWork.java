package com.hiray.mvvm.androidaop;

import android.util.Log;

import com.hiray.mvvm.androidaop.broadcast.NetWorkUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by hiray on 2018/5/16.
 *
 * @author hiray
 */
@Aspect
public class AopNetWork {
    private static final String TAG = "AopNetWork";
    public static final String NETWORK_POINT_CUT = "execution(@com.hiray.mvvm.androidaop.AspectNetWork * *(..))";

    @Pointcut(NETWORK_POINT_CUT)
    public void networkMethod() {
    }

    @Around("networkMethod()")
    public Object intercept(ProceedingJoinPoint joinPoint) throws Throwable {
        Object r = null;
        if (NetWorkUtil.isConnected())
            r =joinPoint.proceed();
        else Log.i(TAG, "intercept: 网络未连接");

        return r;
    }
}
