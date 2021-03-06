package com.taskservice.task.controller;

import com.taskservice.task.enums.LoginTypes;
import com.taskservice.task.po.Login;
import com.taskservice.task.service.LoginService;
import com.taskservice.task.service.VcresourcesService;
import com.taskservice.task.tool.EmptyUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @program: task--com.taskservice.task.controller
 * @author: WaHotDog 2019-02-28 22:02
 **/

/**
 * 关于Swagger 显示参数的配置
 * paramType：参数放在哪个地方
 * // * header-->请求参数的获取：@RequestHeader
 * // * query-->请求参数的获取：@RequestParam
 * // * path（用于restful接口）-->请求参数的获取：@PathVariable
 * // * body（不常用）
 * // * form（不常用）
 * // * name：参数名
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

    @ApiOperation("短信验证")
    @GetMapping("/yzmtt")
    public Map<String,Object>  yzmTest(@RequestParam String tel ,@RequestParam String vc){
        String info = vcresourcesService.checkVc(tel,vc);
        System.out.println(info);
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("infoId",info);
        map.put("info",LoginTypes.getLoginTypes(Integer.parseInt(info)));
        return  map;
    }

    //手机短信验证
    //    @PostMapping("/insertuser")
    @ApiOperation("添加新用户")
    @GetMapping("/insertuu")
//    @ResponseBody
    public Map<String,Object> insertUser(@RequestParam String tel, @RequestParam String username, @RequestParam String password, @RequestParam int sign, @RequestParam String vc,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String,Object> map = new HashMap<String,Object>();
        System.out.println(username);
        String info = vcresourcesService.checkVc(tel,vc);
        map.put("infoId",info);
        map.put("info",LoginTypes.getLoginTypes(Integer.parseInt(info)));
        if ("1".equals(info)){
            loginService.insertUser(tel, username, password, sign, vc);
            return map;
        }
        return map;
    }

    @ApiOperation("判断手机号是否被使用")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "tel", dataType = "String", required = true, value = "手机号")
    })
    @GetMapping("/gettel")
//    @ResponseBody
    public Boolean getLoginTel(@RequestParam String tel ) {
        return loginService.getLoginTel(tel);
    }

    @ApiOperation("通过验证码登录")
    @GetMapping("/login/vc")
    public Map<String,Object> getLoginTelandYzm(@RequestParam String tel, @RequestParam String vc, HttpServletRequest request ) {
        Map<String,Object> map =new HashMap<String,Object>();
        if (loginService.getLoginTelandYzm(tel, vc) != null) {
            HttpSession session = request.getSession();
            String username = loginService.getLoginTelandYzm(tel, vc).getUsername();
            session.setAttribute("username", username);
            session.setAttribute("mobile",tel);
            map.put("username",username);
            return map;
        }else{
            Map<String,Object> resultMap = new HashMap<String,Object>();
            resultMap.put("username",1);
            return resultMap;
        }
    }

    @ApiOperation("通过密码登录")
    @GetMapping("/login/pass")
    public Map<String,Object> getLoginTelandPass(@RequestParam String tel, @RequestParam String password, HttpServletRequest request) throws NoSuchAlgorithmException, IOException {
        if (loginService.getLoginTelandPass(tel, password) != null) {
            HttpSession session = request.getSession();
            String username = loginService.getLoginTelandPass(tel, password).getUsername();
            System.out.println(username);
            session.setAttribute("username", username);
            session.setAttribute("mobile",tel);
//            response.sendRedirect("/success");
            Map<String,Object> resultMap = new HashMap<String,Object>();
            resultMap.put("username",username);
            resultMap.put("mobile",tel);
            return resultMap;
        }else{
            Map<String,Object> resultMap = new HashMap<String,Object>();
            resultMap.put("username",1);
            return resultMap;
        }
    }
    @ApiOperation("用于获取用户登录信息")
    @GetMapping("/userinfo")
    public Map<Object,String> getUserInfo(HttpServletRequest request){
        Map<Object,String> map = new HashMap<Object,String>();
        HttpSession session = request.getSession();
        String tel = (String) session.getAttribute("mobile");
        EmptyUtils emptyUtils = new EmptyUtils();
        if (emptyUtils.isEmpty(tel)){
            map.put("username","null");
            return map;
        }else{
            System.out.println(tel);
            Login logininfo= loginService.getUsername(tel);
            String username= logininfo.getUsername();
            int sign1= logininfo.getSign();
            map.put("username",username);
            map.put("tel",tel);
            map.put("sign", String.valueOf(sign1));
            return map;
        }
    }

    @ApiOperation("通过手机号、更新密码、用户名（可以不填，不填为原值）")
    @GetMapping("/updatepass")
    public Map<String,Object>  updatePassByTel(@RequestParam String tel, @RequestParam String vc, @RequestParam String password) throws IOException, NoSuchAlgorithmException {
        String info = loginService.updatePassByTel(tel, vc, password);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("infoId",info);
        map.put("info",LoginTypes.getLoginTypes(Integer.parseInt(info)));
        return map;
    }

    //判断登录是否成功
    @ApiOperation("判断登录是否成功")
    @GetMapping("/tel")
    public String telcs(@RequestParam String tel, @RequestParam String vc,HttpServletRequest request,HttpServletResponse response) throws IOException {
        return LoginTypes.getLoginTypes(Integer.parseInt(vcresourcesService.checkVc(tel, vc)));
    }

    //未注册用户发验证
    @ApiOperation("未注册用户发送消息")
    @GetMapping("/inserttel")
    public Map<String,Object> telinsert(@RequestParam String tel) {
        String info = vcresourcesService.insertTel(tel);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("infoId",info);
        map.put("info",LoginTypes.getLoginTypes(Integer.parseInt(info)));
        return map;
    }
    //已注册用户发验证
    @ApiOperation("已注册用户发送消息")
    @GetMapping("/sendyzm")
    public Map<String,Object> sendYzm(@RequestParam String tel) {
        Map<String,Object> map = new HashMap<String,Object>();
        String info =vcresourcesService.sendYzm(tel);
        map.put("infoId",info);
        map.put("info",LoginTypes.getLoginTypes(Integer.parseInt(info)));
        return map;
    }
    //退出账号
    @ApiOperation("退出账号")
    @GetMapping("/unlogin")
    public String  unlogin(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/"+"main");
        return null;
    }

}
