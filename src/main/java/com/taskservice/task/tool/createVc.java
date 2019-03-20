package com.taskservice.task.tool;

/**
 * @program: task--com.taskservice.task.tool
 * @author: WaHotDog 2019-03-06 13:31
 **/


public class createVc {
     public String createVc(){
         String val ="";
         for (int i = 0 ;i<6;i++){
             val += String.valueOf((int)(1+Math.random()*9));
         }
         return val;
     }

}
