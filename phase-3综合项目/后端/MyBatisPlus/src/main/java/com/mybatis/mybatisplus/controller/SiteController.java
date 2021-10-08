package com.mybatis.mybatisplus.controller;

import com.mybatis.mybatisplus.pojo.Site;
import com.mybatis.mybatisplus.service.SiteService;
import com.mybatis.mybatisplus.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cjl
 * @date 2021/8/3 18:58
 */
@RestController
@RequestMapping("/site")
@CrossOrigin
public class SiteController {

    @Autowired
    SiteService siteService;

    //查询当前用户拥有的工地
    @RequestMapping("/sites/{userId}/{pageNum}/{pageSize}")
    public ResultVO findAllByUserId(@PathVariable("userId") Integer userId,
                            @PathVariable("pageNum") Integer pageNum,
                            @PathVariable("pageSize") Integer pageSize){
        ResultVO resultVO = siteService.selectByUserId(userId,pageNum,pageSize);
        return resultVO;
    }
    //查询所有的工地
    @RequestMapping("/sites")
    public ResultVO findAll(){
        ResultVO resultVO = siteService.selectAll();
        return resultVO;
    }

    //更新工地时的下拉框所需要的数据
    @RequestMapping("/select/{siteId}")
    public ResultVO findBySiteId(@PathVariable("siteId")Integer siteId){
        ResultVO resultVO = siteService.selectBySiteId(siteId);
        return resultVO;
    }

    //更新工地信息，接受JSON用@RequestBody
    @PostMapping("/update")
    public ResultVO update(@RequestBody Site site){
        ResultVO resultVO = siteService.update(site);
        return resultVO;
    }
    //新增工地信息，接受JSON用@RequestBody
    @PostMapping("/add")
    public ResultVO insert(@RequestBody Site site){
        ResultVO resultVO = siteService.insert(site);
        return  resultVO;
    }

    //删除工地信息
    @DeleteMapping("/sites/{siteId}")
    public ResultVO deleteById(@PathVariable("siteId")Integer siteId){
        ResultVO resultVO = siteService.deleteById(siteId);
        return resultVO;
    }




}
