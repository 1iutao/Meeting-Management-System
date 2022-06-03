package org.mm.meetingmanage.controller;

import org.mm.meetingmanage.model.Department;
import org.mm.meetingmanage.model.Employee;
import org.mm.meetingmanage.model.Meeting;
import org.mm.meetingmanage.model.MeetingDTO;
import org.mm.meetingmanage.service.DepartmentService;
import org.mm.meetingmanage.service.EmployeeService;
import org.mm.meetingmanage.service.MeetingRoomService;
import org.mm.meetingmanage.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MeetingController {
    @Autowired
    private MeetingRoomService meetingRoomService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MeetingService meetingService;

     //会议预订页面
    @RequestMapping("/bookmeeting")
    public String bookmeeting(Model model) {
        //让bookmeeting页面展示出数据库中的会议室名称
        model.addAttribute("mrs", meetingRoomService.getAllMr());
        return "bookmeeting";
    }


     //获取所有的部门
    @RequestMapping("/alldeps")
    @ResponseBody
    public List<Department> getAllDeps() {
        return departmentService.getAllDeps();
    }


     // 通过部门编号获取员工
    @RequestMapping("/getempbydepid")
    @ResponseBody
    public List<Employee> getEmpsByDepId(Integer depId) {
        return employeeService.getEmpsByDepId(depId);
    }


     //增加会议操作
    @RequestMapping("/doAddMeeting")
    public String doAddMeeting(Meeting meeting, Integer[] mps, HttpSession session) {
        Employee currentuser = (Employee) session.getAttribute("currentUser");
        meeting.setReservationistid(currentuser.getEmployeeId());
        //给meeting的status设置状态为0 （0代表有的会议，1代表取消的会议）
        meeting.setStatus(0);
        Integer result = meetingService.addMeeting(meeting, mps);
        if (result == 1) {
            return "redirect:/searchmeetings";
        } else {
            return "forward:/bookmeeting";
        }
    }

    //一页放10条数据
    public static final Integer PAGE_SIZE = 10;

     // 返回搜索会议页面
    @RequestMapping("/searchmeetings")
    public String getAllMeetings(MeetingDTO meetingDTO, @RequestParam(defaultValue = "1") Integer page, Model model) {
        List<MeetingDTO> listMeetingDTOs = meetingService.listMeetingDTOs(meetingDTO, page, PAGE_SIZE);

        Long total = meetingService.getTotal(meetingDTO);

        model.addAttribute("meetings", listMeetingDTOs);
        model.addAttribute("total", total);
        model.addAttribute("page", page);
        model.addAttribute("pagenum", total % PAGE_SIZE == 0 ? total / PAGE_SIZE : total / PAGE_SIZE + 1);

        return "searchmeetings";
    }

    // 返回meetingdetails页面并且根据meetingid添加会议到model中
    @RequestMapping("/meetingdetails")
    public String meetingdetails(Integer meetingid, Model model) {
        Meeting meeting = meetingService.getmeetingByid(meetingid);
        model.addAttribute("meeting", meeting);
        List<Employee> listEmployee = employeeService.getEmpsByid(meetingid);
        model.addAttribute("ems", listEmployee);
        return "meetingdetails";
    }
}
