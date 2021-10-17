package com.shine;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shine.dao.HeroDao;
import com.shine.entity.Hero;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Day68ApplicationTests {

    @Autowired
    private HeroDao heroDao;

    @Test
    void contextLoads() {
        Hero hero = heroDao.selectHeroById(1);
        System.out.println(hero);
    }

    @Test
    public void getHeroes(){
        PageHelper.startPage(2,5);

        List<Hero> heroes = heroDao.selectHeroes();
        heroes.stream().forEach(System.out::println);

        PageInfo pageInfo = new PageInfo(heroes);
        System.out.println(pageInfo);
    }

}
