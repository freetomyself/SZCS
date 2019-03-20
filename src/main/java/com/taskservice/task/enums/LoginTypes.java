package com.taskservice.task.enums;

/**
 * @program: task--com.taskservice.task.enums
 * @author: WaHotDog 2019-03-06 14:44
 **/


public enum LoginTypes {

    SUCCESS(1,"登录成功"),
    PASSBAD(2,"密码错误"),
    YZMBAD(3,"验证码错误"),
    TELOVER(4,"号码已被使用"),
    MSGC(5,"短信发送成功"),
    UPDATEC(6,"验证码已发送"),
    ZCBAD(7,"注册失败"),
    ZCC(8,"注册成功"),
    YZMC(9,"验证码正确"),
    TELBAD(10,"账号错误"),
    TELNOTFIND(11,"号码未注册"),
    SMSOUT(12,"短信发送上限了");

    private int state;
    private String message;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    LoginTypes(int state, String message) {
        this.state = state;
        this.message = message;
    }
    public static String getLoginTypes(int state){
        for (LoginTypes loginTypes : LoginTypes.values()){
            if(loginTypes.getState() == state){
                return loginTypes.getMessage();
            }
        }
        return "";
    }
}
