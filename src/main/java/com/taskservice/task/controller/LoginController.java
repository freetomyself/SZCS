package com.taskservice.task.controller;

import com.taskservice.task.enums.LoginTypes;
import com.taskservice.task.po.Login;
import com.taskservice.task.service.LoginService;
import com.taskservice.task.service.VcresourcesService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


/**
 * @program: task--com.taskservice.task.controller
 * @author: WaHotDog 2019-02-28 22:02
 **/

/**
 * 关于Swagger 显示参数的配置
 * paramType：参数放在哪个地方
// * header-->请求参数的获取：@RequestHeader
// * query-->请求参数的获取：@RequestParam
// * path（用于restful接口）-->请求参数的获取：@PathVariable
// * body（不常用）
// * form（不常用）
// * name：参数名
 * dataType：参数类型
 * required：参数是否必须传
 * value：参数的意思
 * defaultValue：参数的默认值
 **/

@CrossOrigin(origins = "*")
//@Controller
@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    VcresourcesService vcresourcesService;
    @ApiOperation("判断手机号是否被使用")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="tel",dataType="String",required=true,value="手机号")
    })
    @GetMapping("/gettel")
//    @ResponseBody
    public Boolean getLoginTel(@RequestParam String tel){
        return loginService.getLoginTel(tel);
    }
    @ApiOperation("通过验证码登录")
    @GetMapping("/login/vc")
    public String getLoginTelandYzm (@RequestParam String tel, @RequestParam String vc,HttpServletRequest request){
        if (loginService.getLoginTelandYzm(tel,vc)!= null){
            HttpSession session = request.getSession();
            String username = loginService.getLoginTelandYzm(tel,vc).getUsername();
            session.setAttribute("username",username);
            return "redirect:/index";
        }
        return "redirect:/login";
//        return loginService.getLoginTelandYzm(tel,vc);
    }
    @ApiOperation("通过密码登录")
    @GetMapping("/login/pass")
//    @ResponseBody
    public String getLoginTelandPass(@RequestParam String tel, @RequestParam String password,HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//        Login login = loginService.getLoginTelandPass(tel,password);
//        System.out.println(login);
//        String user = login.getUsername();
//        session.setAttribute("user",user);
        if (loginService.getLoginTelandPass(tel,password)!= null){
            HttpSession session = request.getSession();
            String username = loginService.getLoginTelandPass(tel,password).getUsername();
            System.out.println(username);
            session.setAttribute("username",username);
            return "success";
        }
        return "fail";
    }
    @ApiOperation("通过手机号、更新密码、用户名（可以不填，不填为原值）")
    @GetMapping("/updatepass")
    public Boolean updatePassByTel(@RequestParam String tel,  @RequestParam String vc,@RequestParam String password,@RequestParam(required = false) String username) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return loginService.updatePassByTel(tel,vc,password,username);
    }
//    @PostMapping("/insertuser")
    @ApiOperation("添加新用户")
    @GetMapping("/insertuser")
    public String insertUser(@RequestParam String tel, @RequestParam String username, @RequestParam String password, @RequestParam int sign, @RequestParam String vc) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return loginService.insertUser(tel,username,password,sign,vc);
    }
//判断登录是否成功
    @ApiOperation("判断登录是否成功")
    @GetMapping("/tel")
    public String telcs(@RequestParam String tel,@RequestParam String vc){
        return  LoginTypes.getLoginTypes(Integer.parseInt(vcresourcesService.checkVc(tel,vc)));
    }
    //未注册用户发验证
    @ApiOperation("未注册用户发送消息")
    @GetMapping("/inserttel")
    public String telinsert(@RequestParam String tel){
        return vcresourcesService.insertTel(tel);
    }
    //已注册用户发验证
    @ApiOperation("已注册用户发送消息")
    @GetMapping("/sendYzm")
    public String sendYzm(@RequestParam String tel){
        return vcresourcesService.sendYzm(tel);
    }

}
