package com.mybatis.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mybatis.mybatisplus.pojo.Site;

import java.util.List;

/**
 * @author Cjl
 * @date 2021/8/3 19:08
 */
public interface ISiteMapper extends BaseMapper<Site> {
    List<Site> search(String name);

}
