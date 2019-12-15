package org.alpha.aop.advisor;

import java.lang.reflect.Method;

/**
 * Advice 描述做什么事情
 *
 * AfterMethodAdvice 描述做 after 操作，根据需要提供该接口的实现
 *
 * @author liyazhou1
 * @date 2019/12/15
 */
public interface AfterMethodAdvice extends Advice {

    Object after(Method method, Object[] arguments, Object target);

}
