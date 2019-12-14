
package org.alpha.ioc.context;

/**
 *
 * @author:	liyazhou
 * @version: 2016年9月24日下午7:34:52
 */
public interface BeanFactory {

	/**
	 * 根据bean的id获取到Bean对象
	 */
	Object getBean(String id) throws Exception;

}