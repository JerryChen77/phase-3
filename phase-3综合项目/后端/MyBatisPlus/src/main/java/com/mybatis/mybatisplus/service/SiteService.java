package com.mybatis.mybatisplus.service;

import com.mybatis.mybatisplus.pojo.Site;
import com.mybatis.mybatisplus.vo.ResultVO;

/**
 * @author Cjl
 * @date 2021/8/3 19:09
 */
public interface SiteService {

   ResultVO selectByUserId(Integer userId,Integer pageNum,Integer pageSize);

   ResultVO selectBySiteId(Integer siteId);

   ResultVO selectAll();

   ResultVO deleteById(Integer siteId);

   ResultVO insert(Site site);

   ResultVO update(Site site);

   ResultVO search(String name);
}
