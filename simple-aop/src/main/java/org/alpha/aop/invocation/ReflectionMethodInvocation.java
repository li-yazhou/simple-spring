package org.alpha.aop.invocation;

import lombok.Getter;
import org.alpha.aop.interceptor.AopMethodInterceptor;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author liyazhou1
 * @date 2019/12/15
 */
public class ReflectionMethodInvocation implements ProxyMethodInvocation {


    private final Object proxy;

    @Getter
    private final Object target;

    private final Method method;

    private Object[] arguments;

    private final List<AopMethodInterceptor> interceptorList;

    private int currentInterceptorIndex = 0;


    public ReflectionMethodInvocation(Object proxy,
                                      Object target,
                                      Method method,
                                      Object[] arguments,
                                      List<AopMethodInterceptor> interceptorList) {
        this.proxy = proxy;
        this.target = target;
        this.method = method;
        this.arguments = arguments;
        this.interceptorList = interceptorList;
    }


    @Override
    public Object getProxy() {
        return proxy;
    }


    @Override
    public Method getMethod() {
        return method;
    }


    @Override
    public Object[] getArguments() {
        return arguments;
    }


    @Override
    public Object proceed() throws Throwable {

        if (currentInterceptorIndex < interceptorList.size()) {
            // 执行拦截器增强后的方法
            AopMethodInterceptor interceptor = interceptorList.get(currentInterceptorIndex ++);
            return interceptor.invoke(this);
        } else {
            // 没有拦截器时执行目标方法
            return invokeOriginalMethod();
        }
    }


    /**
     * 通过反射调用原生的方法
     */
    protected Object invokeOriginalMethod() throws Throwable {
        method.setAccessible(true);
        return method.invoke(target, arguments);
    }

}
