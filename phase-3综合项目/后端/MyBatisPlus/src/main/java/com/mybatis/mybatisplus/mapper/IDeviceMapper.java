package com.mybatis.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mybatis.mybatisplus.pojo.Device;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Cjl
 * @date 2021/8/3 19:13
 */
@Mapper
public interface IDeviceMapper extends BaseMapper<Device> {
    List<Device> selectAllByUserId(Integer userId);
}
