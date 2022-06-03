package org.mm.meetingmanage.controller;

//审批控制类

import org.mm.meetingmanage.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ApproveaccountController {
    @Autowired
    private EmployeeService employeeService;

    public static final Integer PENDING_APPROVE = 0;   //待批准的status为0

    //获取所有的待批准
    @RequestMapping("/approveaccount")
    public String approveaccount(Model model) {
        model.addAttribute("emps", employeeService.getAllEmpsByStatus(PENDING_APPROVE));
        return "approveaccount";    //返回审批页面
    }

    //更新用户status
    @RequestMapping("/updatestatus")
    public String updatestatus(Integer employeeid, Integer status) {
        employeeService.updatestatus(employeeid, status);
        return "redirect:/admin/approveaccount";      //更新完返回审批页面
    }
}
