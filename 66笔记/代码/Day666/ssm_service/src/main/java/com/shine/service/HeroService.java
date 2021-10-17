package com.shine.service;

import com.shine.entity.Hero;

import java.util.List;

/**
 * HeroService
 */
public interface HeroService {
    Integer saveHero(Hero hero);
    Integer removeHero(Integer id);
    Integer modifyHero(Hero hero);
    Hero queryHeroById(Integer id);
    List<Hero> queryHeroes();
}
