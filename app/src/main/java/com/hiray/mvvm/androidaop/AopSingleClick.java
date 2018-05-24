package com.hiray.mvvm.androidaop;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by hiray on 2018/5/23.
 *
 * @author hiray
 */
@Aspect
public class AopSingleClick {

    private static final String TAG = "AopSingleClick";
    private static long lastClickTime = 0;
    public static final String POINT_CUT = "execution(@com.hiray.mvvm.androidaop.AspectSingleClick * *(..))";

    @Pointcut(POINT_CUT)
    public void methodSingleClick() {

    }

    @Around("methodSingleClick()")
    public Object processSingleClick(ProceedingJoinPoint joinPoint) {
        Log.i(TAG, "singleClick: -----------------------");
        long currTime = System.currentTimeMillis();
        if (currTime - lastClickTime > 750) {
            try {
                Log.i(TAG, "singleClick: 点击成功！");
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        } else {
            lastClickTime = currTime;
            Log.i(TAG, "点击事件间隔太短！");
        }

        return null;
    }
}
