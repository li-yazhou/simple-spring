package cn.edu.myspring.di.config.parse.test;

import java.util.List;
import java.util.Map;

import cn.edu.myspring.di.config.Bean;
import cn.edu.myspring.di.config.Property;
import cn.edu.myspring.di.config.parse.ConfigManager;

/**  
 * 作用：
 *
 * @author:	liyazhou   
 * @version:2016年9月25日上午9:54:13 
 */
public class ConfigManagerTest {
	public static void main(String[] args){
		String path = "/applicationContext.xml";
		Map<String, Bean> config = ConfigManager.getConfig(path);
		for(Map.Entry<String, Bean> entry : config.entrySet()){
			String key = entry.getKey();
			Bean value = entry.getValue();
			List<Property> lstProp = value.getProperties();
			System.out.println(key + " :: " + value);
			for (Property property : lstProp) {
				System.out.println("\t" + property);
			}
		}
	}
}
   