package com.shine.mapper;

import com.shine.entity.User;
import com.shine.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class UserMapperTest {
    @Test
    public void getUser(){
        UserMapper userMapper = MyBatisUtils.getMapper(UserMapper.class);
        User user = userMapper.selectUser(1);
        System.out.println(user);
        MyBatisUtils.commit();

        UserMapper userMapper2 = MyBatisUtils.getMapper(UserMapper.class);
        User user2 = userMapper2.selectUser(1);
        System.out.println(user2);
        MyBatisUtils.commit();
    }

    @Test
    public void getUserById(){
        UserMapper userMapper = MyBatisUtils.getMapper(UserMapper.class);
        User user = userMapper.selectUser(1);
        System.out.println(user);

        User user1 = userMapper.selectUser(1);
        System.out.println(user1);

        System.out.println(user == user1);

    }

    @Test
    public void getUser02(){
        UserMapper userMapper = MyBatisUtils.getMapper(UserMapper.class);
        User user01 = userMapper.selectUser(1);
        System.out.println(user01);

        User user02 = new User();
        user02.setUsername("bajie");
        user02.setPassword("jieba");
        Integer ret02 = userMapper.insertUser(user02);
        System.out.println("ret02==" + ret02);

        User user03 = userMapper.selectUser(1);
        System.out.println(user03);

        System.out.println(user01 == user03);

    }

    @Test
    public void getUser03(){
        UserMapper userMapper = MyBatisUtils.getMapper(UserMapper.class);
        User user01 = userMapper.selectUser(1);
        System.out.println(user01);

        Integer ret02 = userMapper.deleteUser(35);
        System.out.println("ret02==" + ret02);

        User user03 = userMapper.selectUser(1);
        System.out.println(user03);

        System.out.println(user01 == user03);

    }

    @Test
    public void getUser04(){
        SqlSession sqlSession01 = MyBatisUtils.openSession();
        UserMapper userMapper01 = sqlSession01.getMapper(UserMapper.class);

        User user01 = userMapper01.selectUser(2);
        System.out.println(user01);
        sqlSession01.commit();

        User user02 = userMapper01.selectUser(2);
        System.out.println(user02);

    }

    @Test
    public void getUser05(){
        SqlSession sqlSession01 = MyBatisUtils.openSession();
        UserMapper userMapper01 = sqlSession01.getMapper(UserMapper.class);

        User user01 = userMapper01.selectUser(2);
        System.out.println(user01);
        sqlSession01.close();


        SqlSession sqlSession02 = MyBatisUtils.openSession();
        UserMapper userMapper02 = sqlSession02.getMapper(UserMapper.class);
        User user02 = userMapper02.selectUser(2);
        System.out.println(user02);
        sqlSession02.close();

    }

    @Test
    public void getUser06(){
        UserMapper userMapper = MyBatisUtils.getMapper(UserMapper.class);
        User user01 = userMapper.selectUser(1);
        System.out.println(user01);
        MyBatisUtils.commit();

        UserMapper userMapper02 = MyBatisUtils.getMapper(UserMapper.class);
        User user02 = userMapper02.selectUser(1);
        System.out.println(user02);
        MyBatisUtils.commit();

        UserMapper userMapper03 = MyBatisUtils.getMapper(UserMapper.class);
        User user03 = userMapper03.selectUser(1);
        System.out.println(user03);
        MyBatisUtils.commit();

        System.out.println(user01 == user03);
    }


    @Test
    public void getUser07(){
        UserMapper userMapper = MyBatisUtils.getMapper(UserMapper.class);
        User user01 = userMapper.selectUser(1);
        System.out.println(user01);
        MyBatisUtils.commit();

        UserMapper userMapper02 = MyBatisUtils.getMapper(UserMapper.class);
        User user02 = new User();
        user02.setUsername("shaseng");
        user02.setPassword("sengsha");
        userMapper02.insertUser(user02);
        MyBatisUtils.commit();

        UserMapper userMapper03 = MyBatisUtils.getMapper(UserMapper.class);
        User user03 = userMapper03.selectUser(1);
        System.out.println(user03);
        MyBatisUtils.commit();

        System.out.println(user01 == user03);
    }

}
