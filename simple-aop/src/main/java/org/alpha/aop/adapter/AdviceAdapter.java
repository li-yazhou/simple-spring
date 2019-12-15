package org.alpha.aop.adapter;

import org.alpha.aop.advisor.Advisor;
import org.alpha.aop.interceptor.AopMethodInterceptor;

/**
 * 适配器，将 Advisor 转换为 AopInterceptor，也即是将不同的Advice创建出对应的Interceptor
 *
 * @author liyazhou1
 * @date 2019/12/15
 */
public interface AdviceAdapter {

    AopMethodInterceptor getInterceptor(Advisor advisor);

}
