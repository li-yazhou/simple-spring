package org.alpha.aop.advisor.impl;

import org.alpha.aop.advisor.AfterMethodAdvice;
import org.alpha.aop.util.ThreadLocalUtil;

import java.lang.reflect.Method;

/**
 * 目标方法之后的增强代码
 *
 * @author liyazhou1
 * @date 2019/12/16
 */
public class AfterMethodTime implements AfterMethodAdvice {


    @Override
    public Object after(Object object, Method method, Object[] arguments, Object target) {

        long endTime = System.currentTimeMillis();
        System.out.println("结束计时，当前时间是 " + endTime);

        long startTime = ThreadLocalUtil.get();
        ThreadLocalUtil.remove();

        long elapsedTime = endTime - startTime;
        System.out.println("执行前后耗时 " + elapsedTime);

        return null;
    }
}
