package com.taskservice.task.service;

/**
 * @program: task--com.taskservice.task.service
 * @author: WaHotDog 2019-03-05 15:15
 **/


public interface VcresourcesService {
    //判断手机号验证码是否存在
    String checkVc(String tel,String vc);
    //判断手机号验证码是否存在
    String insertTel(String tel);
    String sendYzm(String tel);
}
