package org.alpha.aop.advisor.impl;

import org.alpha.aop.advisor.BeforeMethodAdvice;
import org.alpha.aop.util.ThreadLocalUtil;

import java.lang.reflect.Method;

/**
 * 目标方法之前的增强代码
 *
 * @author liyazhou1
 * @date 2019/12/16
 */
public class BeforeMethodTime implements BeforeMethodAdvice {

    @Override
    public Object before(Method method, Object[] arguments, Object target) {

        long startTime = System.currentTimeMillis();

        ThreadLocalUtil.set(startTime);

        System.out.println("开始计时，当前时间为 " + startTime);

        return null;
    }

}
