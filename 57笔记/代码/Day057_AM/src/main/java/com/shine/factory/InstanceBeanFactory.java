package com.shine.factory;

import java.io.IOException;
import java.util.Properties;

public class InstanceBeanFactory {
    private Properties properties = new Properties();

    /**
     * 构造器
     */
    public InstanceBeanFactory() {
        try {
            properties.load(InstanceBeanFactory.class.getClassLoader().getResourceAsStream("bean.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取对象
     * @param beanName
     * @return
     */
    public Object getBean(String beanName){
        try {
            Class<?> clazz = Class.forName(properties.getProperty(beanName));

            if (clazz != null){
                return clazz.newInstance();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
