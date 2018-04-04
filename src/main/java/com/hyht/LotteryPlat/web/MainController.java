package com.hyht.LotteryPlat.web;


import com.hyht.LotteryPlat.EnvirArgs;
import com.hyht.LotteryPlat.service.UsersService;
import com.hyht.LotteryPlat.util.HttpRequestUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {


    @Autowired
    UsersService usersService;

    /*@Resource
    private HttpAPIService httpAPIService;*/

    @RequestMapping("/searchUser/{userId}")
    public String shareLetter(@PathVariable("userId") String userId) {
        //return usersService.queryUserById(Integer.parseInt(letterId)).toString() + " banana~";
        //System.out.println(usersService.queryUserById(letterId));
        /*Letter letter = usersService.queryUserById(Integer.parseInt(letterId));
        model.addAttribute("letter_content", letter.toString() + " bitch2233");*/
        //model.addAttribute("letter_content", letterId + " bitch2233");

        return usersService.queryUserById(Integer.parseInt(userId)).toString();
    }

    @RequestMapping("/")
    public String showMain(HttpServletRequest request, HttpSession session, Model m) throws Exception {

        //拿到授权码
        String code = request.getParameter("code");
        System.out.println(code + " bulibuli");

        //String getCodeStr = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=appid&redirect_uri=&response_type=code&scope=snsapi_userinfo&state=STATE";
        if (code != null && code != "") {
            //System.out.println(openid + " dudududu");
/*
            //通过code拿到用户openid,存入session，否则会刷新
            if(session.getAttribute("openid") == null || session.getAttribute("openid").toString() == ""){
                String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + EnvirArgs.APPID + "&secret=" + EnvirArgs.APPSECRET + "&code=" + code + "&grant_type=authorization_code";
                JSONObject jb = HttpRequestUtils.httpGet(url);
                session.setAttribute("openid", jb.getString("openid"));
                EnvirArgs.ACCESS_TOKEN = jb.getString("access_token");
                //System.out.println(jb.getString("openid") + " dudududu");
                //System.out.println(jb.toString() + " dodododod");

            }
            else{
                //String access_token = jb.getString("access_token");
                String openid = session.getAttribute("openid").toString();
                //再通过openid拿到用户的基本信息
                String url2 = "https://api.weixin.qq.com/sns/userinfo?access_token=" + EnvirArgs.ACCESS_TOKEN + "&openid=" + openid + "&lang=zh_CN";
                JSONObject jb2 = HttpRequestUtils.httpGet(url2);
                System.out.println("执行成功");
                m.addAttribute("name", jb2.toString());
                return "main";
            }*/
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + EnvirArgs.APPID + "&secret=" + EnvirArgs.APPSECRET + "&code=" + code + "&grant_type=authorization_code";
            JSONObject jb = HttpRequestUtils.httpGet(url);
            EnvirArgs.ACCESS_TOKEN = jb.getString("access_token");
            //openid应该要存入数据库
            String openid = jb.getString("openid");
            String url2 = "https://api.weixin.qq.com/sns/userinfo?access_token=" + EnvirArgs.ACCESS_TOKEN + "&openid=" + openid + "&lang=zh_CN";
            JSONObject jb2 = HttpRequestUtils.httpGet(url2);
            System.out.println(jb2.toString() + " ggg");
            System.out.println("执行成功");
            m.addAttribute("name", jb2.toString());
            return "main";
        }
        return "bitch";
    }

    //获取用户的基本信息
    @RequestMapping("/getUserBasicInfo")
    public String getUserBasicInfo(HttpServletRequest request, Model m) {

        //拿到授权码
        String code = request.getParameter("code");
        System.out.println(code + " bulibuli");
        if (code != null && code != "") {
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + EnvirArgs.APPID + "&secret=" + EnvirArgs.APPSECRET + "&code=" + code + "&grant_type=authorization_code";
            JSONObject jb = HttpRequestUtils.httpGet(url);
            EnvirArgs.ACCESS_TOKEN = jb.getString("access_token");
            //openid应该要存入数据库
            String openid = jb.getString("openid");
            String url2 = "https://api.weixin.qq.com/sns/userinfo?access_token=" + EnvirArgs.ACCESS_TOKEN + "&openid=" + openid + "&lang=zh_CN";
            JSONObject jb2 = HttpRequestUtils.httpGet(url2);
            System.out.println(jb2.toString() + " ggg");
            System.out.println("执行成功");
            m.addAttribute("name", jb2.toString());
        }
        return "main";
    }


    @RequestMapping("/bitch")
    public String showbitch(@RequestBody Object user_info, Model m) {

        m.addAttribute("accessToken", user_info.toString());
        return "main";
    }

}
