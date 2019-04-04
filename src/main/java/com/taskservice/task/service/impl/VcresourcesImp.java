package com.taskservice.task.service.impl;

import com.taskservice.task.enums.LoginTypes;
import com.taskservice.task.mapper.LoginMapper;
import com.taskservice.task.mapper.VcresourcesMapper;
import com.taskservice.task.po.Login;
import com.taskservice.task.po.LoginExample;
import com.taskservice.task.po.Vcresources;
import com.taskservice.task.po.VcresourcesExample;
import com.taskservice.task.service.LoginService;
import com.taskservice.task.service.VcresourcesService;
import com.taskservice.task.tool.TestMt3;
import com.taskservice.task.tool.createVc;
import com.taskservice.task.tool.getDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: task--com.taskservice.task.service.impl
 * @author: WaHotDog 2019-03-05 15:17
 **/

@Service
public class VcresourcesImp implements VcresourcesService {
    @Autowired
    VcresourcesMapper vcresourcesMapper;
    @Autowired
    LoginService loginService;
    @Autowired
    LoginMapper loginMapper;

    @Override
    public String checkVc(String tel, String vc) {
        VcresourcesExample vcresourcesExample = new VcresourcesExample();
        vcresourcesExample.or().andTelEqualTo(tel);
        //按更新时间降序排取第一条
        vcresourcesExample.setOrderByClause("inserttime DESC");
        List<Vcresources> vcresources = vcresourcesMapper.selectByExample(vcresourcesExample);
        System.out.println(vcresources);
        //当条件满足取最新的验证码与用户的验证码比对
        if (vcresources.size() > 0) {
            String getvc = vcresources.get(0).getVc();
            if (getvc.equals(vc)) {
                return String.valueOf(LoginTypes.SUCCESS.getState());
            } else {
                return String.valueOf(LoginTypes.YZMBAD.getState());
            }
        }
        return String.valueOf(LoginTypes.TELBAD.getState());
    }

    //注册调用
    @Override
    public String insertTel(String tel) {
        if(!(loginService.getLoginTel(tel))){
            getDay getDay = new getDay();
            VcresourcesExample vcresourcesExample = new VcresourcesExample();
            vcresourcesExample.or().andTelEqualTo(tel).andInserttimeBetween(getDay.initDateByMorning(),getDay.initDateByNight());
            List<Vcresources> vcresourcess = vcresourcesMapper.selectByExample(vcresourcesExample);
            if (vcresourcess.size()>=10)return LoginTypes.SMSOUT.getMessage() + "当前发送条数为：" + vcresourcess.size();
            //此处需要添加一个号码验证
            Vcresources vcresources = new Vcresources();
            vcresources.setTel(tel);
            TestMt3 testMt3 = new TestMt3();
            String vc = testMt3.test(tel);
            vcresources.setVc(vc);
            vcresourcesMapper.insertSelective(vcresources);
            return String.valueOf(LoginTypes.MSGC.getState());
        }
        return String.valueOf(LoginTypes.TELOVER.getState());
    }

    //登陆调用
    @Override
    public String sendYzm(String tel) {
        //首先判断用户账号是否存在之后将vc同步到对应的用户表上
        if (loginService.getLoginTel(tel)) {
            TestMt3 testMt3 = new TestMt3();
            String vc = testMt3.test(tel);
            Vcresources vcresources = new Vcresources();
            vcresources.setTel(tel);
            vcresources.setVc(vc);
            vcresourcesMapper.insertSelective(vcresources);
            Login login = new Login();
            LoginExample loginExample = new LoginExample();
            loginExample.or().andTelEqualTo(tel);
            login.setVc(vc);
            loginMapper.updateByExampleSelective(login, loginExample);
            return LoginTypes.MSGC.getMessage() +"短信已发送内容为：" + vc;
        } else {
            return LoginTypes.TELNOTFIND.getMessage();
        }
    }
}
