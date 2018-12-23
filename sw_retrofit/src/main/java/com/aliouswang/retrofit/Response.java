package com.aliouswang.retrofit;

import android.support.annotation.Nullable;

import okhttp3.ResponseBody;

public final class Response<T> {

    private final @Nullable ResponseBody errorBody;
    private final @Nullable T body;
    private final okhttp3.Response rawResponse;

    public Response(okhttp3.Response rawResponse, @Nullable T body, @Nullable ResponseBody errorBody) {
        this.rawResponse = rawResponse;
        this.body = body;
        this.errorBody = errorBody;
    }

    public @Nullable T body() {
        return this.body;
    }

    public okhttp3.Response raw() {
        return this.raw();
    }

}
