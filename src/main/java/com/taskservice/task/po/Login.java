package com.taskservice.task.po;

import java.util.Date;

public class Login {
    private Integer id;

    private String username;

    private String password;

    private Integer sign;

    private String tel;

    private String vc;

    private Date createtime;

    private Date updatetime;

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sign=" + sign +
                ", tel='" + tel + '\'' +
                ", vc='" + vc + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getVc() {
        return vc;
    }

    public void setVc(String vc) {
        this.vc = vc == null ? null : vc.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }


}