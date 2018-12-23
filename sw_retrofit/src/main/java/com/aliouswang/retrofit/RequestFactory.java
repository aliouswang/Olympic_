package com.aliouswang.retrofit;

import android.support.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.regex.Pattern;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;

final class RequestFactory {

    private final Method method;
    private final HttpUrl baseUrl;
    final String httpMethod;
    private final @Nullable String relativeUrl;
    private final @Nullable Headers headers;
    private final @Nullable MediaType contentType;

    private final boolean hasBody;
    private final boolean isFormEncoded;


    static final class Builder {
        // Upper and lower characters, digits, underscores, and hyphens, starting with a character.
        private static final String PARAM = "[a-zA-Z][a-zA-Z0-9_-]*";
        private static final Pattern PARAM_URL_REGEX = Pattern.compile("\\{(" + PARAM + ")\\}");
        private static final Pattern PARAM_NAME_REGEX = Pattern.compile(PARAM);

        final Retrofit retrofit;
        final Method method;
        final Annotation[] methodAnnotations;
        final Annotation[][] parameterAnnotationsArray;
        final Type[] parameterTypes;

        boolean gotFiled;
        boolean gotPart;
        boolean gotBody;
        boolean gotPath;
        boolean gotQuery;
        boolean gotQueryName;
        boolean gotQueryMap;
        boolean gotUrl;
        @Nullable String httpMethod;
        boolean hasBody;
        boolean isFormEncoded;
        boolean isMultipart;
        @Nullable String relativeUrl;
        @Nullable Headers headers;
        @Nullable MediaType contentType;
        @Nullable Set<String> relativeUrlParamNames;

        Builder(Retrofit retrofit, Method method) {
            this.retrofit = retrofit;
            this.method = method;
            this.methodAnnotations = method.getAnnotations();
            this.parameterAnnotationsArray = method.getParameterAnnotations();
            this.parameterTypes = method.getParameterTypes();
        }



    }

















































}
