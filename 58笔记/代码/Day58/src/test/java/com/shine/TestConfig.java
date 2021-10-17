package com.shine;

import com.shine.config.SourceConfig;
import com.shine.config.SpringConfig;
import com.shine.entity.User;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

public class TestConfig {

    @Test
    public void getUser01(){
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig.class);

        User user = ioc.getBean(User.class);
        System.out.println(user);
    }

    @Test
    public void getUser02() throws SQLException {
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig.class);

        DataSource dataSource = ioc.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void getUser03() throws SQLException {
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig.class, SourceConfig.class);

        DataSource dataSource = ioc.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void getUser04() throws SQLException {
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig.class);

        DataSource dataSource = ioc.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

}
