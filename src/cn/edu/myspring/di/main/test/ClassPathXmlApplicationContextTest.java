package cn.edu.myspring.di.main.test;

import org.junit.Before;
import org.junit.Test;

import cn.edu.myspring.di.bean.B;
import cn.edu.myspring.di.bean.C;
import cn.edu.myspring.di.main.BeanFactory;
import cn.edu.myspring.di.main.ClassPathXmlApplicationContext;

/**  
 * 作用：
 *
 * @author:	liyazhou   
 * @version:2016年9月26日上午9:57:27 
 */
public class ClassPathXmlApplicationContextTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_01() {
		String path = "/applicationContext.xml";
		BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);
		
		System.out.println("-------------------");
		B b = (B) beanFactory.getBean("B");
		System.out.println(b.getObjA());
	}
	
	
	@Test
	public void test_02() {
		String path = "/applicationContext.xml";
		BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);
		
		System.out.println("-------------------");
		C c = (C) beanFactory.getBean("C");
		System.out.println(c.getB().getObjA().getName());
	}

	@Test
	public void test_03(){
		String path = "/applicationContext.xml";
		BeanFactory bf = new ClassPathXmlApplicationContext(path);
		
		System.out.println("-------------------");
		C c1 = (C)bf.getBean("C");
		C c2 = (C)bf.getBean("C");
		System.out.println("singleton,c1==c2?" + (c1 == c2));
		
		B b1 = (B)bf.getBean("B");
		B b2 = (B)bf.getBean("B");
		System.out.println("prototype,b1==b2?"+(b1 == b2));
	}
}
   