package org.alpha.aop.core;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.alpha.aop.interceptor.AopMethodInterceptor;
import org.alpha.aop.invocation.CglibMethodInvocation;
import org.alpha.aop.invocation.MethodInvocation;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 实现 Cglib 的 MethodInterceptor 接口
 * @author liyazhou1
 * @date 2019/12/15
 */
public class DynamicAdvisedInterceptor implements MethodInterceptor {

    private Object target;

    private List<AopMethodInterceptor> interceptorList;

    public DynamicAdvisedInterceptor(Object target, List<AopMethodInterceptor> interceptorList) {
        this.target = target;
        this.interceptorList = interceptorList;
    }


    /**
     * 通过CGLIB的Interceptor接口为目标对象的方法添加拦截器中的增强方法
     */
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        MethodInvocation mi = new CglibMethodInvocation(proxy, target, method, args, interceptorList, methodProxy);
        return mi.proceed();
    }
}
