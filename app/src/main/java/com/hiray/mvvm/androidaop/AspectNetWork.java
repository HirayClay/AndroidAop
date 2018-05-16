package com.hiray.mvvm.androidaop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hiray on 2018/5/16.
 * 拦截网络请求的入口，并且统一给出网络问题提示
 * @author hiray
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD})
public @interface AspectNetWork {

}
