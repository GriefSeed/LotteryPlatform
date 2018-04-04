package com.hyht.LotteryPlat.util;


import com.hyht.LotteryPlat.EnvirArgs;
import com.hyht.LotteryPlat.util.HttpAPIService;
import groovy.util.logging.Commons;
import org.hibernate.validator.internal.util.privilegedactions.GetMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;

import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;

import javax.annotation.Resource;
import java.io.IOException;


/*
* 自启动监听器*/
@Component
public class MyApplicationStartedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    //7200 * 1000
    private static long timeSpace = 5000;


    @Autowired
    public HttpAPIService hAPIServiced;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(this.hAPIServiced + " bilibil");
        //getAccessTokenPro();
    }


    //定时获取access_token
    public void getAccessTokenPro() {

        /*Thread t = new MyThread(httpAPIService, timeSpace);
        t.start();*/
        System.out.println(this.hAPIServiced + " bilibil");
    }


}

class MyThread extends Thread {
    private HttpAPIService httpAPIService;
    private long timeSpace;

    MyThread(HttpAPIService h, long t) {
        this.httpAPIService = h;
        this.timeSpace = t;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("自启动程序开始");
            EnvirArgs.ACCESS_TOKEN = "123";
            try {
                String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&APPID=" + EnvirArgs.APPID + "&secret=" + EnvirArgs.APPSECRET;
                System.out.println(url);
                EnvirArgs.ACCESS_TOKEN = httpAPIService.doGet(url);
                Thread.sleep(timeSpace);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}