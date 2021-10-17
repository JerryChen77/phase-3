package com.shine.factory;

import java.io.IOException;
import java.util.Properties;

public class StaticBeanFactory {
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(StaticBeanFactory.class.getClassLoader().getResourceAsStream("bean.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取对象的方法
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        try {
            Class<?> clazz = Class.forName(properties.getProperty(beanName));

            if (clazz != null){
                return clazz.newInstance();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
