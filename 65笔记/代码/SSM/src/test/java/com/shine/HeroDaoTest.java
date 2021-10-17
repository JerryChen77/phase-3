package com.shine;

import com.shine.dao.HeroDao;
import com.shine.entity.Hero;
import com.shine.service.HeroService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class HeroDaoTest {

    @Autowired
    private HeroDao heroDao;

    @Autowired
    private HeroService heroService;

    @Test
    public void getHero01(){
        Hero hero = heroDao.selectHeroById(2);
        System.out.println(hero);
    }

    @Test
    public void getHero02(){
        Hero hero = heroService.queryHeroById(2);
        System.out.println(hero);
    }

}
