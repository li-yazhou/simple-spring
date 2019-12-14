package org.alpha.ioc.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 作用：
 *
 * @author:	liyazhou
 * @version: 2016年9月24日下午7:33:45
 */
@Data
public class ObjC {

	private String name;

	private ObjB b;


	public ObjC(){
		super();
		System.out.println("创建一个C对象，" + LocalDateTime.now());
	}

}