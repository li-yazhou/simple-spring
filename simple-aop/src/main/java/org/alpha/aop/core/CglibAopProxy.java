package org.alpha.aop.core;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import org.alpha.aop.advisor.AdvisedSupport;
import org.alpha.aop.interceptor.AopMethodInterceptor;

import java.util.List;

/**
 * @author liyazhou1
 * @date 2019/12/15
 */
public class CglibAopProxy implements AopProxy {

    private AdvisedSupport advisedSupport;

    private Class<?>[] constructorArgTypes;

    private Object[] constructorArgs;


    public CglibAopProxy(AdvisedSupport advisedSupport){
        this.advisedSupport = advisedSupport;
    }


    public CglibAopProxy(AdvisedSupport advisedSupport,
                         Class<?>[] constructorArgTypes,
                         Object[] constructorArgs) {
        this.advisedSupport = advisedSupport;
        this.constructorArgTypes = constructorArgTypes;
        this.constructorArgs = constructorArgs;
    }


    @Override
    public Object getProxy() {
        return this.getProxy(null);
    }


    @Override
    public Object getProxy(ClassLoader loader) {

        if (loader == null) {
            loader = Thread.currentThread().getContextClassLoader();
        }

        Object target = advisedSupport.getTarget();
        List<AopMethodInterceptor> interceptorList = advisedSupport.getInterceptorList();

        Class<?> rootClass = target.getClass();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(rootClass.getSuperclass());

        // 添加拦截器方法
        Callback callback = new DynamicAdvisedInterceptor(target, interceptorList);
        enhancer.setCallback(callback);
        enhancer.setClassLoader(loader);

        if (constructorArgTypes != null && constructorArgTypes.length > 0
                && constructorArgs != null && constructorArgs.length > 0) {
            return enhancer.create(constructorArgTypes, constructorArgs);
        } else {
            return enhancer.create();
        }
    }
}
