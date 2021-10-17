package com.shine.mapper;

import com.shine.entity.Hero;
import com.shine.entity.User;

import java.util.List;

public interface HeroMapper {

    Integer insertHero(Hero hero);


    List<Hero> selectHero(Hero hero);

    Integer insertHeroes(List<Hero> heroes);
    List<Hero> selectHeroesById(List<Integer> ids);

    Integer updateHero(Hero hero);


    Integer updateHero2(Hero hero);

    List<Hero> selectHero2(Hero hero);

    List<Hero> selectHeroByOrder(Hero hero);

}
