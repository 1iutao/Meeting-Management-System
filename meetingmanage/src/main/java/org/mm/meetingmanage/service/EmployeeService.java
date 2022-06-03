package org.mm.meetingmanage.service;

import org.mm.meetingmanage.mapper.EmployeeMapper;
import org.mm.meetingmanage.mapper.MeetingParticipantsMapper;
import org.mm.meetingmanage.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private MeetingParticipantsMapper meetingparticipantsMapper;

    //登录操作
    public Employee doLogin(String username, String password) {
        Employee employee = employeeMapper.loadEmpByUsername(username);
        if (employee == null || !employee.getPassword().equals(password)) {    //如果employee为null或者密码不正确
            return null;
        }
        return employee;     //如果正确则返回employee
    }

    //注册操作
    public Integer doReg(Employee employee) {
        Employee emp = employeeMapper.loadEmpByUsername(employee.getUsername());
        if (emp != null) {
            return -1;
        }
        employee.setRole(1);      //权限为普通用户
        employee.setStatus(0);      //状态为未审批
        return employeeMapper.doReg(employee);
    }

    //按状态获取员工
    public List<Employee> getAllEmpsByStatus(Integer status) {

        return employeeMapper.getAllEmpsByStatus(status);
    }

    //更新状态
    public Integer updatestatus(Integer employeeid, Integer status) {
        return employeeMapper.updatestatus(employeeid, status);
    }

    //分页查询获取所有emps
    public List<Employee> getAllEmps(Employee employee, Integer page, Integer pageSize) {
        page = (page - 1) *pageSize;
        return employeeMapper.getAllEmps(employee, page, pageSize);
    }

    //获取员工数量
    public Long getTotal(Employee employee) {
        return employeeMapper.getTotal(employee);
    }


    //通过id获取员工信息
    public List<Employee> getEmpsByid(Integer meetingid) {

        List<Integer> list = meetingparticipantsMapper.getAllBymeetingid(meetingid);

        return employeeMapper.getAllEmpsByid(list);

    }

    //修改密码
    public void doChang(String username, String newpassword) {
        employeeMapper.doChang(username, newpassword);
    }

    //通过部门编号获取员工
    public List<Employee> getEmpsByDepId(Integer depId) {
        return employeeMapper.getEmpsByDepId(depId);
    }
}
