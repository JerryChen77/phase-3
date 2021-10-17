package com.shine.dao;

import com.shine.entity.Hero;

import java.util.List;

/**
 * 持久层接口
 */
public interface HeroDao {
    Integer insertHero(Hero hero);

    Hero selectHeroById(Integer id);

    List<Hero> selectHeroes();
}
