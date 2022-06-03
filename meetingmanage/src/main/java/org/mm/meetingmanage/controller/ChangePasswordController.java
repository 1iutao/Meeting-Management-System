package org.mm.meetingmanage.controller;

import org.mm.meetingmanage.model.Employee;
import org.mm.meetingmanage.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChangePasswordController {
    @Autowired
    private EmployeeService employeeService;


     //跳转到changepassword页面

    @RequestMapping("/changepassword")
    public String changehtml() {
        return "changepassword";
    }


    //修改密码具体操作
    @PostMapping("/dochange")
    public String dochang(String username, String password, String newpassword, Model model) {
        Employee employee = employeeService.doLogin(username, password);
        if (employee == null) {
            model.addAttribute("error", "用户名或原密码输入错误，修改失败");
            return "forward:changepassword";
        } else {
            employeeService.doChang(username, newpassword);
        }
        return "redirect:/";
    }
}
