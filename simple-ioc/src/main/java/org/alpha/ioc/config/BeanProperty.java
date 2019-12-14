package org.alpha.ioc.config;


import lombok.Builder;
import lombok.Data;

/**
 * 	<bean id="B" class="org.alpha.ioc.xml.entity.ObjB" scope="prototype">
 * 		<property name="name" value="B对象"></property>
 * 		<property name="objA" ref="A"></property>
 * 	</bean>
 */
@Data
@Builder
public class BeanProperty {

	private String name;

	private String value;

	private String ref;

}
   