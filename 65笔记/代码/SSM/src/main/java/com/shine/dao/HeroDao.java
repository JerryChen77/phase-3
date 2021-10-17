package com.shine.dao;

import com.shine.entity.Hero;

import java.util.List;

/**
 * HeroDao
 */
public interface HeroDao {
    Integer insertHero(Hero hero);
    Integer deleteHero(Integer id);
    Integer updateHero(Hero hero);
    Hero selectHeroById(Integer id);
    List<Hero> selectHeroes();
}
