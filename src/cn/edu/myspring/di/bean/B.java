package cn.edu.myspring.di.bean; 
  
/**  
 * 作用：
 *
 * @author:	liyazhou   
 * @version:2016年9月24日下午7:33:38 
 */
public class B {
	private String name;
	private A objA;
	
	public B(){
		super();
		System.out.println("创建一个B对象.");
	}
	
	public void setObjA(A objA){
		this.objA = objA;
	}
	
	public A getObjA(){
		return this.objA;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "B [name=" + name + ", objA=" + objA + "]";
	}
}
   