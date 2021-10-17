package com.shine.mapper;

import com.shine.entity.Hero;

import java.util.List;

public interface HeroMapper {

    Integer insertHero(Hero hero);

    Integer insertHeroes(List<Hero> heroes);

    List<Hero> selectHero(Hero hero);

}
