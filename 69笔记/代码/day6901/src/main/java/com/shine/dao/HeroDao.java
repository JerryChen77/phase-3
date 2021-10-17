package com.shine.dao;

import com.shine.entity.Hero;

import java.util.List;

public interface HeroDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Hero record);

    int insertSelective(Hero record);

    Hero selectByPrimaryKey(Integer id);
    List<Hero> selectHeroes();

    int updateByPrimaryKeySelective(Hero record);

    int updateByPrimaryKey(Hero record);
}