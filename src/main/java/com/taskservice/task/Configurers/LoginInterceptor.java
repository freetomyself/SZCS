package com.taskservice.task.Configurers;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: task--com.taskservice.task.controller
 * @author: WaHotDog 2019-03-13 16:58
 **/


public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request);
        System.out.println(response);
        System.out.println("1111111111");
        Object username = request.getSession().getAttribute("username");
        if(null ==  username || !(username instanceof  String)){
            response.sendRedirect("/fail");
            System.out.println("2222222222---fail");
            return false;
        }
        String userame = (String)username;
        System.out.println("当前用户已登录"+ userame);
        return true;
    }
    /**
    * 在请求被处理后，视图渲染之前调用
    * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
/**
 * 在整个请求结束后调用
* */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
