package com.shine;

import com.shine.proxyjdk.FangDong;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TestCglibProxy {

    @Test
    public void testFang() {
        FangDong target = new FangDong();

        FangDong proxy = (FangDong) Enhancer.create(
                target.getClass(),
                new MethodInterceptor() {
                    /**
                     * @param proxy     代理对象，一般不用
                     * @param method    被代理方法
                     * @param args      方法中传入的参数
                     * @param methodProxy   方法代理
                     * @return
                     * @throws Throwable
                     */
                    @Override
                    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
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
        proxy.chuZu(2000);
    }
}
