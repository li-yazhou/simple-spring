package org.alpha.aop.invocation;

import net.sf.cglib.proxy.MethodProxy;
import org.alpha.aop.interceptor.AopMethodInterceptor;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author liyazhou1
 * @date 2019/12/15
 */
public class CglibMethodInvocation extends ReflectionMethodInvocation {

    private MethodProxy methodProxy;

    public CglibMethodInvocation(Object proxy,
                                 Object target,
                                 Method method,
                                 Object[] arguments,
                                 List<AopMethodInterceptor> interceptorList,
                                 MethodProxy methodProxy) {
        super(proxy, target, method, arguments, interceptorList);
        this.methodProxy = methodProxy;
    }


    /**
     * 通过CGLIB调用原生的方法
     */
    @Override
    protected Object invokeOriginalMethod() throws Throwable {
       return methodProxy.invoke(super.getTarget(), super.getArguments());
    }

}
