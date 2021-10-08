package com.qf.mybatisplus.springboot;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qf.mybatisplus.springboot.mapper.IUserMapper;
import com.qf.mybatisplus.springboot.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisPlusSpringbootApplicationTests {

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
    public void test07() throws Exception {

        User user = new User();
        user.setName("李雷");
        user.setAge(11);
        user.setPassword("111222");
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", 9L);

        int row = userMapper.update(user, wrapper);
        System.out.println("row = " + row);
    }


    /**
     * update
     **/
    @Test
    public void test06() throws Exception {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", 9L);
        wrapper.eq("name", "九九");

        //要更新必须使用set方法
        wrapper.set("user_name", "jiujiujiu");
        wrapper.set("name", "小九九");

        int row = userMapper.update(null, wrapper);
        System.out.println("row = " + row);
    }

    /**
     * updateById
     **/
    @Test
    public void test05() throws Exception {
        User user = new User(9L, "jiujiu", "123123", "九九", 99, "jj@163.com", null);
        int row = userMapper.updateById(user);
        System.out.println("row = " + row);
    }


    /**
     * delete
     *  条件为实体
     **/
    @Test
    public void test04() throws Exception {
        User user = new User();
        user.setId(8L);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.setEntity(user);
        int row = userMapper.delete(wrapper);
        System.out.println("row = " + row);

    }

    /**
     * deleteByMap
     *  map的key写的表的字段
     **/
    @Test
    public void test03() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", "jack");
        int row = userMapper.deleteByMap(map);
        System.out.println("row = " + row);
    }

    /**
     * 增加
     **/
    @Test
    public void test02() throws Exception {
        User user = new User();
        //user.setUserName("jack").setPassword("123123").setEmail("jack@163.com").setAge(12).setName("杰克");
        user.setUserName("jack").setPassword("123123").setMail("jack@163.com").setAge(12).setName("杰克");
        int row = userMapper.insert(user);
        System.out.println("row = " + row);
        //主键回填
        System.out.println(user.getId());
    }


    /**
     * MybatisPlus快速入门
     */
    @Test
    public void test01() {

        List<User> users = userMapper.selectList(null);
        print(users);
    }

}
