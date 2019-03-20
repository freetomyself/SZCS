package com.taskservice.task.tool;

import java.util.Calendar;
import java.util.Date;

/**
 * @program: task--com.taskservice.task.tool
 * @author: WaHotDog 2019-03-06 14:16
 **/


public class getDay {
    /**
     * 获得当天零时零分零秒
     * @return
     */
    public Date initDateByMorning(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
    public Date initDateByNight(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }
}
