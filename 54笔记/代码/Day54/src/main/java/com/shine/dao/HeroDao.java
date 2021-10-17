package com.shine.dao;

import com.shine.entity.Hero;

import java.util.List;

/**
 * 持久层接口
 */
public interface HeroDao {
    // 插入数据
    int insertHero(Hero hero);

    int getHeroGenId01(Hero hero);

    int getHeroGenId02(Hero hero);

    int insertHeroInfo(String username,String gender,int age,String addr,String info);

    int deleteHero(int id);

    int updateHero(Hero hero);

    // 查找
    Hero selectHero(int id);

    List<Hero> selectHeroByName(String username);

    List<Hero> selectAllHero();

}
