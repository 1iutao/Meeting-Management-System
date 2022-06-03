package org.mm.meetingmanage.controller;

import org.mm.meetingmanage.model.Employee;
import org.mm.meetingmanage.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

//通知页面
@Controller
public class NotificationsController {
    @Autowired(required = false)
    private MeetingService meetingService;

    @GetMapping("/notifications")
    public String notifications(Model model, HttpSession httpSession) {
        Employee currentUser = (Employee) httpSession.getAttribute("currentUser");
        Integer employeeid = currentUser.getEmployeeId();
        model.addAttribute("ms", meetingService.getSevenDayMeeting(employeeid));
        model.addAttribute("cms", meetingService.getCancelMeeting());
        return "/notifications";
    }

}
