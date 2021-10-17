package com.shine.config;

/**
 * @author Cjl
 * @date 2021/7/7 22:21
 */
public class Test1 {

    Target target = new Target();
    //创建目标对象
    // 创建代理对象
    TargetInterface proxy = (TargetInterface) Proxy.newProxyInstance(target.getClass()
.getClassLoader(),target.getClass().getInterfaces(),new InvocationHandler() {            @Override            public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {                System.out.println("前置增强代码...");                Object invoke = method.invoke(target, args);                System.out.println("后置增强代码...");                return invoke;            }        });



}
