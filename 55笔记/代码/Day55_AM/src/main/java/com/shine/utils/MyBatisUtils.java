package com.shine.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * MyBatis工具类
 *  获取sqlSession对象
 *  提交事务
 *  回滚
 */
public class MyBatisUtils {
    private static SqlSessionFactory sqlSessionFactory = null;
    private static final ThreadLocal<SqlSession> THREAD_LOCAL = new ThreadLocal();

    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取会话
     * @return
     * @throws IOException
     */
    public static SqlSession openSession(){
        SqlSession sqlSession = THREAD_LOCAL.get();

        if (sqlSession == null){
            sqlSession = sqlSessionFactory.openSession();
            THREAD_LOCAL.set(sqlSession);
        }
        return sqlSession;
    }

    /**
     * 关闭连接
     */
    public static void closeSession(){
        SqlSession sqlSession = openSession();
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 关闭连接
     */
    public static void rollback(){
        SqlSession sqlSession = openSession();
        sqlSession.rollback();
    }

    /**
     * 获取Mapper对象
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Object> T getMapper(Class<T> clazz){
        SqlSession sqlSession = openSession();
        return sqlSession.getMapper(clazz);
    }

}
