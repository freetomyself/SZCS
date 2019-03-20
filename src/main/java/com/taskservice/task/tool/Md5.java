package com.taskservice.task.tool;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @program: task--com.taskservice.task.tool
 * @author: WaHotDog 2019-03-05 13:39
 **/


public class Md5 {
    public String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }
    public Boolean checkpassword(String newpassword,String oldpassword)throws NoSuchAlgorithmException,UnsupportedEncodingException{
        if(EncoderByMd5(newpassword).equals(oldpassword)){
            return true;
        }else{
            return false;
        }
    }
}
