package com.shine;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shine.dao.UserDao;
import com.shine.entity.User;
import com.shine.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

public class TestUser00 {
    /**
     * MyBatis代码
     * @throws IOException
     */
    @Test
    public void getUser01() throws IOException {

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> users = userDao.selectUsers();

        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * MyBatis + Spring
     * @throws IOException
     */
    @Test
    public void getUser02() throws IOException {
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext-ms.xml");
        SqlSessionFactory sqlSessionFactory = ioc.getBean(SqlSessionFactory.class);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> users = userDao.selectUsers();

        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void getUser03(){
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext-ms.xml");
        UserService userService = ioc.getBean(UserService.class);

        PageHelper.startPage(2,5);

        List<User> users = userService.queryUsers();

        PageInfo info = new PageInfo(users);

        System.out.println(info);

        for (User user : users) {
            System.out.println(user);
        }
    }
}
