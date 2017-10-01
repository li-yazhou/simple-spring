package cn.edu.myspring.di.bean; 
  
/**  
 * 作用：
 *
 * @author:	liyazhou   
 * @version:2016年9月24日下午7:33:31 
 */
public class A {
	private String name;
	private Integer age;
	
	public A() {
		super();
		System.out.println("创建一个A对象.");
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "A [name=" + name + ", age=" + age + "]";
	}
	
}
   