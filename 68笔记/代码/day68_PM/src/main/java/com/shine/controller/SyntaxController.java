package com.shine.controller;

import com.shine.dao.HeroDao;
import com.shine.entity.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/syntax")
public class SyntaxController {

    @Autowired
    private HeroDao heroDao;

    @RequestMapping("/testIf")
    public String testIf(Model model){
        model.addAttribute("age",12);
        return "syntax";
    }

    // switch
    @RequestMapping("/testSwitch")
    public String testSwitch(Model model){
        model.addAttribute("day",3);
        return "syntax";
    }

    // each
    @RequestMapping("/testEach")
    public String testEach(Model model){
        List<Hero> heroes = heroDao.selectHeroes();
        model.addAttribute("heroes",heroes);
        return "syntax";
    }

}
