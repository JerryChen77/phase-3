package com.shine;

import com.shine.entity.Account;
import com.shine.mapper.AccountMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class TestAccount {
    @Test
    public void testFind() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        Account account = accountMapper.findByUsername("zhang");
        System.out.println(account);
    }

    @Test
    public void testUpdate() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        Account account = new Account();
        account.setUsername("zhang");
        account.setBalance(15000);
        Integer ret = accountMapper.updateAccount(account);
        System.out.println(ret);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testFind02(){
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext-ms.xml");
        SqlSessionFactory sqlSessionFactory = ioc.getBean(SqlSessionFactory.class);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        Account account = accountMapper.findByUsername("zhang");
        System.out.println(account);
    }
}
