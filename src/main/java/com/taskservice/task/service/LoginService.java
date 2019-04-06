package com.taskservice.task.service;

import com.taskservice.task.po.Login;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @program: task--com.taskservice.task.service
 * @author: WaHotDog 2019-02-28 21:46
 **/


public interface LoginService {
    //判断手机号是否存在
    Boolean getLoginTel(String tel);
    //通过密码登录
    Login getLoginTelandPass(String tel,String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    //通过验证码登录
    Login getLoginTelandYzm(String tel, String vc);
    //修改密码
    String updatePassByTel(String tel, String vc, String pass) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    //修改密码、修改用户名可选填（不填为原来的）
    String updatePassByTel(String tel, String vc, String pass, String username) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    //添加账号
    String insertUser(String tel, String username, String pass, int sign, String vc) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
