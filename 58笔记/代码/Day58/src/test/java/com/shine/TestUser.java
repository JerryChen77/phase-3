package com.shine;

import com.shine.dao.UserDao;
import com.shine.entity.User;
import com.shine.service.UserService;
import com.shine.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

public class TestUser {

    @Test
    public void getUser01(){
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext03.xml");

        User user = ioc.getBean("user", User.class);
        user.show();
        System.out.println(user);

        ioc.close();
    }

    @Test
    public void getUser02(){
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext03.xml");

        UserDao userDao = ioc.getBean(UserDao.class);
        userDao.insertUser(new User());
    }

    @Test
    public void getUser03(){
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext03.xml");

        UserService userService = ioc.getBean(UserService.class);
        userService.saveUser(new User());
    }

    @Test
    public void getUser04() throws SQLException {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext03.xml");

        DataSource dataSource = ioc.getBean("dataSource", DataSource.class);
        System.out.println(dataSource.getConnection());
    }

}
