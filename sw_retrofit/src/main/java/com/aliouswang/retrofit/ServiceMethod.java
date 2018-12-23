package com.aliouswang.retrofit;

import java.lang.reflect.Method;

/**
 * 包级别的访问控制的抽象类，
 * @param <T>
 */
abstract class ServiceMethod<T> {

    public static ServiceMethod parseAnnotations(Retrofit retrofit, Method method) {

    }

    abstract T invoke(Object[] args);

}
