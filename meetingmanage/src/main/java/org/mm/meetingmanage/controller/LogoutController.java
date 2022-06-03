package org.mm.meetingmanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


//退出用户
@Controller
public class LogoutController {
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        //将session从页面中移除
        request.getSession().removeAttribute("currentUser");

        return "redirect:/";
    }
}
