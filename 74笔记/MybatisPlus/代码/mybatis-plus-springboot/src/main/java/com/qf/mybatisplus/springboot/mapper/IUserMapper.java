package com.qf.mybatisplus.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.mybatisplus.springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ghy
 * @version 1.0
 * @date 2021-07-30
 **/
public interface IUserMapper extends BaseMapper<User> {

    List<User> findAll();

}
