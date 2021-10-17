package com.shine.controller;

import com.shine.exception.MyException01;
import com.shine.exception.MyException02;
import com.shine.exception.MyException03;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/ex01")
@Controller
public class ExController {

    @RequestMapping("/e01")
    public String e01(Integer id){
        try {
            System.out.println("id ===> " + id);
        } catch (MyException01 e) {
            e.printStackTrace();
        } catch (MyException02 e) {
            e.printStackTrace();
        } catch (MyException03 e) {
            e.printStackTrace();
        }  finally {
        }
        return "Success";
    }

    @RequestMapping("/e02")
    public String e02(Integer id){
        try {
            System.out.println("id ===> " + id);
        } catch (MyException01 e) {
            e.printStackTrace();
        } catch (MyException02 e) {
            e.printStackTrace();
        } catch (MyException03 e) {
            e.printStackTrace();
        }  finally {
        }
        return "Success";
    }

    @RequestMapping("/e03")
    public String e03(Integer id){
        try {
            System.out.println("id ===> " + id);
        } catch (MyException01 e) {
            e.printStackTrace();
        } catch (MyException02 e) {
            e.printStackTrace();
        } catch (MyException03 e) {
            e.printStackTrace();
        }  finally {
        }
        return "Success";
    }
}
