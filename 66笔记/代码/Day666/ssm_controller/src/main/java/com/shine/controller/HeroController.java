package com.shine.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shine.entity.Hero;
import com.shine.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/hero")
@Controller
public class HeroController {

    @Autowired
    private HeroService heroService;

    /**
     * 查询指定id
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/heroes/{id}")
    @ResponseBody
    public Hero getHeroById(@PathVariable Integer id, Model model){
        Hero hero = heroService.queryHeroById(id);
        return hero;
    }

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/heroes")
    @ResponseBody
    public ModelAndView getHeroes(Integer pageNum){
        pageNum = pageNum==null ? 1:pageNum;

        PageHelper.startPage(pageNum,5);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/showHeroes.jsp");
        List<Hero> heroes = heroService.queryHeroes();

        PageInfo pageInfo = new PageInfo(heroes);

        modelAndView.addObject("heroes",heroes);
        modelAndView.addObject("pageInfo",pageInfo);

        return modelAndView;
    }

    @DeleteMapping("/heroes/{id}")
    @ResponseBody
    public String removeHero(@PathVariable Integer id){
        Integer row = heroService.removeHero(id);
        if (row>0){
            return "success";
        }
        return "failed";
    }

    /**
     * 添加英雄
     * @param hero
     * @return
     */
    @RequestMapping("/saveHero")
    public ModelAndView saveHero(Hero hero){
        Integer ret = heroService.saveHero(hero);

        ModelAndView modelAndView = getHeroes(1);

        return modelAndView;
    }

    /**
     * 修改英雄
     * @param hero
     * @return
     */
    @RequestMapping("/modifyHero")
    public ModelAndView modifyHero(Hero hero){
        Integer ret = heroService.modifyHero(hero);

        ModelAndView modelAndView = getHeroes(1);

        return modelAndView;
    }
}
