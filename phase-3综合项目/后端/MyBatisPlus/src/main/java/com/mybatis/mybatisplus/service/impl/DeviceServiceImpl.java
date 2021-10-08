package com.mybatis.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
import com.mybatis.mybatisplus.mapper.IDeviceMapper;
import com.mybatis.mybatisplus.pojo.Device;
import com.mybatis.mybatisplus.service.DeviceService;
import com.mybatis.mybatisplus.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Cjl
 * @date 2021/8/3 19:14
 */
@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    IDeviceMapper deviceMapper;

    @Override
    public ResultVO findAllByUserId(Integer userId) {
        ResultVO resultVO = new ResultVO();
        List<Device> devices = deviceMapper.selectAllByUserId(userId);
        resultVO.setMessage("查询成功");
        resultVO.setSuccess(true);
        resultVO.setData(devices);
        return resultVO;
    }

    @Override
    public ResultVO findByDeviceId(Integer deviceId) {
        ResultVO resultVO = new ResultVO();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("device_id",deviceId);
        Device device = deviceMapper.selectOne(wrapper);
        resultVO.setMessage("查询成功");
        resultVO.setSuccess(true);
        resultVO.setData(device);
        return resultVO;
    }

    @Override
    public ResultVO update(Device device) {
        ResultVO resultVO = new ResultVO();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("device_id",device.getDeviceId());
        deviceMapper.update(device,wrapper);
        resultVO.setMessage("更新成功");
        resultVO.setSuccess(true);
        return resultVO;
    }

    @Override
    public ResultVO add(Device device) {
        ResultVO resultVO = new ResultVO();
        deviceMapper.insert(device);
        resultVO.setMessage("增加成功");
        resultVO.setSuccess(true);
        return resultVO;
    }

    @Override
    public ResultVO delete(Integer deviceId) {
        ResultVO resultVO = new ResultVO();
        deviceMapper.deleteById(deviceId);
        resultVO.setMessage("删除成功");
        resultVO.setSuccess(true);
        return resultVO;
    }


}
