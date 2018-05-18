package com.hiray.mvvm.androidaop;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.hiray.mvvm.androidaop.PermissionRequestActivity.PermissionCallBack;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * Created by hiray on 2018/5/16.
 *
 * @author hiray
 */
@Aspect
public class AopPermission {

    private static final String TAG = "AopPermission";
    public static final String PERMISSION_CUT = "execution(@com.hiray.mvvm.androidaop.AspectPermission * *(..))";

    @Pointcut(PERMISSION_CUT)
    public void methodPermission() {

    }

    @Around("methodPermission()")
    public void intercept(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Activity context = (Activity) joinPoint.getThis();
        AspectPermission perAnnotation = method.getAnnotation(AspectPermission.class);
        String permission = perAnnotation.value();
        int granted = ContextCompat.checkSelfPermission(context, permission);
        if (granted != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "intercept: AAAAAAAAAAAAAA");
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, permission)) {
                Log.i(TAG, "intercept: BBBBBBBBBBBBBBBB");

                AlertDialog alertDialog = new AlertDialog.Builder(context)
                        .setTitle("权限申请")
                        .setMessage("需要拍照，权限爱给不给，自己去设置里面开启")
                        .create();
                alertDialog.show();
            } else {
                Log.i(TAG, "intercept: CCCCCCCCCCCCCC");

                PermissionRequestActivity.requestPermissions(context, new String[]{permission}, 1234, new PermissionCallBack() {

                    @Override
                    public void onPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                        if (requestCode == 1234 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            try {
                                joinPoint.proceed();
                            } catch (Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        }
                    }
                });
            }
        } else joinPoint.proceed();


    }

}
