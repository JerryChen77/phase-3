package com.shine.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello User";
    }

    @RequestMapping("/login")
    public String login(){
        return "Hello Login";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "Bye User";
    }

    @RequestMapping("/getUser01")
    public String getUser01(){
        return "Hello Login0001";
    }

    @RequestMapping("/getUser02")
    public String getUser02(){
        return "Hello Login0002";
    }

    @RequestMapping("/getUser03")
    public String getUser03(){
        return "Hello Login0003";
    }

    @RequestMapping("/getUser04")
    public String getUser04(){
        return "Hello Login0004";
    }

    @RequestMapping("/getUser05")
    public String getUser05(){
        return "Hello Login0005";
    }

    @RequestMapping("/getUser06")
    public String getUser06(){
        return "Hello Login0006";
    }

    @RequestMapping("/getUser07")
    public String getUser07(){
        return "Hello Login0007";
    }

    // user角色可以访问
    @Secured("ROLE_user")
    @RequestMapping("/getUser08")
    public String getUser08(){
        return "Hello Login0008";
    }

    // admin角色可以访问
    @Secured("ROLE_admin")
    @RequestMapping("/getUser09")
    public String getUser09(){
        return "Hello Login0009";
    }

    // admin角色可以访问
    @Secured({"ROLE_admin","ROLE_user"})
    @RequestMapping("/getUser10")
    public String getUser10(){
        return "Hello Login0010";
    }

    // admin角色可以访问
    @PreAuthorize("hasRole('ROLE_admin')")
    @RequestMapping("/getUser11")
    public String getUser11(){
        return "Hello Login0011";
    }

    // user角色可以访问
    @PreAuthorize("hasRole('ROLE_user')")
    @RequestMapping("/getUser12")
    public String getUser12(){
        return "Hello Login0012";
    }

    // admin或者user角色可以访问
    @PreAuthorize("hasAnyAuthority('ROLE_user','ROLE_admin')")
    @RequestMapping("/getUser13")
    public String getUser13(){
        return "Hello Login0013";
    }

    // user_add权限可以访问
    @PreAuthorize("hasAuthority('user_add')")
    @RequestMapping("/getUser14")
    public String getUser14(){
        return "Hello Login0014";
    }

    // user_query权限可以访问
    @PreAuthorize("hasAuthority('user_query')")
    @RequestMapping("/getUser15")
    public String getUser15(){
        return "Hello Login0015";
    }

    // user_query或者user_add权限可以访问
    @PreAuthorize("hasAnyAuthority('user_add','user_query')")
    @RequestMapping("/getUser16")
    public String getUser16(){
        return "Hello Login0016";
    }

    // user_add权限可以访问&获取返回值，user_query可以访问
    @PostAuthorize("hasAuthority('user_add')")
    @RequestMapping("/getUser17")
    public String getUser17(){
        System.out.println("Hello Login0017");
        return "Hello Login0017";
    }

    // ROLE_user权限可以访问&获取返回值
    @PostAuthorize("hasRole('ROLE_user')")
    @RequestMapping("/getUser18")
    public String getUser18(){
        return "Hello Login0018";
    }

    // 获取登录的用户信息
    @PreAuthorize("hasAnyAuthority('ROLE_user','ROLE_admin')")
    @RequestMapping("/getUser19")
    public String getUser19(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("name===>" + name);
        return "Hello Login0019";
    }

    // 获取登录的用户信息
    @RequestMapping("/getUser20")
    public String getUser20(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("name===>" + name);
        return "Hello Login0020";
    }

    // 获取登录的用户信息
    @RequestMapping("/getUser21")
    public String getUser21(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        return "Hello Login0021";
    }
}
