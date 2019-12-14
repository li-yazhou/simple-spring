package org.alpha.ioc.context;

import org.alpha.ioc.entity.ObjB;
import org.alpha.ioc.entity.ObjC;
import org.alpha.ioc.entity.ObjD;
import org.alpha.ioc.entity.ObjE;
import org.junit.Before;
import org.junit.Test;

public class ClassPathXmlApplicationContextTest {

    private BeanFactory beanFactory;


	@Before
	public void setUp() throws Exception {
        String path = "./applicationContext.xml";
        beanFactory = new ClassPathXmlApplicationContext(path);
	}

	@Test
	public void test_01() throws Exception {
		ObjB b = (ObjB) beanFactory.getBean("B");
		System.out.println(b);
	}
	
	
	@Test
	public void test_02() throws Exception {
		ObjC c = (ObjC) beanFactory.getBean("C");
		System.out.println(c);
	}


	@Test
	public void test_03() throws Exception {
		ObjC c1 = (ObjC)beanFactory.getBean("C");
		ObjC c2 = (ObjC)beanFactory.getBean("C");
		System.out.println("singleton, c1 == c2 ? " + (c1 == c2));
		
		ObjB b1 = (ObjB)beanFactory.getBean("B");
		ObjB b2 = (ObjB)beanFactory.getBean("B");
		System.out.println("prototype, b1 == b2 ? "+(b1 == b2));
	}

	@Test
	public void test_04() throws Exception {
		ObjD objD = (ObjD) beanFactory.getBean("D");
		ObjE objE = (ObjE) beanFactory.getBean("E");
		System.out.println(objD);
		System.out.println(objE);
	}
}
   