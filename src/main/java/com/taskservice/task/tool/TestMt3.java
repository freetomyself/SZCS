package com.taskservice.task.tool;

/**
 * @program: task--com.taskservice.task.tool
 * @author: WaHotDog 2019-03-06 15:55
 **/


public class TestMt3 {
    public String test(String tel) {
        createVc vc = new createVc();
        String val = vc.createVc();
        String ip = "210.51.190.233";
        int port = 8085;
        // HTTP 请求工具
        HttpClientUtil util = new HttpClientUtil(ip,  port, "/mt/MT3.ashx");
        String user = "synchronized";//你的用户名
        String pwd = "a12345678";//你的密码：
        String ServiceID = "SEND"; //固定，不需要改变
        String dest = "86" + tel; // 你的目的号码【收短信的电话号码】
        String sender = "";// 你的原号码,可空【大部分国家原号码带不过去，只有少数国家支持透传，所有一般为空】
        String msg = "您的验证码是：" + val;//你的短信内容

        // codec=8 Unicode 编码,  3 ISO-8859-1, 0 ASCII
        // 短信内容 HEX 编码，8 为 UTF-16BE HEX 编码， dataCoding = 8 ,支持所有国家的语言，建议直接使用 8
        String hex = WebNetEncode.encodeHexStr(8, msg);
        hex = hex.trim() + "&codec=8";
        System.out.println("POST MT3");
        // HTTP 封包请求, util.sendPostMessage  返回结果，
        // 如果是以 “-” 开头的为发送失败，请查看错误代码，否则为MSGID
        System.out.println("msgid = " + util.sendPostMessage( user,  pwd,  ServiceID,  dest,  sender,  hex));
        return val;
    }

}
