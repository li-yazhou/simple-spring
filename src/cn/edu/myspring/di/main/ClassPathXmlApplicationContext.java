package cn.edu.myspring.di.main;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import cn.edu.myspring.di.config.Bean;
import cn.edu.myspring.di.config.Property;
import cn.edu.myspring.di.config.parse.ConfigManager;

/**  
 * 作用：
 *
 * @author:	liyazhou   
 * @version:2016年9月24日下午7:35:29 
 */
public class ClassPathXmlApplicationContext implements BeanFactory {
	
	
	Map<String, Object> context;
	Map<String, Bean> config;   // 配置文件信息
	
	public ClassPathXmlApplicationContext(String path) {
		super();
		// 0.初始化容器
		// 1.解析出配置文件的信息
		// 2.遍历配置信息初始化bean
		
		// 0. 初始化容器
		context = new HashMap<String, Object>();
		
		// 1.解析出配置文件的信息
		config = ConfigManager.getConfig(path);
		
		// 2.遍历配置信息初始化bean
		if(config!=null){
			for(Map.Entry<String, Bean> entry : config.entrySet()){
				String id = entry.getKey();
				Bean configBean = entry.getValue();
				// 根据bean配置，创建Bean对象
				Object objBean = createBean(configBean);
				// System.out.println(objBean);
				
				// 3.将初始化好的Bean对象放入到容器中
				// 只有 scope是 singleton类型时，才将对象放入到容器中
				if("singleton".equals(configBean.getScope())){
					context.put(id, objBean);
				}
			}
		}
		
	}

	// 根据bean的配置信息创建obj对象
	private Object createBean(Bean configBean){
		// 1.获取到Bean的字节码对象，创建Bean对象
		String className = configBean.getClassName();
		@SuppressWarnings("rawtypes")
		Class clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("请检查"+configBean.getId()+"的class配置信息.");
		}
		
		Object objBean = null;
		try {
			objBean = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("请检查该类"+ className +"是否有无参的构造方法.");
		}
		
		// 2.获取Bean的属性，将其注入到Bean对象中
		// 2.1 简单情况，value属性注入
		// 2.2 复杂情况，ref属性注入，需要获取到被引用的对象
		
		if(!configBean.getProperties().isEmpty()){
			for(Property prop : configBean.getProperties()){
				// 2.1 简单情况，value值的注入
				if(prop.getValue() != null){
					try {
						// BeanUtils.setProperty()方法可以实现对value值的类型转换
						BeanUtils.setProperty(objBean, prop.getName(), prop.getValue());
					} catch (IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
						throw new RuntimeException("请检查 "+ configBean.getId() + " 的配置信息.");
					}
				}
				
				// 2.2 复杂情况，ref值的注入，需要获取到被引用的对象
				if(prop.getRef() != null){
					String refId = prop.getRef();
					
					Object existRef = context.get(refId);
					if(existRef == null){
						existRef = createBean(config.get(refId));
						// 新创建对象后，决定是否将其放入到容器中（时机）
						// 只有当 scope属性值为 singleton时，才将Bean对象放入到容器中
						if("singleton".equals(config.get(refId).getScope())){
							context.put(refId, existRef);
						}
					}
					
					try {
						BeanUtils.setProperty(objBean, prop.getName(), existRef);
					} catch (IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
						throw new RuntimeException("请检查 "+ configBean.getId() + " 的配置信息.");
					}
					
				}
			}
		}
		
		return objBean;
	}
	
	// 根据id从容器中获取到 Bean对象
	@Override
	public Object getBean(String id) {
		Object objBean = context.get(id);
		// 假如 scope是 prototype类型，则容器中就不会存在该Bean对象
		if(objBean == null){
			objBean = createBean(config.get(id));
		}
		
		return objBean;
	}

}
   