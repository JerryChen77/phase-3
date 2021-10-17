package com.shine.mapper;

import com.shine.entity.Hero;
import com.shine.utils.MyBatisUtils;
import org.junit.Test;

import java.util.List;

public class HeroMapperTest {
    @Test
    public void getHero(){
        Hero hero = new Hero();
        hero.setUsername("wuyong");
        hero.setAge(36);
        hero.setAddr("%山东%");
        HeroMapper heroMapper = MyBatisUtils.getMapper(HeroMapper.class);
        List<Hero> heroes = heroMapper.selectHero(hero);

        System.out.println(heroes);
    }

}
