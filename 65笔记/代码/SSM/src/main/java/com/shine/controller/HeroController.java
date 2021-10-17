package com.shine.controller;


import com.shine.entity.Hero;
import com.shine.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public ModelAndView getHeroes(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/showHeroes.jsp");
        List<Hero> heroes = heroService.queryHeroes();

        modelAndView.addObject("heroes",heroes);

        return modelAndView;
    }

}
