package org.alpha.aop.interceptor;

import org.alpha.aop.advisor.AfterMethodAdvice;
import org.alpha.aop.invocation.MethodInvocation;

/**
 * 方法执行之后拦截
 *
 * @author liyazhou1
 * @date 2019/12/15
 */
public class AfterMethodAdviceInterceptor implements AopMethodInterceptor {


    private AfterMethodAdvice advice;


    public AfterMethodAdviceInterceptor(AfterMethodAdvice advice){
        this.advice = advice;
    }


    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {

        Object obj = mi.proceed();

        advice.after(mi.getMethod(), mi.getArguments(), mi);

        return obj;
    }

}
