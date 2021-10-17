package com.shine;

import com.shine.entity.User;
import com.shine.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-ms.xml")
public class TestUser01 {
    @Autowired
    private UserService userService;

    @Test
    public void getUser01() {
        List<User> users = userService.queryUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void getUser02() {
        Integer ret = userService.removeUser(38);
        System.out.println(ret);
    }

    @Test
    public void getUser03() throws SQLException {
        User user = new User();
        user.setId(24);
        user.setUsername("xunwukong");
        user.setPassword("wukongxun");

        Integer ret = userService.modifyUser(user);
        System.out.println(ret);
    }
}
