package cn.edu.myspring.di.config.parse;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.edu.myspring.di.config.Bean;
import cn.edu.myspring.di.config.Property;

/**  
 * 作用：
 *
 * @author:	liyazhou   
 * @version:2016年9月24日下午7:34:36 
 */
public class ConfigManager {
	
	public static Map<String, Bean> getConfig(String path){
		// 1. 创建返回对象，Map对象
		// 2. dom4j解析applicationContext.xml配置文件
			// 2.1  创建解析器
		  	// 2.2 加载配置文件，document对象
		   	// 2.3 定义XPath表达式，取出所有的Bean元素
			// 2.4 对Bean元素进行遍历
		// 3.返回 Map对象
		
		// 1. 创建返回对象，Map对象
		Map<String, Bean> map = new HashMap<String, Bean>();
		
		// 2. dom4j解析applicationContext.xml配置文件
		// 2.1  创建解析器
		SAXReader reader = new SAXReader();
		
		// 2.2 加载配置文件，document对象
		InputStream in = ConfigManager.class.getResourceAsStream(path);
		Document doc = null;
		try {
			doc = reader.read(in);
		} catch (DocumentException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException("读取配置文件出现异常！");
		}
		
		// 2.3 定义XPath表达式，取出所有的Bean元素
		String xpath = "//bean";
		
		// 2.4 对Bean元素进行遍历
		@SuppressWarnings("unchecked")
		List<Element> list = doc.selectNodes(xpath);
		if(list!=null){
			for(Element eleBean : list){
				Bean bean = new Bean();
				// 取出eleBean中的 name/class/scope属性值，封装到Bean中
				String id = eleBean.attributeValue("id");
				String className = eleBean.attributeValue("class");
				String scope = eleBean.attributeValue("scope");
				// name和class的属性值都是String类型，可以直接使用setter方法
				bean.setId(id);
				bean.setClassName(className);
				// scope属性值可能为 null
				bean.setScope(scope);
				
				// 取出当前bean元素的所有的property子元素，并将其name/value/ref装封装到Property对象中
				@SuppressWarnings("unchecked")
				List<Element> children = eleBean.elements("property");
				if(children != null){
					for(Element eleProp : children){
						Property property = new Property();
						
						String pName = eleProp.attributeValue("name");
						String pValue = eleProp.attributeValue("value");
						String pRef = eleProp.attributeValue("ref");
						
						property.setName(pName);
						property.setValue(pValue);
						property.setRef(pRef);
						
						// 将property子元素封装到bean中
						bean.getProperties().add(property);
					}
				}
				// 将解析好的Bean对象放到map中
				map.put(id, bean);
			}
		}
		
		// 3.返回 Map对象
		return map;
	}
}
   