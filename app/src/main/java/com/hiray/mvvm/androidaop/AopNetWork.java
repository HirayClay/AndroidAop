package com.hiray.mvvm.androidaop;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.hiray.mvvm.androidaop.broadcast.NetWorkUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

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
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Log.i(TAG, "intercept: ===" + signature.getName());
        Object r = null;
        if (NetWorkUtil.isConnected()) {
            r = joinPoint.proceed();
            Log.i(TAG, "intercept: ---网络请求发出");
        } else {
            Object ev = joinPoint.getThis();
            if (ev instanceof Activity)
                Toast.makeText((Context) ev, "网络未连接", Toast.LENGTH_SHORT).show();
            else if (ev instanceof Fragment)
                Toast.makeText(((Fragment) ev).getActivity(), "网络未连接", Toast.LENGTH_SHORT).show();
        }

        return r;
    }
}
