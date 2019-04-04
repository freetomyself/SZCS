package com.taskservice.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @program: task--com.taskservice.task.controller
 * @author: WaHotDog 2019-03-09 16:49
 **/
@CrossOrigin(origins = "*")
@Controller
//@RequestMapping("/api")
public class webController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    @RequestMapping("/register")
    public String register(){
        return "register";
    }
    @RequestMapping("/vclogin")
    public String vclogin(){
        return "vclogin";
    }
    @RequestMapping("/update")
    public String update(){
        return "update";
    }
    @RequestMapping("/main")
    public String main(){
        return "main";
    }
}
