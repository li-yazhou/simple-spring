package org.alpha.aop.invocation;

import java.lang.reflect.Method;

/**
 * 描述一般方法的"调用"
 *
 * Invocation 描述一个方法的调用，而非调用方法真正的执行动作
 *
 * 在具体的拦截器中调用原始的方法以及未来增强的代码
 *
 * @author liyazhou1
 * @date 2019/12/15
 */
public interface MethodInvocation {

    /**
     * 方法对象
     */
    Method getMethod();

    /**
     * 方法参数
     */
    Object[] getArguments();

    /**
     * 执行方法
     */
    Object proceed() throws Throwable;

}
