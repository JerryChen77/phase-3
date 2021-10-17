package com.shine;

import com.alibaba.druid.pool.DruidDataSource;
import com.shine.dao.StuDao;
import com.shine.dao.impl.impl.StuDaoImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class TestStu {
    @Test
    public void getStu01() throws SQLException {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext02.xml");
        StuDaoImpl stuDao = ioc.getBean("stuDao", StuDaoImpl.class);
        System.out.println(stuDao.getDataSource().getConnection());
        ioc.close();
    }

    @Test
    public void getStu02() throws SQLException {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext02.xml");
        DruidDataSource dataSource = ioc.getBean(DruidDataSource.class);
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void getStu03() throws SQLException {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext02.xml");
        StuDao stuDao = ioc.getBean(StuDao.class);
        System.out.println(stuDao);
        ioc.close();
        /**
         * BeanFactory
         */
    }
}
