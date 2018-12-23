package com.aliouswang.retrofit;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Retrofit {

    //用了 concurrentHashMap, 线程安全，
    private final Map<Method, ServiceMethod<?>> serviceMethodMap = new ConcurrentHashMap<>();

    /**
     *
     * @param service 一开始我命名为clazz，不够准确
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> service) {
        return (T) Proxy.newProxyInstance(
                service.getClassLoader(),
                new Class[]{service},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //如果是Object 中的方法，直接调用
                        if (method.getDeclaringClass() == Object.class) {
                            method.invoke(this, args);
                        }
                        return loadServiceMethod(method).invoke(args);
                    }
                }
        );
    }

    private ServiceMethod<?> loadServiceMethod(Method method) {
        ServiceMethod result = serviceMethodMap.get(method);
        if (result != null) {
            return result;
        }

        synchronized (serviceMethodMap) {
            result = serviceMethodMap.get(method);
            if (result == null) {
                result = ServiceMethod.parseAnnotations(this, method);
            }
            serviceMethodMap.put(method, result);
        }
        return result;
    }

}
