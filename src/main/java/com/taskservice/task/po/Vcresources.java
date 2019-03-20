package com.taskservice.task.po;

import java.util.Date;

public class Vcresources {
    private Integer id;

    private String tel;

    private String vc;

    private Date inserttime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    @Override
    public String toString() {
        return "Vcresources{" +
                "id=" + id +
                ", tel='" + tel + '\'' +
                ", vc='" + vc + '\'' +
                ", inserttime=" + inserttime +
                '}';
    }
}
