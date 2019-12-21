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


    /**
     * 先执行增强代码，后执行原始的方法
     *
     * @param mi
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {

        // TODO
        advice.before(mi.getMethod(), mi.getArguments(), mi);

        return mi.proceed();
    }

}
