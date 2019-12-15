package org.alpha.aop.core;


import org.alpha.ioc.config.BeanDefinition;
import org.alpha.ioc.config.BeanProperty;
import org.alpha.ioc.config.ConfigManager;
import org.alpha.ioc.context.BeanFactory;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 *
 * @author liyazhou1
 * @date 2019/12/15
 */
public class AopClassPathXmlApplicationContext implements BeanFactory {


    private Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>();

    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    private Map<String, BeanDefinition> beanConfig;


    /**
     * 初始化容器并实例化对象
     */
    public AopClassPathXmlApplicationContext(String path) throws Exception {

        // 解析出配置文件的信息
        beanConfig = ConfigManager.loadConfig(path);

        // 遍历配置信息初始化bean
        if(beanConfig != null){
            for(Map.Entry<String, BeanDefinition> entry : beanConfig.entrySet()){
                String id = entry.getKey();
                BeanDefinition beanDef = entry.getValue();

                Object object = createBean(beanDef);

                // 将初始化好的Bean对象放入到容器中，只有 scope是 singleton类型时，才将对象放入到容器中
                if("singleton".equalsIgnoreCase(beanDef.getScope())){
                    // object已完成实例化和初始化，从二级缓存中删除，放到一级缓存
                    singletonObjects.putIfAbsent(id, object);
                }
            }
        }
    }


    /**
     * 根据bean的配置信息创建obj对象
     */
    private Object createBean(BeanDefinition beanDef) throws Exception {

        // 实例化和初始化完成的对象，直接返回
        Object object = singletonObjects.get(beanDef.getId());
        if (object != null) {
            return object;
        }

        // 提前返回实例化的对象，若其他的对象依赖它时可以注入
        object = earlySingletonObjects.get(beanDef.getId());
        if (object != null) {
            return object;
        }

        // 实例化对象，只有当 scope属性值为 singleton时，才将Bean对象放入到容器中
        object = Class.forName(beanDef.getClassName()).newInstance();
        if ("singleton".equals(beanDef.getScope())) {
            earlySingletonObjects.put(beanDef.getId(), object);
        }

        // 获取Bean的属性，将属性值或者依赖的对象注入到Bean对象中
        if(!beanDef.getProperties().isEmpty()){
            for(BeanProperty prop : beanDef.getProperties()){
                // 简单情况，属性值的注入
                if(prop.getValue() != null){
                    BeanUtils.setProperty(object, prop.getName(), prop.getValue());
                }

                // 复杂情况，依赖对象的注入，需要获取到被引用的对象
                if(prop.getRef() != null){
                    String refId = prop.getRef();

                    // 一级缓存中不存在，则到二级缓存中查找对象
                    Object existRef = singletonObjects.get(refId);
                    if(existRef == null){

                        // 若二级缓存中该对象，则创建对象
                        existRef = earlySingletonObjects.get(refId);
                        if (existRef == null) {
                            // 递归地创建对象
                            existRef = createBean(beanConfig.get(refId));
                        }
                    }

                    BeanUtils.setProperty(object, prop.getName(), existRef);
                }
            }
        }

        // object已完成实例化和初始化，从二级缓存中删除，放到一级缓存
        if("singleton".equalsIgnoreCase(beanDef.getScope())){
            earlySingletonObjects.remove(beanDef.getId(), object);
        }
        return object;
    }



    @Override
    public Object getBean(String id) throws Exception {
        // 获取单例对象
        Object object = singletonObjects.get(id);
        // 创建原生对象
        if (object == null) {
            object = createBean(beanConfig.get(id));
        }
        return object;
    }
}
