package com.shine;

import com.shine.proxyjdk.FangDong;
import com.shine.proxyjdk.ZuFang;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestJdkProxy {
    @Test
    public void testFang(){
        FangDong target = new FangDong();

        /**
         * ClassLoader loader,      代理目标的类加载器
         * Class<?>[] interfaces,   代理目标的接口S
         * InvocationHandler h
         */
        ZuFang proxy = (ZuFang) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {

                    /**
                     *
                     * @param proxy     代理对象，一般用不到
                     * @param method    代理的方法
                     * @param args      代理方法中接收到的参数们
                     * @return 代理对象
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("中介发广告");
                        System.out.println("中介带看房");
                        System.out.println("中介签合同");
                        System.out.println("中介收到房租:" + args[0]);

                        args[0] = (Integer) args[0] - 400;

                        // 代理目标方法调用之后的返回值
                        Object result = method.invoke(target, args);

                        System.out.println("中介提供售后服务...");

                        return result;
                    }
                }
        );

        Integer money = proxy.chuZu(2000);
        System.out.println(money);

    }
}
