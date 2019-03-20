package com.taskservice.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @program: task--com.taskservice.task.controller
 * @author: WaHotDog 2019-03-09 16:49
 **/

@Controller
//@RequestMapping("/api")
public class webController {
    @RequestMapping("/fail")
    public String index(HttpServletRequest request){
        System.out.println("index");
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        System.out.println("login");
        return "fail";
    }

    @RequestMapping("/success")
    public String login(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("username")==null){
            return "fail";
        }

        return "success";
    }
}
