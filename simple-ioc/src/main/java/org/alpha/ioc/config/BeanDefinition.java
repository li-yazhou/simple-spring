package org.alpha.ioc.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 对配置文件的抽象
 *
 * <pre>
 * {@code
 *      <bean id="A" class="org.alpha.ioc.xml.entity.ObjA" scope="singleton">
 *  		<property name="name" value="A对象"></property>
 *  		<property name="age" value="22"></property>
 *   	</bean>
 * }
 * </pre>
 *
 * @author:	liyazhou
 * @version: 2016年9月24日下午7:34:01
 */
@Getter
@ToString
@NoArgsConstructor
public class BeanDefinition {

    @Setter
	private String id;

	@Setter
	private String className;

	/**
	 * 	只考虑 singleton和prototype两种类型
 	 */
    @Setter
	private String scope;


    public BeanDefinition(String id, String className, String scope) {
        this.id = id;
        this.className = className;
        this.scope = scope;
    }

	private List<BeanProperty> properties = new ArrayList<>();

	public void addProperty(BeanProperty property) {
	    this.properties.add(property);
    }

}