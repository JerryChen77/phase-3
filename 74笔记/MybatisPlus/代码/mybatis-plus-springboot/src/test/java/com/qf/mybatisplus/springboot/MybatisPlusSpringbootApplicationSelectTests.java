package com.qf.mybatisplus.springboot;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.mybatisplus.springboot.mapper.IUserMapper;
import com.qf.mybatisplus.springboot.pojo.User;
import com.qf.mybatisplus.springboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisPlusSpringbootApplicationSelectTests {

    @Autowired
    UserService userService;

    @Autowired
    private IUserMapper userMapper;


    public void print(List<User> users){
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

    /**
     *
     **/
    @Test
    public void test06() throws Exception {
        List<User> users = userMapper.findAll();
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

    /**
     *
     **/
    @Test
    public void test05() throws Exception {

        System.out.println("userService = " + userService);

        IPage<User> page = new Page<>();
        page.setCurrent(2);
        page.setSize(5);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("password", "123456");

        IPage<User> ipage = userMapper.selectPage(page, wrapper);

        System.out.println("页码:" + ipage.getCurrent());
        System.out.println("总页数:" + ipage.getPages());
        System.out.println("页大小:" + ipage.getSize());
        System.out.println("总条数:" + ipage.getTotal());
        System.out.println("记录:" + ipage.getRecords());

    }

    /**
     * selectOne
     **/
    @Test
    public void test04() throws Exception {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", "jiujiujiu");
        User user = userMapper.selectOne(wrapper);
        System.out.println("user = " + user);
    }

    /**
     * selectMaps
     **/
    @Test
    public void test03() throws Exception {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("age", 20);
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        for (Map<String, Object> map : maps) {
            for (String key : map.keySet()) {
                System.out.println(key + ":" + map.get(key));
            }
        }
    }

    /**
     *
     **/
    @Test
    public void test02() throws Exception {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("age", 20);
        Integer count = userMapper.selectCount(wrapper);
        System.out.println("count = " + count);
    }


    /**
     * 根据ID查询
     */
    @Test
    public void test01() {

        User user = userMapper.selectById(1);
        System.out.println("user = " + user);
    }

}
