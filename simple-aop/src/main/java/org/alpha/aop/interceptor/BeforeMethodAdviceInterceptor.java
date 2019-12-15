package org.alpha.aop.interceptor;

import org.alpha.aop.advisor.BeforeMethodAdvice;
import org.alpha.aop.invocation.MethodInvocation;

/**
 * 方法执行之前拦截
 *
 * @author liyazhou1
 * @date 2019/12/15
 */
public class BeforeMethodAdviceInterceptor implements AopMethodInterceptor {


    private BeforeMethodAdvice advice;


    public BeforeMethodAdviceInterceptor(BeforeMethodAdvice advice) {
        this.advice = advice;
    }


    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {

        advice.before(mi.getMethod(), mi.getArguments(), mi);

        return mi.proceed();
    }

}
