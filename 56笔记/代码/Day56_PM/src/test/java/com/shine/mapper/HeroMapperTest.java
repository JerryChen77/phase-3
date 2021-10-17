package com.shine.mapper;

import com.shine.entity.Hero;
import com.shine.utils.MyBatisUtils;
import org.junit.Test;

import java.util.ArrayList;
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

    @Test
    public void addHeroes(){
        Hero hero01 = new Hero();
        hero01.setUsername("公孙胜");
        hero01.setAge(36);
        hero01.setGender("male");
        hero01.setAddr("山东济宁");
        hero01.setInfo("入云龙");

        Hero hero02 = new Hero();
        hero02.setUsername("关胜");
        hero02.setAge(36);
        hero02.setGender("male");
        hero02.setAddr("山东菏泽");
        hero02.setInfo("天勇星");

        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero01);
        heroes.add(hero02);

        HeroMapper heroMapper = MyBatisUtils.getMapper(HeroMapper.class);

        Integer ret = heroMapper.insertHeroes(heroes);
        System.out.println("ret==" + ret);

        // MyBatisUtils.commit();
    }

    @Test
    public void getHeroesById(){
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(3);
        ids.add(5);

        HeroMapper heroMapper = MyBatisUtils.getMapper(HeroMapper.class);

        List<Hero> heroes = heroMapper.selectHeroesById(ids);
        System.out.println(heroes);

        MyBatisUtils.commit();

    }


    @Test
    public void modifyHero(){
        Hero hero = new Hero();
        hero.setId(2);
        hero.setUsername("吴用");
        hero.setAge(36);
        hero.setAddr("山东曹县");

        HeroMapper heroMapper = MyBatisUtils.getMapper(HeroMapper.class);
        Integer ret = heroMapper.updateHero(hero);

        System.out.println("ret==" + ret);

        MyBatisUtils.commit();
    }

    @Test
    public void modifyHero2(){
        Hero hero = new Hero();
        hero.setId(3);
        hero.setUsername("武松");
        hero.setAge(33);
        hero.setAddr("山东郓城");

        HeroMapper heroMapper = MyBatisUtils.getMapper(HeroMapper.class);
        Integer ret = heroMapper.updateHero2(hero);

        System.out.println("ret==" + ret);

        //MyBatisUtils.commit();
    }

    @Test
    public void getHero2(){
        Hero hero = new Hero();
        hero.setAddr("%山东%");
        HeroMapper heroMapper = MyBatisUtils.getMapper(HeroMapper.class);
        List<Hero> heroes = heroMapper.selectHero2(hero);

        System.out.println(heroes);
    }

    @Test
    public void getHeroByOrder(){
        Hero hero = new Hero();
        hero.setUsername("吴用");
        hero.setAddr("%山东%");
        HeroMapper heroMapper = MyBatisUtils.getMapper(HeroMapper.class);
        List<Hero> heroes = heroMapper.selectHeroByOrder(hero);

        System.out.println(heroes);
    }

}
