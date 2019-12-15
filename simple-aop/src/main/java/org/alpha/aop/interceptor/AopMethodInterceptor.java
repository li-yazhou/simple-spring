package org.alpha.aop.interceptor;


import org.alpha.aop.invocation.MethodInvocation;

/**
 * 拦截器，所有的方法拦截器均需要实现该接口
 *
 * 不同的拦截器中，需要注入不同的Advice，以对目标方法增强
 *
 * 在具体的拦截器invoke代码中，在调用原始的方法前后执行Advice方法
 *
 * @author liyazhou1
 * @date 2019/12/15
 */
public interface AopMethodInterceptor {

    Object invoke(MethodInvocation methodInvocation) throws Throwable;

}
