package org.alpha.aop.invocation;

/**
 * 描述代理方法的"调用"
 *
 * @author liyazhou1
 * @date 2019/12/15
 */
public interface ProxyMethodInvocation extends MethodInvocation {

    /**
     * 获取代理对象
     */
    Object getProxy();

}
