package com.shine.config;

import com.shine.entity.User5;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 当前类标记为配置类，作用等同于配置文件
 */
@Configuration
public class UserConfig {

    /**
         <bean id=user5 class=com.shine.entity.User5>
            <property name=id>112233</property>
            <property name=username>112233</property>
            <property name=password>112233</property>
         </bean>
     */

    @Bean
    public User5 user5(){
        User5 user5 = new User5();
        user5.setId(112233);
        user5.setUsername("赵子龙");
        user5.setPassword("xiaolong");
        return user5;
    }

}
