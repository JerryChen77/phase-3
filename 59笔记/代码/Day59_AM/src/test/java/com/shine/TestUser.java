package com.shine;

import com.shine.entity.User;
import com.shine.service.UserService;
import com.shine.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {

    @Test
    public void testBegin(){
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserService userService = ioc.getBean(UserServiceImpl.class);

        userService.updateUser(new User());

        System.out.println("============================");

        userService.selectUser(10);

    }

}
