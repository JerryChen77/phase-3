package com.shine;

import com.shine.entity.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {
    @Test
    public void getUser01(){
        /**
         * Bean的声明周期
         *  构造 ===》   set ====》   后处理器的Before====》   init  ===》  后处理器的After ===》 destroy
         */
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext-aop.xml");

        User user = ioc.getBean(User.class);

        ioc.close();

    }
}
