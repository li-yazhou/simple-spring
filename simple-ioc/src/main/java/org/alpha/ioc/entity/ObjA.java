package org.alpha.ioc.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 作用：
 *
 * @author:	liyazhou
 * @version:2016年9月24日下午7:33:31
 */
@Data
public class ObjA {

	private String name;

	private Integer age;

	public ObjA() {
		super();
		System.out.println("创建一个A对象，" + LocalDateTime.now());
	}


}