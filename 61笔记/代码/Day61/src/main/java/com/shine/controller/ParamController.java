package com.shine.controller;

import com.shine.entity.User;
import com.shine.entity.User2;
import com.shine.entity.User3;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/param")
public class ParamController {

    /**
     * 参数名称和方法的形参名字相同
     *  参数可以少于形参
     *  参数可以多于形参
     *  数据类型必须和形参保持一致
     *  数据和形参顺序无要求
     *
     * @param id
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/getParam01")  // http://localhost:8080/Day61/param/getParam01?id=10010&username=zhang&password=lisi
    public String getParam01(Integer id,String username,String password){
        System.out.println("id==>" + id + ",username==>" + username + ",password==>" + password);
        return "index";
    }

    @RequestMapping("/getParam02")  // http://localhost:8080/Day61/param/getParam02?id=10011&username=lisis&password=sisi&registerTime=1990/12/12 15:30:25
    public String getParam02(Integer id, String username, String password, Date registerTime){
        System.out.println("id==>" + id + ",username==>" + username + ",password==>" + password + ",registerTime===》" + registerTime);
        return "index";
    }

    /**
     * 使用对象接收参数
     *  对象的属性会自定赋值传递的参数：类似ORM映射
     *      只会获取参数和属性一样的数据，多了或者少了没有影响
     *  比较常用
     * @param user
     * @return
     */
    @RequestMapping("/getParam03")
    public String getParam03(User user){
        System.out.println("user==>" + user);
        return "index";
    }

    /**
     * 对象和普通参数一起使用
     *  各自赋值，没有影响
     * @param user
     * @param username
     * @return
     */
    @RequestMapping("/getParam04")
    public String getParam04(User user,String username){
        System.out.println("user==>" + user);
        System.out.println("username===>" + username);
        return "index";
    }

    /**
     * 接收数组
     * @param hobby
     * @return
     */
    @RequestMapping("/getParam05")
    public String getParam05(String[] hobby){
        if (hobby != null){
            for (String s : hobby) {
                System.out.println(s);
            }
        }
        return "index";
    }

    /**
     * 接收包含数组的对象
     * @param user2
     * @return
     */
    @RequestMapping("/getParam06")
    public String getParam06(User2 user2){
        System.out.println(user2);
        return "index";
    }

    /**
     * 接收包含集合的对象
     * @param users
     * http://cccxxx?users[0].id=10011&users[0].username=zhang&users[0].password=sisi&users[0].registerTime=2018/12/15
     * @return
     */
    @RequestMapping("/getParam07")
    public String getParam07(User3 users){
        System.out.println(users);
        return "index";
    }

    /**
     * 路径中编写占位符获取参数的数据
     *  注意名字、类型
     * http://localhost:8080/day61/param/getParam08/{id}
     * http://localhost:8080/day61/param/getParam08/110011
     * @return
     */
    @RequestMapping("/getParam08/{uid}")
    public String getParam08(@PathVariable("uid")  Integer id){
        System.out.println("id==>" + id);
        return "index";
    }

    /**
     * 路径收参
     *  接收多个数据
     * @param id
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/getParam09/{id}/{username}/{password}")
    public String getParam09(@PathVariable("id")  Integer id,@PathVariable("username") String username,@PathVariable("password") String password){
        System.out.println("id==>" + id + ",username===>" + username + ",password===>" + password);
        return "index";
    }

    /**
     * 乱码问题解决
     *  前后端字符集设置一致
     *  Tomcat服务器设置UTF-8===》能解决get方式
     *  设置过滤器
     * @param username
     * @return
     */
    @RequestMapping("/getParam10")
    public String getParam10(String username){
        System.out.println("username===>" + username);
        return "index";
    }

}
