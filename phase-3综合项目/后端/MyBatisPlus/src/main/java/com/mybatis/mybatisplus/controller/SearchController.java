package com.mybatis.mybatisplus.controller;

import com.mybatis.mybatisplus.pojo.Site;
import com.mybatis.mybatisplus.service.DeviceService;
import com.mybatis.mybatisplus.service.SiteService;
import com.mybatis.mybatisplus.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cjl
 * @date 2021/8/5 14:48
 */
@RestController
@RequestMapping("/search")
@CrossOrigin
public class SearchController {
    @Autowired
    DeviceService deviceService;
    @Autowired
    SiteService siteService;

    //模糊查询
    @RequestMapping("/s/{name}")
    public ResultVO search(@PathVariable("name") String name){
        ResultVO resultVO = siteService.search(name);
        return resultVO;
    }



}
