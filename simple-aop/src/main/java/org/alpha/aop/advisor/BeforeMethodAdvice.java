package org.alpha.aop.advisor;

import java.lang.reflect.Method;

/**
 * Advice 描述做什么事情
 *
 * BeforeMethodAdvice 描述做 before 操作
 *
 * @author liyazhou1
 * @date 2019/12/15
 */
public interface BeforeMethodAdvice extends Advice {

    Object before(Method method, Object[] arguments, Object target);

}
