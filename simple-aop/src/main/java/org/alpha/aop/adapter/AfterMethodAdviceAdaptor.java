package org.alpha.aop.adapter;

import org.alpha.aop.advisor.Advisor;
import org.alpha.aop.advisor.AfterMethodAdvice;
import org.alpha.aop.interceptor.AfterMethodAdviceInterceptor;
import org.alpha.aop.interceptor.AopMethodInterceptor;

/**
 *
 *
 * @author liyazhou1
 * @date 2019/12/15
 */
public class AfterMethodAdviceAdaptor implements AdviceAdapter {

    /**
     * 单例模式，避免创建多个对象
     */
    private AfterMethodAdviceAdaptor() { }

    private static final AfterMethodAdviceAdaptor INSTANCE = new AfterMethodAdviceAdaptor();

    public static AfterMethodAdviceAdaptor getInstance() {
        return INSTANCE;
    }


    @Override
    public AopMethodInterceptor getInterceptor(Advisor advisor) {
        AfterMethodAdvice advice = (AfterMethodAdvice) advisor.getAdvice();
        return new AfterMethodAdviceInterceptor(advice);
    }

}
