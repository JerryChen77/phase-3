package com.shine;

import com.shine.service.AccountService;
import com.shine.service.impl.AccountServiceImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestAccount {

    @Test
    public void testAccount(){

        AccountServiceImpl target = new AccountServiceImpl();
        /**
         * ClassLoader loader,      代理目标的类加载器
         * Class<?>[] interfaces,   代理目标的接口S
         * InvocationHandler h
         */
        AccountService proxy = (AccountService) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * @param proxy     代理对象，一般用不到
                     * @param method    代理的方法
                     * @param args      代理方法中接收到的参数们
                     * @return 代理对象
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("交易开始....");
                        Object result = null;
                        try {
                            result = method.invoke(target, args);
                            System.out.println("转账成功");
                            System.out.println("打印转账凭证");
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("转账失败,请重新操作");
                        } finally {
                            System.out.println("交易结束,收尾");
                        }
                        return result;
                    }
                }
        );
        proxy.transfer(-2000,"" ,"");

    }


    public Object getProxy(Class clazz){
        Object result = Proxy.newProxyInstance(
                clazz.getClassLoader(),
                clazz.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * @param proxy     代理对象，一般用不到
                     * @param method    代理的方法
                     * @param args      代理方法中接收到的参数们
                     * @return 代理对象
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("交易开始....");
                        Object result = null;
                        try {
                            result = method.invoke(clazz.newInstance(), args);
                            System.out.println("转账成功");
                            System.out.println("打印转账凭证");
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("转账失败,请重新操作");
                        } finally {
                            System.out.println("交易结束,收尾");
                        }
                        return result;
                    }
                }
        );
        return result;
    }

    @Test
    public void tt() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(""));
        SqlSession sqlSession = sqlSessionFactory.openSession();

//        sqlSession.getMapper()

    }

}
