package com.shine.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtils {
    private static SqlSessionFactory sqlSessionFactory = null;
    private static final ThreadLocal<SqlSession> THREADLOCAL = new ThreadLocal();

    static {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取sqlsession对象
     * @return
     */
    public static SqlSession openSession(){
        SqlSession sqlSession = THREADLOCAL.get();

        if (sqlSession == null){
            sqlSession = sqlSessionFactory.openSession();
            THREADLOCAL.set(sqlSession);
        }

        return sqlSession;
    }

    /**
     * 释放链接
     */
    private static void closeSession(){
        SqlSession session = THREADLOCAL.get();
        session.close();
        THREADLOCAL.remove();
    }

    /**
     * 提交事务（提交当前线程中的SqlSession所管理的事务）
     */

    public static void commit(){
        SqlSession session = openSession();
        session.commit();
        closeSession();
    }

    /**
     * 回滚事务（回滚当前线程中的SqlSession所管理的事务）
     */
    public static void rollback(){
        SqlSession session = openSession();
        session.rollback();
        closeSession();
    }

    /**
     * 获得接口实现类对象
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Object> T getMapper(Class<T> clazz){
        SqlSession session = openSession();
        return session.getMapper(clazz);
    }
}
