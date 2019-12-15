package org.alpha.aop.core;

/**
 *
 * @author liyazhou1
 * @date 2019/12/15
 */
public interface AopProxy {

    Object getProxy();

    Object getProxy(ClassLoader loader);

}
