/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.taskservice.task.tool;

import java.io.ByteArrayInputStream;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpClientConnection;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.ImmutableHttpProcessor;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author guanmofeng@126.com
 */
public class HttpClientUtil {

     HttpParams params;
     HttpProcessor httpproc;
     HttpRequestExecutor httpexecutor;
     HttpContext context;
     HttpHost host;
     DefaultHttpClientConnection conn;
     ConnectionReuseStrategy connStrategy;

     String sendURL = "";
     
     public HttpClientUtil(String ip, int port, String SendURL)
     {
        this.sendURL = SendURL;
        params = new SyncBasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, "GB2312");
        HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
        HttpProtocolParams.setUseExpectContinue(params, true);

        httpproc = new ImmutableHttpProcessor(new HttpRequestInterceptor[] {
                // Required protocol interceptors
                new RequestContent(),
                new RequestTargetHost(),
                // Recommended protocol interceptors
                new RequestConnControl(),
                new RequestUserAgent(),
                new RequestExpectContinue()});

        httpexecutor = new HttpRequestExecutor();
        context = new BasicHttpContext(null);
        host = new HttpHost(ip, port);

        conn = new DefaultHttpClientConnection();
        connStrategy = new DefaultConnectionReuseStrategy();
        context.setAttribute(ExecutionContext.HTTP_CONNECTION, conn);
        context.setAttribute(ExecutionContext.HTTP_TARGET_HOST, host);
     }

      public String sendGetMessage(String user, String pwd, String ServiceID, String dest, String sender, String msg)
     {
          String msgid = "";
        try{

                if (!conn.isOpen()) {
                    Socket socket = new Socket(host.getHostName(), host.getPort());
                    conn.bind(socket, params);
                }
                String par = sendURL.trim() + "?src=%s&pwd=%s&ServiceID=%s&dest=%s&sender=%s&msg=%s";
                 String url = String.format(par, user, pwd, ServiceID, dest, sender, msg);
                //String url = String.format(par, user, pwd, ServiceID, dest, sender, URLEncoder.encode(msg, "GB2312"));
                BasicHttpEntityEnclosingRequest request = new BasicHttpEntityEnclosingRequest("GET", url );

                System.out.println(">> Request URI: " + request.getRequestLine().getUri());

                request.setParams(params);
                httpexecutor.preProcess(request, httpproc, context);
                HttpResponse response = httpexecutor.execute(request, conn, context);
                response.setParams(params);

               
                httpexecutor.postProcess(response, httpproc, context);

                DeBug("<< Response: " + response.getStatusLine());
                msgid = EntityUtils.toString(response.getEntity());
                DeBug(msgid);
                DeBug("==============");
                if (!connStrategy.keepAlive(response, context)) {
                    conn.close();
                } else {
                    System.out.println("Connection kept alive...");
                    conn.close();
                }

        }catch(Exception e){ msgid = "";}
        finally {
            return msgid;
        }
     }


     public String sendPostMessage(String user, String pwd, String ServiceID, String dest, String sender, String msg)
     {
          String msgid = "";
        try{

               
                String parf = "src=%s&pwd=%s&ServiceID=%s&dest=%s&sender=%s&msg=%s";
                 String par = String.format(parf, user, pwd, ServiceID, dest, sender, msg);
                //String url = String.format(par, user, pwd, ServiceID, dest, sender, URLEncoder.encode(msg, "GB2312"));
                BasicHttpEntityEnclosingRequest request = new BasicHttpEntityEnclosingRequest("POST",  sendURL.trim());
                System.out.println(">> Request URI: " + request.getRequestLine().getUri());

                System.out.println(">> Request URI:" + par);
                byte[] data1 = par.getBytes("ASCII");
                ByteArrayEntity entiy = new ByteArrayEntity(data1);
                //hwRequest.ContentType = "application/x-www-form-urlencoded";
                //hwRequest.ContentLength = bData.Length;
                entiy.setContentType("application/x-www-form-urlencoded");
                request.setEntity(entiy);
               
                
                System.out.println(">> Request URI: " + request.getRequestLine().getMethod());
                request.setParams(params);
             
                if (!conn.isOpen()) {
                    Socket socket = new Socket(host.getHostName(), host.getPort());
                    conn.bind(socket, params);
                }

             HeaderIterator it = request.headerIterator();
               while(it.hasNext()) {
                    Header hesd =  it.nextHeader();
                       System.out.println(">> Request Header: " +hesd.getName() + " : " + hesd.getValue());
               }

            
                httpexecutor.preProcess(request, httpproc, context);
                HttpResponse response = httpexecutor.execute(request, conn, context);
                response.setParams(params);

                httpexecutor.postProcess(response, httpproc, context);

                DeBug("<< Response: " + response.getStatusLine());
                msgid = EntityUtils.toString(response.getEntity());
                DeBug(msgid);
                DeBug("==============");
                if (!connStrategy.keepAlive(response, context)) {
                    conn.close();
                } else {
                    System.out.println("Connection kept alive...");
                    conn.close();
                }

        }catch(Exception e){ msgid = "";}
        finally {
            return msgid;
        }
     }


     public static void DeBug(Object obj) {
        System.out.println(obj);
     }


     
     public static void main(String[] args) throws Exception {
        //TestHttpGet();
        String ip = "210.51.190.233";
        int port = 8085;
        HttpClientUtil util = new HttpClientUtil(ip,  port, "/MT3.ashx");

        
       // 用户名
        String user = "";

        //密码：
        String pwd = "";


        String ServiceID = "SEND";

        // 目的号码
        String dest = "8613701387330";

        // 原号码
        String sender = "8613701234567";

       // 短信内容
        String msg = "大家好 test 123 。。";

        // UTF-16BE
        String hex = WebNetEncode.encodeHexStr(8, msg);
        hex = hex.trim() + "&codec=8";
        //"ISO-8859-1"
        //String hex = WebNetEncode.encodeHexStr(3, msg);
        // hex = hex.trim() + "&codec=3";
        
         //"GBK"
        //String hex = WebNetEncode.encodeHexStr(15, msg);
         // hex = hex.trim() + "&codec=15";

        
          //"ASCII"
        //String hex = WebNetEncode.encodeHexStr(0, msg);
        //hex = hex.trim() + "&codec=0";
  



       System.out.println("msgid = " + util.sendGetMessage( user,  pwd,  ServiceID,  dest,  sender,  hex));

    }

    

}
