package com.shine.service.impl;

import com.shine.dao.HeroDao;
import com.shine.entity.Hero;
import com.shine.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * HeroService实现类
 */
@Service
public class HeroServiceImpl implements HeroService {

    @Autowired
    private HeroDao heroDao;

    @Override
    public Integer saveHero(Hero hero) {
        Integer row = heroDao.insertHero(hero);
        return row;
    }

    @Override
    public Integer removeHero(Integer id) {
        Integer row = heroDao.deleteHero(id);
        return row;
    }

    @Override
    public Integer modifyHero(Hero hero) {
        Integer row = heroDao.updateHero(hero);
        return row;
    }

    @Override
    public Hero queryHeroById(Integer id) {
        Hero hero = heroDao.selectHeroById(id);
        return hero;
    }

    @Override
    public List<Hero> queryHeroes() {
        List<Hero> heroes = heroDao.selectHeroes();
        return heroes;
    }
}
