package com.shine.controller;

import com.shine.dao.HeroDao;
import com.shine.entity.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/hero")
public class HeroController {

    @Autowired
    private HeroDao heroDao;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello Hero";
    }

    @RequestMapping("/heroes")
    public String getHeroes(Model model){
        List<Hero> heroes = heroDao.selectHeroes();
        model.addAttribute("heroes",heroes);
        System.out.println(heroes);
        return "showHeroes";
    }

    /**
     * 查询数据，转到修改页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/selectHeroByIdToMod/{id}")
    public String selectHeroByIdToMod(Model model,@PathVariable Integer id){
        Hero hero = heroDao.selectByPrimaryKey(id);
        model.addAttribute("hero",hero);
        return "updateHero";
    }



    @RequestMapping("/updateHero")
    public String updateHero(Model model,Hero hero){
        heroDao.updateByPrimaryKeySelective(hero);
        return "redirect:/hero/heroes";
    }

}
