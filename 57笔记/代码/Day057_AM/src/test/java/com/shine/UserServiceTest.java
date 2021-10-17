package com.shine;

import com.shine.entity.User;
import com.shine.factory.InstanceBeanFactory;
import com.shine.factory.StaticBeanFactory;
import com.shine.service.UserService;
import org.junit.Test;

public class UserServiceTest {
    @Test
    public void saveUser(){
        UserService userService = (UserService) StaticBeanFactory.getBean("userService");
        userService.saveUser(new User());
    }
    @Test
    public void saveUser02(){
        InstanceBeanFactory beanFactory = new InstanceBeanFactory();
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.saveUser(new User());
    }

}
