package com.shine;

import com.shine.dao.UserDao;
import com.shine.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day70ApplicationTests {
    @Autowired
    UserDao userDao;

    @Test
    void contextLoads() {
        User user = userDao.selectByUsername("zhangsan");
        System.out.println(user);
    }

}
