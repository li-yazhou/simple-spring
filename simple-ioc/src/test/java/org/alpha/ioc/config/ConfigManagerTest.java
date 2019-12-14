package org.alpha.ioc.config;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;


/**
 * @author liyazhou1
 * @date 2019/12/12
 */
public class ConfigManagerTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("before");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("after");
    }

    @Test
    public void config() {
        String path = "./applicationContext.xml";

        Map<String, BeanDefinition> config = ConfigManager.loadConfig(path);

        for(Map.Entry<String, BeanDefinition> entry : config.entrySet()){
            String key = entry.getKey();
            BeanDefinition value = entry.getValue();
            List<BeanProperty> lstProp = value.getProperties();
            System.out.println(key + " :: " + value);
            for (BeanProperty property : lstProp) {
                System.out.println("\t" + property);
            }
        }
    }
}