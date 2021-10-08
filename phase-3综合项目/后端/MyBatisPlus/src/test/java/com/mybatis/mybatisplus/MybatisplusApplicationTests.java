package com.mybatis.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.github.pagehelper.PageInfo;
import com.mybatis.mybatisplus.mapper.IDeviceMapper;
import com.mybatis.mybatisplus.mapper.ISiteMapper;
import com.mybatis.mybatisplus.mapper.IUserMapper;
import com.mybatis.mybatisplus.pojo.Device;
import com.mybatis.mybatisplus.pojo.Site;
import com.mybatis.mybatisplus.pojo.User;
import com.mybatis.mybatisplus.service.DeviceService;
import com.mybatis.mybatisplus.service.SiteService;
import com.mybatis.mybatisplus.service.UserService;
import com.mybatis.mybatisplus.vo.ResultVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class MybatisplusApplicationTests {
    @Autowired
    IUserMapper userMapper;
    @Autowired
    ISiteMapper siteMapper;
    @Autowired
    IDeviceMapper deviceMapper;
    @Autowired
    SiteService siteService;
    @Autowired
    UserService userService;
    @Autowired
    DeviceService deviceService;


    @Test
    public void test01() {
        Site site = siteMapper.selectById(1);
        System.out.println("site = " + site);

        ResultVO resultVO = siteService.selectBySiteId(1);
        Site data = (Site) resultVO.getData();
        System.out.println("data = " + data);
    }


    @Test
    public void test0() {
        ResultVO resultVO = userService.selectAll();
        List<User> users = (List<User>) resultVO.getData();
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }


    @Test
    public void contextLoads() {
        int row = userMapper.deleteById(1);
        System.out.println("row = " + row);
    }

    @Test
    public void test2(){
////        User user = new User(null,"李四","eminem","狂徒",23,"2323@qq.com",null);
//        int row = userMapper.insert(user);
//        System.out.println("row = " + row);
//        System.out.println("user.getId() = " + user.getId());
    }
    @Test
    public void test03(){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.ge("age",18);
        List list = userMapper.selectMaps(queryWrapper);
        for (Object o : list) {
            System.out.println(o);
        }
    }

    @Test
    public void test04(){

        IPage page = new Page();
        page.setSize(3);
        page.setCurrent(1);
        IPage page1 = userMapper.selectPage(page, null);
        List records = page1.getRecords();
        for (Object record : records) {
            System.out.println("record = " + record);
        }


    }
//    @Test
//    public void test05(){
//        ResultVO resultVO = deviceService.findAllByUserId(1, 1, 3);
//        PageInfo pageInfo = (PageInfo) resultVO.getData();
//        System.out.println(pageInfo);
//
//    }


    @Test
    public void test06(){
        List<Device> devices = deviceMapper.selectAllByUserId(2);
        System.out.println(devices);

    }
}
