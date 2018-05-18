package com.hiray.mvvm.androidaop;

import android.util.Log;

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
public class AopLog {

    public static final String POINT_CUT = "execution(@com.hiray.mvvm.androidaop.AspectLog * *(..))";

    @Pointcut(POINT_CUT)
    public void methodWithLog() {

    }

    @Around("methodWithLog()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AspectLog annotation = signature.getMethod().getAnnotation(AspectLog.class);
        Log.i("AspectLog", signature.getName() + "============="+annotation.value());
        return joinPoint.proceed();
    }
}
