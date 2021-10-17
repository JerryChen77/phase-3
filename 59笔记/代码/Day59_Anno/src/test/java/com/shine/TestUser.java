package com.shine;

import com.shine.config.SpringConfig;
import com.shine.entity.User;
import com.shine.service.UserService;
import com.shine.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {

    @Test
    public void testBegin(){
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserService userService = ioc.getBean(UserService.class);

        userService.updateUser(new User());

        System.out.println("============================");

        userService.selectUser(10);

    }

    @Test
    public void testDelete(){
//        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = ioc.getBean(UserService.class);

        userService.deleteUser(10);
    }
}
