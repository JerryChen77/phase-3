package com.shine.mapper;

import com.shine.entity.User;
import com.shine.utils.MyBatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class UserMapperTest {
    @Test
    public void getUserById() throws IOException {

        /**
         * SqlSessionFactoryBuilder
         * SqlSessionFactory
         *      加入了配置文件
         *      XMLConfigBuilder
         *      Configuration
         *
         * SqlSession
         *  数据库类型--环境
         *  事务
         *  提交方式
         *  openSessionFromDataSource
         *      配置信息
         *      事务隔离级别
         *      事务提交方式
         *      Executor
         *      DefaultSqlSession
         */

        // 创建sqlsessionFactory
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.findUserById(2);

        System.out.println(user);

    }

    @Test
    public void getUserByCondition(){
        UserMapper userMapper = MyBatisUtils.getMapper(UserMapper.class);

        User user = new User();
        user.setUsername("lisi");
        user.setPassword("11' or '1'='1");

        List<User> users = userMapper.findUserByCondition(user);

        System.out.println(users);
    }
}
