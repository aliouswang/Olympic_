package com.aliouswang.retrofit;

import android.support.annotation.Nullable;

import com.aliouswang.retrofit.annotations.GET;
import com.aliouswang.retrofit.annotations.POST;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
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
    private final boolean isMultipart;

    RequestFactory(Builder builder) {
        this.method = builder.method;
        this.baseUrl = null;
        this.httpMethod = builder.httpMethod;
        this.relativeUrl = builder.relativeUrl;
        this.headers = builder.headers;
        this.contentType = builder.contentType;

        this.hasBody = builder.hasBody;
        this.isFormEncoded = builder.isFormEncoded;
        this.isMultipart = builder.isMultipart;
    }


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

        RequestFactory build() {
            for (Annotation annotation : methodAnnotations) {
                parseMethodAnnotation(annotation);
            }

            int parmeterCount = parameterAnnotationsArray.length;

        }

        private void parseMethodAnnotation(Annotation annotation) {
            if (annotation instanceof GET) {
                parseHttpMethodsAndPath("GET", ((GET) annotation).value(), false);
            }else if (annotation instanceof POST) {
                parseHttpMethodsAndPath("POST", ((POST) annotation).value(), true);
            }
        }

        private void parseHttpMethodsAndPath(String httpMethod, String value, boolean hasBody) {
            if (this.httpMethod != null) {
                throw Utils.methodError(method, "Only one HTTP method is allowed. Found: %s and %s."
                , this.httpMethod, httpMethod);
            }
            this.httpMethod = httpMethod;
            this.hasBody = hasBody;

            if (value.isEmpty()) {
                return;
            }

            this.relativeUrl = value;
            this.relativeUrlParamNames = parsePathParameters(value);
        }


        static Set<String> parsePathParameters(String path) {
            Matcher m = PARAM_URL_REGEX.matcher(path);
            Set<String> patterns = new LinkedHashSet<>();
            while (m.find()) {
                patterns.add(m.group(1));
            }
            return patterns;
        }
    }

















































}
