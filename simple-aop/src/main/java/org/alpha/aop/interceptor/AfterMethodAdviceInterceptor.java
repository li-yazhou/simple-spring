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

    /**
     * 注入拦截器对应的Advice
     */
    public AfterMethodAdviceInterceptor(AfterMethodAdvice advice){
        this.advice = advice;
    }


    /**
     * 先执行原始方法，然后执行增强的代码
     *
     * @param mi 方法调用描述对象
     * @return 方法的返回结果
     */
    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {

        // 调用本次增强之前的方法
        // TODO 参数
        Object obj = mi.proceed();

        // 调用本拦截器增强代码
        // TODO 参数
        advice.after(obj, mi.getMethod(), mi.getArguments(), mi);

        return obj;
    }

}
