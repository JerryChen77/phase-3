package com.mybatis.mybatisplus.controller;

import com.mybatis.mybatisplus.pojo.Device;
import com.mybatis.mybatisplus.service.DeviceService;
import com.mybatis.mybatisplus.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Cjl
 * @date 2021/8/3 21:50
 */
@RestController
@RequestMapping("/device")
@CrossOrigin
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    //查询当前用户所有工地的设备
    @RequestMapping("/devices/{userId}")
    public ResultVO findAll(@PathVariable("userId") Integer userId){
        ResultVO resultVO = deviceService.findAllByUserId(userId);
        return resultVO;
    }
    //修改时回显的信息
    @RequestMapping("/select/{deviceId}")
    public ResultVO findByDeviceId(@PathVariable("deviceId") Integer deviceId){
        ResultVO resultVO = deviceService.findByDeviceId(deviceId);
        return resultVO;
    }

    //保存修改
    @PostMapping("/update")
    public ResultVO update(@RequestBody Device device){
        ResultVO resultVO = deviceService.update(device);
        return resultVO;
    }

    //增加设备
    @PostMapping("/add")
    public ResultVO add(@RequestBody Device device){
        ResultVO resultVO = deviceService.add(device);
        return  resultVO;
    }
    //删除设备
    @DeleteMapping("/devices/{deviceId}")
    public ResultVO delete(@PathVariable("deviceId") Integer deviceId){
        ResultVO resultVO = deviceService.delete(deviceId);
        return resultVO;
    }

}
