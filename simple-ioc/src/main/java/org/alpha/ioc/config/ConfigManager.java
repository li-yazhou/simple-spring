package org.alpha.ioc.config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作用：
 *
 * @author:	liyazhou
 * @version: 2016年9月24日下午7:34:36
 */
public class ConfigManager {

	/**
	 * 解析applicationContext.xml配置文件中的Bean
	 */
	public static Map<String, BeanDefinition> loadConfig(String path){

		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			InputStream in = ConfigManager.class.getClassLoader().getResourceAsStream(path);
			doc = reader.read(in);
		} catch (DocumentException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException("读取配置文件出现异常！");
		}

		String xpath = "//bean";
		@SuppressWarnings("unchecked")
		List<Element> list = doc.selectNodes(xpath);
		if(list == null || list.isEmpty()){
		    return Collections.emptyMap();
        }

        Map<String, BeanDefinition> beanContainer = new HashMap<>();
        for(Element eleBean : list){

            String id = eleBean.attributeValue("id");
            String className = eleBean.attributeValue("class");
            String scope = eleBean.attributeValue("scope");

            BeanDefinition bean = new BeanDefinition(id, className, scope);

            @SuppressWarnings("unchecked")
            List<Element> children = eleBean.elements("property");
            if(children != null){
                for(Element element : children){
                    String name = element.attributeValue("name");
                    String value = element.attributeValue("value");
                    String ref = element.attributeValue("ref");

                    BeanProperty property = BeanProperty.builder()
                            .name(name)
                            .value(value)
                            .ref(ref)
                            .build();

                    bean.addProperty(property);
                }
            }
            beanContainer.put(id, bean);
        }

		return beanContainer;
	}
}