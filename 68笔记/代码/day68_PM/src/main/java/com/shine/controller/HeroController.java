package com.shine.controller;

import com.shine.dao.HeroDao;
import com.shine.entity.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/hero")
@Controller
public class HeroController {

    @Autowired
    private HeroDao heroDao;

    @RequestMapping("/getHero01")
    public String getHero01(Model model){
        Hero hero = heroDao.selectHeroById(1);
        model.addAttribute("hero",hero);
        return "index";
    }

    @RequestMapping("/getHero02")
    public String getHero02(Model model){
        List<Hero> heroes = heroDao.selectHeroes();
        model.addAttribute("heroes",heroes);
        return "index";
    }

}
