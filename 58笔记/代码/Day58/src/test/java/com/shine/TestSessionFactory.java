package com.shine;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSessionFactory {

    @Test
    public void getSession(){
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext03.xml");
        SqlSession session = ioc.getBean("session", SqlSession.class);
        System.out.println(session);
    }

}
