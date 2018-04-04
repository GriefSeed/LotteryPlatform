package com.hyht.LotteryPlat;


import com.hyht.LotteryPlat.util.HttpRequestUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.servlet.annotation.WebListener;


/*
* 自启动监听器*/
@WebListener
public class MyApplicationStartedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    //7200 * 1000
    private static long timeSpace = 7000000;



    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        getAccessTokenPro();
        //System.out.println(this.hAPIServiced + " bilibil");
    }

    //定时获取access_token
    public void getAccessTokenPro() {
        Thread t = new MyThread(timeSpace);
        t.start();
    }


}


class MyThread extends Thread {
    private long timeSpace;

    MyThread(long t) {
        this.timeSpace = t;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("自启动程序开始");
            EnvirArgs.ACCESS_TOKEN = "123";
            try {
                String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&APPID=" + EnvirArgs.APPID + "&secret=" + EnvirArgs.APPSECRET;
                EnvirArgs.ACCESS_TOKEN = new HttpRequestUtils().httpGet(url).getString("access_token");
                System.out.println(EnvirArgs.ACCESS_TOKEN);
                Thread.sleep(timeSpace);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}