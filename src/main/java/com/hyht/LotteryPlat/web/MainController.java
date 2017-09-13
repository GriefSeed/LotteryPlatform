package com.hyht.LotteryPlat.web;


import com.hyht.LotteryPlat.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {


    @Autowired
    UsersService usersService;

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
    public String showMain() {
        return "main";
    }

    @RequestMapping("/bitch")
    public String showbitch() {
        return "bitch";
    }

}
