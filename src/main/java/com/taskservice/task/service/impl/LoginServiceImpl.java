package com.taskservice.task.service.impl;

import com.taskservice.task.enums.LoginTypes;
import com.taskservice.task.mapper.LoginMapper;
import com.taskservice.task.po.Login;
import com.taskservice.task.po.LoginExample;
import com.taskservice.task.service.LoginService;
import com.taskservice.task.service.VcresourcesService;
import com.taskservice.task.tool.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @program: task--com.taskservice.task.service
 * @author: WaHotDog 2019-02-28 21:46
 **/

@Service
//@ComponentScan
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginMapper loginMapper;
    @Autowired
    VcresourcesService vcresourcesService;
    //验证号码是否存在
    public Boolean getLoginTel(String tel) {
        //没号码为false
        Boolean flag = false;
        try {
            LoginExample loginExample = new LoginExample();
            loginExample.or().andTelEqualTo(tel);
            List<Login> logins = loginMapper.selectByExample(loginExample);
            System.out.println(logins.toString());
            if (!CollectionUtils.isEmpty(logins) && logins.size() > 0)
                //有号码为true
                flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
//通过密码登录
    @Override
    public Login getLoginTelandPass(String tel, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Boolean flag = false;
        Md5 md5 = new Md5();
        password = md5.EncoderByMd5(password);
        LoginExample loginExample = new LoginExample();
        loginExample.or().andTelEqualTo(tel).andPasswordEqualTo(password);
        List<Login> logins = loginMapper.selectByExample(loginExample);
        String username = null;
        if (!CollectionUtils.isEmpty(logins) && logins.size() > 0) {
            Login login = logins.get(0);
            username = login.getUsername();
            return login;
        }
        return null;
    }
//通过验证码登录
    @Override
    public Login getLoginTelandYzm(String tel, String vc) {
        if(vcresourcesService.checkVc(tel,vc).equals("1")){
            LoginExample loginExample = new LoginExample();
            loginExample.or().andTelEqualTo(tel);
            List<Login> logins = loginMapper.selectByExample(loginExample);
            if (!CollectionUtils.isEmpty(logins) && logins.size() > 0) {
                Login login = logins.get(0);
                return login;
            }
        }
        return null;
    }
//通过号码更新密码
    //true 修改成功、false 修改失败
    @Override
    public String updatePassByTel(String tel, String vc, String pass) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (getLoginTel(tel)) {
            LoginExample loginExample = new LoginExample();
            loginExample.or().andTelEqualTo(tel).andVcEqualTo(vc);
            List<Login> logins = loginMapper.selectByExample(loginExample);
            System.out.println(logins);
            if (!CollectionUtils.isEmpty(logins) && logins.size() > 0) {
                loginExample.or().andTelEqualTo(tel).andVcEqualTo(vc);
                Login login = new Login();
                Md5 md5 = new Md5();
                if (pass.trim().length() == 0 || pass.trim().length() > 16) {
                    pass = "123456";
                }
                pass = md5.EncoderByMd5(pass);
                login.setPassword(pass);
                System.out.println(pass);
                loginMapper.updateByExampleSelective(login, loginExample);
                return String.valueOf(LoginTypes.SUCCESS.getState());
            }
            return String.valueOf(LoginTypes.YZMBAD.getState());
        } else {
            return String.valueOf(LoginTypes.TELNOTFIND.getState());
        }
    }

    @Override
    public String updatePassByTel(String tel, String vc, String pass, String username) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return null;
    }

    //添加用户
    //手机号、用户名、密码、权限标记、验证码
    @Override
    public String insertUser(String tel, String username, String pass, int sign, String vc) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //调用检测号码是否存在
//        vcresourcesService.checkVc(tel,vc)
        String val = vcresourcesService.checkVc(tel,vc);
        if(val.equals("1")){
            if (!getLoginTel(tel)){
            LoginExample loginExample = new LoginExample();
            loginExample.or().andTelEqualTo(tel).andVcEqualTo(vc);
            Login login = new Login();
            login.setUsername(username);
            login.setTel(tel);
            Md5 md5 = new Md5();
            if(pass.trim().length()==0 || pass.trim().length()>16) {
                pass = "123456";
            }
            pass = md5.EncoderByMd5(pass);
            login.setPassword(pass);
            login.setSign(sign);
            login.setVc(vc);
            loginMapper.insertSelective(login);
            return String.valueOf(LoginTypes.ZCC.getMessage());
        }else{
        return String.valueOf(LoginTypes.TELOVER.getMessage());
            }
        }
        return val;
    }
}
