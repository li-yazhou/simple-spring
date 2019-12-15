package org.alpha.aop.adapter;

import org.alpha.aop.advisor.Advisor;
import org.alpha.aop.advisor.BeforeMethodAdvice;
import org.alpha.aop.interceptor.AopMethodInterceptor;
import org.alpha.aop.interceptor.BeforeMethodAdviceInterceptor;

/**
 * 将 Advice 转换为 AopMethodInterceptor
 *
 * @author liyazhou1
 * @date 2019/12/15
 */
public class BeforeMethodAdviceAdaptor implements AdviceAdapter {

    /**
     * 单例模式，避免创建多个对象
     */
    private BeforeMethodAdviceAdaptor(){ }

    private static final BeforeMethodAdviceAdaptor INSTANCE = new BeforeMethodAdviceAdaptor();

    public static BeforeMethodAdviceAdaptor getInstance() {
        return INSTANCE;
    }


    @Override
    public AopMethodInterceptor getInterceptor(Advisor advisor) {
        BeforeMethodAdvice advice = (BeforeMethodAdvice) advisor.getAdvice();
        return new BeforeMethodAdviceInterceptor(advice);
    }

}
