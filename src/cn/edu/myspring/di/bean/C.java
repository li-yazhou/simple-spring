package cn.edu.myspring.di.bean; 
  
/**  
 * 作用：
 *
 * @author:	liyazhou   
 * @version:2016年9月24日下午7:33:45 
 */
public class C {
	private String name;
	private B b;
	
	public C(){
		super();
		System.out.println("创建一个C对象.");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}

	@Override
	public String toString(){
		return "C["+ name + ", " + b +"]";
	}
}
   