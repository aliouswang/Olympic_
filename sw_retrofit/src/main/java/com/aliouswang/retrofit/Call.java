package com.aliouswang.retrofit;

import java.io.IOException;

/**
 * 1. execute 同步执行 可能会抛出异常，而异步执行没有，因为已经在回调中 帮调用者处理过了，这里算是一个小知识点
 *
 *
 * @param <T>
 */
public interface Call<T> extends Cloneable{

    Response<T> execute() throws IOException;

    void dequeue(Callback<T> callback);

    boolean isExecuted();

    void cancel();

    boolean isCanceled();

    Call<T> clone();

}
