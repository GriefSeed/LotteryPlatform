package com.hyht.LateLetter.web;



import com.hyht.LateLetter.util.WeiXinCheck;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping("/")
    public String loginPageTest() {
        System.out.println("热部署2333333");
        return "<h1>TEST SUCCESS！微信后台连通测试</h1>";
    }

    @RequestMapping(value = "/weixin", method = RequestMethod.GET)
    public String weiXinCheck(@RequestParam(name = "signature" ,required = false) String signature,
                              @RequestParam(name = "nonce",required = false) String  nonce,
                              @RequestParam(name = "timestamp",required = false) String  timestamp,
                              @RequestParam(name = "echostr",required = false) String  echostr){
        if(WeiXinCheck.checkSignature(signature, timestamp, nonce)){
            System.out.println("验证成功");
            return echostr;
        }
        System.out.println("验证失败");
        return "";
    }

}
