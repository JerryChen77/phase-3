package com.shine;

import com.shine.dao.UserDao;
import com.shine.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {

    @Test
    public void getUser(){
        User user = new User();
        user.show();
    }

    @Test
    public void getUserIOC(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");

        User user = applicationContext.getBean("user", User.class);
        user.show();

    }

    @Test
    public void getUserDaoIOC(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");

        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        userDao.insertUser(new User());

    }

}
