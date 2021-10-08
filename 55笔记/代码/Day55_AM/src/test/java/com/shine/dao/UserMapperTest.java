package com.shine.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shine.entity.User;
import com.shine.utils.MyBatisUtils;
import org.junit.Test;

import java.util.List;

public class UserMapperTest {
    @Test
    public void getUserById(){
        UserMapper userMapper = MyBatisUtils.getMapper(UserMapper.class);

        User user = userMapper.findUserById(2);
        System.out.println(user);

        MyBatisUtils.closeSession();
    }

    @Test
    public void getUserById2(){
        UserMapper userMapper = MyBatisUtils.getMapper(UserMapper.class);

        User user = userMapper.findUserById2(2);
        System.out.println(user);

        MyBatisUtils.closeSession();
    }

    @Test
    public void getUserById3(){
        UserMapper userMapper = MyBatisUtils.getMapper(UserMapper.class);

        User user = userMapper.findUserById3(2);
        System.out.println(user);

        MyBatisUtils.closeSession();
    }

    @Test
    public void getUserById4(){
        UserMapper userMapper = MyBatisUtils.getMapper(UserMapper.class);

        User user = userMapper.findUserById4(2);
        System.out.println(user);

        MyBatisUtils.closeSession();
    }

    @Test
    public void getAllUser(){
        // 开启分页
        PageHelper.startPage(5,5);

        UserMapper userMapper = MyBatisUtils.getMapper(UserMapper.class);

        List<User> users = userMapper.findAllUser();
        // 创建pageInfo对象，封装了大量和分页相关的数据
        PageInfo pageInfo = new PageInfo(users);
        System.out.println("PageNum:" + pageInfo.getPageNum());
        System.out.println("PageSize:" + pageInfo.getPageSize());
        System.out.println("StartRow:" + pageInfo.getStartRow());
        System.out.println("Pages:" + pageInfo.getPages());
        System.out.println("List:" + pageInfo.getList());

        System.out.println("============================");

        System.out.println(pageInfo);

        /*for (User user : users) {
            System.out.println(user);
        }*/

        MyBatisUtils.closeSession();
    }
}
