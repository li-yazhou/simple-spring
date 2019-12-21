package org.alpha.aop.util;

/**
 * 线程本地变量工具
 *
 * @author liyazhou1
 * @date 2019/12/16
 */
public class ThreadLocalUtil {

    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static long get() {
        return threadLocal.get();
    }

    public static void set(Long value) {
        threadLocal.set(value);
    }

    public static void remove() {
        threadLocal.remove();
    }
}
