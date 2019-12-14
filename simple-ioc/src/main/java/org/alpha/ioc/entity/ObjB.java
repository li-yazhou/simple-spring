package org.alpha.ioc.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 作用：
 *
 * @author:	liyazhou
 * @version:2016年9月24日下午7:33:38
 */
@Data
public class ObjB {

	private String name;

	private ObjA objA;


	public ObjB(){
		super();
		System.out.println("创建一个B对象，" + LocalDateTime.now());
	}
}